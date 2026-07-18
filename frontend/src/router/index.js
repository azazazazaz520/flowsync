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
      { path: 'overview',   component: () => import('@/views/panels/PlaceholderPanel.vue') },
      { path: 'projects',   component: () => import('@/views/panels/PlaceholderPanel.vue') },
      { path: 'tasks',      component: () => import('@/views/panels/PlaceholderPanel.vue') },
      { path: 'tasklogs',   component: () => import('@/views/panels/PlaceholderPanel.vue') },
      { path: 'summaries',  component: () => import('@/views/panels/PlaceholderPanel.vue') },
      { path: 'members',    component: () => import('@/views/panels/PlaceholderPanel.vue') },
      { path: 'profile',    component: () => import('@/views/panels/PlaceholderPanel.vue') },
      { path: 'ai-task-plan', component: () => import('@/views/panels/PlaceholderPanel.vue') },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
