import { createRouter, createWebHistory } from 'vue-router'

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
    redirect: '/app/overview',
    children: [
      // TODO[总览]: 实现 OverviewPanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'overview', component: () => import('@/views/panels/OverviewPanel.vue') },
      { path: 'overview', component: () => import('@/views/panels/PlaceholderPanel.vue') },

      // TODO[模块一]: 实现 ProjectPanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'projects', component: () => import('@/views/panels/ProjectPanel.vue') },
      { path: 'projects', component: () => import('@/views/panels/PlaceholderPanel.vue') },

      // TODO[模块二]: 实现 TaskPanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'tasks', component: () => import('@/views/panels/TaskPanel.vue') },
      { path: 'tasks', component: () => import('@/views/panels/PlaceholderPanel.vue') },

      // TODO[模块三]: 实现 TaskLogPanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'tasklogs', component: () => import('@/views/panels/TaskLogPanel.vue') },
      { path: 'tasklogs', component: () => import('@/views/panels/PlaceholderPanel.vue') },

      // TODO[模块三]: 实现 SummaryPanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'summaries', component: () => import('@/views/panels/SummaryPanel.vue') },
      { path: 'summaries', component: () => import('@/views/panels/PlaceholderPanel.vue') },

      // TODO[成员列表]: 实现 MemberListPanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'members', component: () => import('@/views/panels/MemberListPanel.vue') },
      { path: 'members', component: () => import('@/views/panels/PlaceholderPanel.vue') },

      // TODO[个人信息]: 实现 ProfilePanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'profile', component: () => import('@/views/panels/ProfilePanel.vue') },
      { path: 'profile', component: () => import('@/views/panels/PlaceholderPanel.vue') },

      // TODO[AI拆解]: 实现 AiTaskPlanPanel.vue 后取消注释下行，删除 placeholder 行
      // { path: 'ai-task-plan', component: () => import('@/views/panels/AiTaskPlanPanel.vue') },
      { path: 'ai-task-plan', component: () => import('@/views/panels/PlaceholderPanel.vue') },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
