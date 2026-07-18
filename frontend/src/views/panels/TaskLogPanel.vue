<!--
  TODO[模块三]: 进度跟踪面板
  参考模板: PlaceholderPanel.vue + 附录B通用面板模板

  本模块仅包含列表展示和新增功能，无编辑和删除。

  页面内容:
  - 任务筛选下拉框 (数据来源 GET /api/tasks)
  - 进度列表 (el-table): 序号、任务、进度(el-progress)、说明、记录人、时间
  - 新增弹窗 (el-dialog): 选择任务、进度百分比(el-input-number 0-100)、说明

  接口:
  GET  /api/task-logs
  POST /api/task-logs
-->

<template>
  <div class="placeholder-panel">
    <el-empty description="进度跟踪（待实现 — 模块三）" />
  </div>
</template>

<script setup>
defineOptions({ name: 'TaskLogPanel' })
</script>

<style scoped>
.placeholder-panel { display: flex; justify-content: center; align-items: center; min-height: 300px; }
</style>
