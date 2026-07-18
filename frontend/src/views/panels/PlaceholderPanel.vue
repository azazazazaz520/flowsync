<template>
  <div class="placeholder-panel">
    <el-empty :description="description" />
  </div>
</template>

<script setup>
defineOptions({ name: 'PlaceholderPanel' })
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

const labels = {
  '/app/overview':     '总览（待开发）',
  '/app/projects':     '项目管理（待开发）',
  '/app/tasks':        '任务管理（待开发）',
  '/app/tasklogs':     '进度跟踪（待开发）',
  '/app/summaries':    '总结中心（待开发）',
  '/app/members':      '成员列表（待开发）',
  '/app/profile':      '个人信息（待开发）',
  '/app/ai-task-plan': '任务拆解（待开发）',
}

const description = computed(() => labels[route.path] || '功能开发中')
</script>

<style scoped>
.placeholder-panel {
  display: flex; justify-content: center; align-items: center;
  min-height: 300px;
}
</style>
