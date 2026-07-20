# FlowSync Backend Launcher (PowerShell)
# Usage: .\start-backend.ps1

$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot\backend

# ---- 1. Check JDK ----
if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
    Write-Host "[ERROR] Java not found. Install JDK 17: https://adoptium.net/" -ForegroundColor Red
    exit 1
}
Write-Host "[OK] Java" -ForegroundColor Green

# ---- 2. Check MySQL ----
if (-not (Get-Process -Name "mysqld" -ErrorAction SilentlyContinue)) {
    Write-Host "[WARN] MySQL may not be running. Start it before continuing." -ForegroundColor Yellow
} else {
    Write-Host "[OK] MySQL" -ForegroundColor Green
}

# ---- 3. Compile ----
Write-Host "[BUILD] Compiling..." -ForegroundColor Cyan
java -classpath ".\.mvn\wrapper\maven-wrapper.jar" "-Dmaven.multiModuleProjectDirectory=$pwd" org.apache.maven.wrapper.MavenWrapperMain compile
if ($LASTEXITCODE -ne 0) { Write-Host "[ERROR] Compilation failed" -ForegroundColor Red; exit 1 }

# ---- 4. Generate classpath (cached, only once) ----
$cpFile = ".classpath.txt"
$needResolve = $true
if (Test-Path $cpFile) {
    if ((Get-Item "pom.xml").LastWriteTime -le (Get-Item $cpFile).LastWriteTime) {
        $needResolve = $false
    }
}
if ($needResolve) {
    Write-Host "[BUILD] Resolving classpath (one-time, ~1min)..." -ForegroundColor Cyan
    java -classpath ".\.mvn\wrapper\maven-wrapper.jar" "-Dmaven.multiModuleProjectDirectory=$pwd" org.apache.maven.wrapper.MavenWrapperMain dependency:build-classpath "-Dmdep.outputFile=$cpFile"
    if ($LASTEXITCODE -ne 0) { Write-Host "[ERROR] Classpath resolution failed" -ForegroundColor Red; exit 1 }
}

# ---- 5. Run ----
Write-Host "[START] http://localhost:8080" -ForegroundColor Green

$classpath = (Get-Content $cpFile -Raw).Trim()
java -cp "target\classes;$classpath" com.flowsync.App
