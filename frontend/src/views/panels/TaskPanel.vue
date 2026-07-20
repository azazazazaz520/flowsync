<template>
  <div class="panel">
    <div class="page-header">
      <h2>任务管理</h2>
      <el-button v-if="store.isLeader" type="primary" @click="openAddDialog">新增任务</el-button>
    </div>

    <!-- 项目筛选 -->
    <el-card style="margin-top: 16px; margin-bottom: 16px">
      <el-form inline>
        <el-form-item label="筛选项目">
          <el-select v-model="filterProjectId" placeholder="全部项目" clearable style="width: 220px"
                     @change="fetchList">
            <el-option v-for="p in projectOptions" :key="p.id" :label="p.name" :value="p.id" />
          </el-select>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card>
      <el-table :data="dataList" border v-loading="loading">
        <el-table-column label="序号" width="60" align="center">
          <template #default="{ $index }">{{ $index + 1 }}</template>
        </el-table-column>
        <el-table-column prop="title" label="标题" min-width="140" show-overflow-tooltip />
        <el-table-column label="所属项目" width="140">
          <template #default="{ row }">
            {{ getProjectName(row.projectId) }}
          </template>
        </el-table-column>
        <el-table-column label="负责人" width="100">
          <template #default="{ row }">
            {{ getUserName(row.assigneeId) }}
          </template>
        </el-table-column>
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
        <el-table-column label="截止日期" width="110" align="center">
          <template #default="{ row }">{{ row.dueDate || '-' }}</template>
        </el-table-column>
        <el-table-column label="操作" width="150" align="center" fixed="right">
          <template #default="{ row }">
            <!-- 负责人：全部操作可用；成员：仅对自己负责的任务显示"更新状态" -->
            <template v-if="store.isLeader">
              <el-button size="small" type="primary" link @click="openEditDialog(row)">编辑</el-button>
              <el-button size="small" type="danger" link @click="handleDelete(row)">删除</el-button>
            </template>
            <template v-else-if="row.assigneeId === store.currentUserId">
              <el-button size="small" type="primary" link @click="openEditDialog(row)">更新状态</el-button>
            </template>
            <span v-else style="color: #c0c4cc">—</span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增/编辑弹窗 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="520px" @closed="resetForm">
      <el-form :model="form" label-width="90px">
        <el-form-item label="所属项目">
          <el-select v-model="form.projectId" style="width: 100%" :disabled="!store.isLeader && isEdit">
            <el-option v-for="p in projectOptions" :key="p.id" :label="p.name" :value="p.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="标题" required>
          <el-input v-model="form.title" placeholder="请输入任务标题"
                    :disabled="!store.isLeader && isEdit" />
        </el-form-item>
        <el-form-item label="说明">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入任务说明"
                    :disabled="!store.isLeader && isEdit" />
        </el-form-item>
        <el-form-item label="负责人">
          <el-select v-model="form.assigneeId" style="width: 100%" :disabled="!store.isLeader && isEdit">
            <el-option v-for="u in userOptions" :key="u.id" :label="u.realName || u.username" :value="u.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status" style="width: 100%">
            <el-option label="未开始" value="未开始" />
            <el-option label="进行中" value="进行中" />
            <el-option label="已完成" value="已完成" />
          </el-select>
        </el-form-item>
        <el-form-item label="优先级">
          <el-select v-model="form.priority" style="width: 100%" :disabled="!store.isLeader && isEdit">
            <el-option label="低" value="低" />
            <el-option label="中" value="中" />
            <el-option label="高" value="高" />
          </el-select>
        </el-form-item>
        <el-form-item label="截止日期">
          <el-date-picker v-model="form.dueDate" type="date" value-format="YYYY-MM-DD" style="width: 100%"
                          :disabled="!store.isLeader && isEdit" />
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
defineOptions({ name: 'TaskPanel' })

import { ref, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useMainStore } from '@/stores'
import request from '@/utils/request'

const store = useMainStore()
const dataList = ref([])
const loading = ref(false)
const filterProjectId = ref(null)

// ========== 下拉选项 ==========
const projectOptions = ref([])
const userOptions = ref([])

// TODO[模块二]: 实现 fetchOptions，调用 GET /api/projects 和 GET /api/users 填充 projectOptions / userOptions
const fetchOptions = async () => {
}

function getProjectName(projectId) {
  const p = projectOptions.value.find(item => item.id === projectId)
  return p ? p.name : ''
}

function getUserName(userId) {
  const u = userOptions.value.find(item => item.id === userId)
  return u ? (u.realName || u.username) : ''
}

// ========== 弹窗状态 ==========
const dialogVisible = ref(false)
const isEdit = ref(false)
const submitting = ref(false)
const form = ref(getEmptyForm())

function getEmptyForm() {
  return { id: null, projectId: null, title: '', description: '', assigneeId: null,
           status: '未开始', priority: '中', dueDate: '' }
}

function resetForm() { form.value = getEmptyForm() }

const dialogTitle = computed(() => {
  if (!store.isLeader && isEdit.value) return '更新任务状态'
  return isEdit.value ? '编辑任务' : '新增任务'
})

// ========== 列表查询 ==========
const fetchList = async () => {
  // TODO[模块二]: 调用 GET /api/tasks?projectId=xxx（若 filterProjectId 有值则传参）
  //   将返回的 res.data 赋值给 dataList
}

// ========== 新增 / 编辑 ==========
function openAddDialog() {
  // TODO[模块二]: 设置 isEdit = false，打开弹窗
}
function openEditDialog(row) {
  // TODO[模块二]: 设置 isEdit = true，将 row 属性复制到 form，打开弹窗
}

const submitForm = async () => {
  // TODO[模块二]: 调用 POST /api/tasks 提交 form.value
  //   成功后关闭弹窗并刷新列表
}

// ========== 删除 ==========
const handleDelete = async (row) => {
  // TODO[模块二]: 使用 ElMessageBox.confirm 确认后调用 DELETE /api/tasks/{id}
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
  // TODO[模块二]: 调用 fetchOptions() 和 fetchList()
})
</script>

<style scoped>
.panel { padding: 20px; }
.page-header { display: flex; justify-content: space-between; align-items: center; }
.page-header h2 { margin: 0; font-size: 20px; color: #303133; }
</style>
