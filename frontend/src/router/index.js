import { createRouter, createWebHistory } from 'vue-router'

// ========== 模块面板路由表（扩展时在此数组中加一行即可） ==========
const panelRoutes = [
  { path: 'projects',  component: () => import('@/views/panels/ProjectPanel.vue') },
  { path: 'tasks',     component: () => import('@/views/panels/TaskPanel.vue') },
  { path: 'tasklogs',  component: () => import('@/views/panels/TaskLogPanel.vue') },
  { path: 'summaries', component: () => import('@/views/panels/SummaryPanel.vue') },
]

const routes = [
  { path: '/', redirect: '/login' },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue')
  },
  {
    path: '/app',
    component: () => import('@/views/HomeView.vue'),
    redirect: `/app/${panelRoutes[0].path}`,
    children: panelRoutes
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
