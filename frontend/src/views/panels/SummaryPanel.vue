<!--
  TODO[模块三]: 总结中心面板
  参考模板: PlaceholderPanel.vue + 附录B通用面板模板

  本模块仅包含列表展示和新增功能，无编辑和删除。

  页面内容:
  - 总结列表 (el-table): 序号、所属项目、关联任务、类型(el-tag)、内容(截断)、创建人、时间
  - 新增弹窗 (el-dialog): 选择项目(来源GET /api/projects)、选择任务(可选，来源GET /api/tasks)、
                       类型(阶段总结/最终总结)、内容(textarea)

  接口:
  GET  /api/summaries
  POST /api/summaries
-->

<template>
  <div class="placeholder-panel">
    <el-empty description="总结中心（待实现 — 模块三）" />
  </div>
</template>

<script setup>
defineOptions({ name: 'SummaryPanel' })
</script>

<style scoped>
.placeholder-panel { display: flex; justify-content: center; align-items: center; min-height: 300px; }
</style>
