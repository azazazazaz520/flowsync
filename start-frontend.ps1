# FlowSync Frontend Launcher (PowerShell)
# Usage: .\start-frontend.ps1

$ErrorActionPreference = "Stop"

Set-Location $PSScriptRoot\frontend

# 1. Check Node.js
$nodeVer = & node -v 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Node.js 18+ required. Download: https://nodejs.org/" -ForegroundColor Red
    exit 1
}
Write-Host "[OK] Node.js $nodeVer" -ForegroundColor Green

# 2. npm install (if needed)
if (-not (Test-Path "node_modules")) {
    Write-Host "[INSTALL] npm install..." -ForegroundColor Cyan
    npm install
    if ($LASTEXITCODE -ne 0) {
        Write-Host "[ERROR] npm install failed" -ForegroundColor Red
        exit 1
    }
}
Write-Host "[OK] node_modules ready" -ForegroundColor Green

# 3. Start Vite
Write-Host "[START] Vite dev server — http://localhost:5173" -ForegroundColor Cyan

npm run dev
