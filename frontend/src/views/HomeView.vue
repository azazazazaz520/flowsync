<template>
  <el-container class="layout-container">
    <!-- 侧边栏 -->
    <el-aside :width="isCollapse ? '64px' : '220px'" class="aside">
      <div class="logo" @click="$router.push('/app')">
        <span v-show="!isCollapse" class="logo-text">FlowSync</span>
        <span v-show="isCollapse" class="logo-text-mini">FS</span>
      </div>
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        router
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409eff"
      >
        <el-menu-item v-for="item in menuItems" :key="item.path" :index="item.path">
          <el-icon><component :is="item.icon" /></el-icon>
          <span>{{ item.label }}</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <!-- 右侧内容 -->
    <el-container>
      <el-header class="header">
        <div class="header-left">
          <el-button text @click="isCollapse = !isCollapse">
            <el-icon :size="20">
              <Fold v-if="!isCollapse" /><Expand v-else />
            </el-icon>
          </el-button>
        </div>
        <div class="header-right">
          <span class="username">{{ store.currentUser.realName || store.currentUser.username }}</span>
          <el-tag size="small" :type="store.isLeader ? 'danger' : 'info'">
            {{ store.currentUser.role }}
          </el-tag>
          <el-button type="danger" text @click="handleLogout">退出登录</el-button>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useMainStore } from '@/stores'

const router = useRouter()
const route = useRoute()
const store = useMainStore()
const isCollapse = ref(false)
const activeMenu = computed(() => route.path)

// ========== 侧边栏菜单项（扩展时在此数组中加一行即可） ==========
const menuItems = [
  { path: '/app/projects',  icon: 'Folder',   label: '项目管理' },
  { path: '/app/tasks',     icon: 'List',     label: '任务管理' },
  { path: '/app/tasklogs',  icon: 'Timer',    label: '进度跟踪' },
  { path: '/app/summaries', icon: 'Document', label: '总结中心' },
]

onMounted(() => {
  store.loadFromStorage()
  if (!store.token) {
    router.push('/login')
  }
})

const handleLogout = () => {
  store.clearUser()
  router.push('/login')
}
</script>

<style scoped>
.layout-container { height: 100vh; }
.aside { background-color: #304156; overflow-y: auto; transition: width 0.3s; }
.logo { height: 60px; display: flex; align-items: center; justify-content: center;
        cursor: pointer; color: #fff; font-size: 20px; font-weight: bold;
        border-bottom: 1px solid rgba(255,255,255,0.1); }
.logo-text-mini { font-size: 18px; }
.header { display: flex; justify-content: space-between; align-items: center;
          background: #fff; border-bottom: 1px solid #e6e6e6;
          box-shadow: 0 1px 4px rgba(0,0,0,0.08); }
.header-left { display: flex; align-items: center; }
.header-right { display: flex; align-items: center; gap: 10px; }
.username { color: #606266; }
.el-main { background-color: #f0f2f5; }
</style>
