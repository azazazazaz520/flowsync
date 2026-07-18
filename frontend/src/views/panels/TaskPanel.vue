<!--
  TODO[模块二]: 任务管理面板
  参考模板: PlaceholderPanel.vue + 附录B通用面板模板

  页面内容:
  - 项目筛选下拉框 (数据来源 GET /api/projects)
  - 任务列表 (el-table): 序号、标题、所属项目、负责人、状态、优先级、截止日期、操作
  - 新增/编辑弹窗 (el-dialog): 所属项目、标题、说明、负责人(来源GET /api/users)、状态、优先级、截止日期

  权限:
  - 负责人: 全部操作可用
  - 成员: 仅对自己负责的任务 (row.assigneeId === store.currentUserId) 显示"更新状态"按钮
         弹窗中仅"状态"字段可编辑，其余字段 :disabled="true"

  接口:
  GET    /api/tasks
  POST   /api/tasks
  DELETE /api/tasks/{id}
-->

<template>
  <div class="placeholder-panel">
    <el-empty description="任务管理（待实现 — 模块二）" />
  </div>
</template>

<script setup>
defineOptions({ name: 'TaskPanel' })
</script>

<style scoped>
.placeholder-panel { display: flex; justify-content: center; align-items: center; min-height: 300px; }
</style>
