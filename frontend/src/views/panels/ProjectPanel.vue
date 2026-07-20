<template>
  <div class="panel">
    <div class="page-header">
      <h2>项目管理</h2>
      <el-button v-if="store.isLeader" type="primary" @click="openAddDialog">新增项目</el-button>
    </div>

    <el-card style="margin-top: 16px">
      <el-table :data="dataList" border v-loading="loading">
        <el-table-column label="序号" width="60" align="center">
          <template #default="{ $index }">{{ $index + 1 }}</template>
        </el-table-column>
        <el-table-column prop="name" label="名称" min-width="140" />
        <el-table-column prop="description" label="说明" min-width="200" show-overflow-tooltip />
        <el-table-column label="状态" width="90" align="center">
          <template #default="{ row }">
            <el-tag :type="statusType(row.status)" size="small">{{ row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="优先级" width="80" align="center">
          <template #default="{ row }">
            <el-tag :type="priorityType(row.priority)" size="small">{{ row.priority }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="开始日期" width="110" align="center">
          <template #default="{ row }">{{ row.startDate || '-' }}</template>
        </el-table-column>
        <el-table-column label="结束日期" width="110" align="center">
          <template #default="{ row }">{{ row.endDate || '-' }}</template>
        </el-table-column>
        <el-table-column v-if="store.isLeader" label="操作" width="150" align="center" fixed="right">
          <template #default="{ row }">
            <el-button size="small" type="primary" link @click="openEditDialog(row)">编辑</el-button>
            <el-button size="small" type="danger" link @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增/编辑弹窗 -->
    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑项目' : '新增项目'" width="500px" @closed="resetForm">
      <el-form :model="form" label-width="90px">
        <el-form-item label="名称" required>
          <el-input v-model="form.name" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="说明">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入项目说明" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status" style="width: 100%">
            <el-option label="未开始" value="未开始" />
            <el-option label="进行中" value="进行中" />
            <el-option label="已完成" value="已完成" />
          </el-select>
        </el-form-item>
        <el-form-item label="优先级">
          <el-select v-model="form.priority" style="width: 100%">
            <el-option label="低" value="低" />
            <el-option label="中" value="中" />
            <el-option label="高" value="高" />
          </el-select>
        </el-form-item>
        <el-form-item label="开始日期">
          <el-date-picker v-model="form.startDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="结束日期">
          <el-date-picker v-model="form.endDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" :loading="submitting" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
defineOptions({ name: 'ProjectPanel' })

import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useMainStore } from '@/stores'
import request from '@/utils/request'

const store = useMainStore()
const dataList = ref([])
const loading = ref(false)

// ========== 弹窗状态 ==========
const dialogVisible = ref(false)
const isEdit = ref(false)
const submitting = ref(false)
const form = ref(getEmptyForm())

function getEmptyForm() {
  return { id: null, name: '', description: '', status: '未开始', priority: '中', startDate: '', endDate: '' }
}

function resetForm() {
  form.value = getEmptyForm()
}

// ========== 列表查询 ==========
const fetchList = async () => {
  // TODO[模块一]: 调用 GET /api/projects，将返回的 res.data 赋值给 dataList
}

// ========== 新增 / 编辑 ==========
function openAddDialog() {
  isEdit.value = false
  dialogVisible.value = true
}

function openEditDialog(row) {
  // TODO[模块一]: 设置 isEdit = true，将 row 的属性复制到 form，打开弹窗
}

const submitForm = async () => {
  // TODO[模块一]: 调用 POST /api/projects 提交 form.value
  //   成功后关闭弹窗并 refreshList()
}

// ========== 删除 ==========
const handleDelete = async (row) => {
  // TODO[模块一]: 使用 ElMessageBox.confirm 确认后调用 DELETE /api/projects/{id}
  //   成功后刷新列表
}

// ========== 辅助 ==========
function statusType(status) {
  return { '未开始': 'info', '进行中': 'warning', '已完成': 'success' }[status] || 'info'
}

function priorityType(priority) {
  return { '低': 'info', '中': 'warning', '高': 'danger' }[priority] || 'info'
}

onMounted(() => {
  // TODO[模块一]: 调用 fetchList()
})
</script>

<style scoped>
.panel { padding: 20px; }
.page-header { display: flex; justify-content: space-between; align-items: center; }
.page-header h2 { margin: 0; font-size: 20px; color: #303133; }
</style>
