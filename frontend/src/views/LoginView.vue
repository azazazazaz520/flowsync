<template>
  <div class="login-wrapper">
    <el-card class="login-card">
      <h2 class="title">FlowSync</h2>
      <p class="subtitle">小组任务协同管理系统</p>
      <el-form :model="form" label-width="0" @keyup.enter="handleLogin">
        <el-form-item>
          <el-input v-model="form.username" placeholder="用户名" prefix-icon="User" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" type="password" placeholder="密码" prefix-icon="Lock" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="loading" @click="handleLogin" style="width: 100%">
            登 录
          </el-button>
        </el-form-item>
      </el-form>
      <p class="hint">测试账号：leader / member1 / member2，密码均为 123456</p>
    </el-card>
  </div>
</template>

<script setup>
defineOptions({ name: 'LoginView' })

import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { useMainStore } from '@/stores'
import request from '@/utils/request'

const router = useRouter()
const store = useMainStore()
const form = ref({ username: '', password: '' })
const loading = ref(false)

const handleLogin = async () => {
  if (!form.value.username || !form.value.password) {
    ElMessage.warning('请输入用户名和密码')
    return
  }
  loading.value = true
  try {
    const res = await request.post('/auth/login', form.value)
    if (res.code === 200) {
      const user = res.data.user
      store.setUserInfo({
        token: res.data.token,
        id: user.id,
        username: user.username,
        realName: user.realName,
        role: user.role
      })
      ElMessage.success('登录成功')
      router.push('/app')
    } else {
      ElMessage.error(res.msg || '登录失败')
    }
  } catch {
    ElMessage.error('登录失败，请检查账号密码')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-wrapper {
  display: flex; justify-content: center; align-items: center;
  min-height: 100vh; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
.login-card { width: 400px; padding: 40px 30px 20px; }
.title { text-align: center; margin: 0 0 4px; font-size: 28px; color: #303133; }
.subtitle { text-align: center; margin: 0 0 24px; color: #909399; font-size: 14px; }
.hint { text-align: center; color: #c0c4cc; font-size: 12px; margin-top: 8px; }
</style>
