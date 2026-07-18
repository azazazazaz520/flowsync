<!--
  TODO[模块一]: 项目管理面板
  参考模板: PlaceholderPanel.vue + 附录B通用面板模板

  页面内容:
  - 项目列表 (el-table): 序号、名称、说明、状态(el-tag)、优先级(el-tag)、起止日期、操作
  - 新增/编辑弹窗 (el-dialog): 名称、说明、状态、优先级、开始日期、结束日期
  - 删除确认

  接口:
  GET    /api/projects
  POST   /api/projects
  DELETE /api/projects/{id}

  权限:
  store.isLeader 为 true 时显示新建/编辑/删除按钮
-->

<template>
  <div class="placeholder-panel">
    <el-empty description="项目管理（待实现 — 模块一）" />
  </div>
</template>

<script setup>
defineOptions({ name: 'ProjectPanel' })
</script>

<style scoped>
.placeholder-panel { display: flex; justify-content: center; align-items: center; min-height: 300px; }
</style>
