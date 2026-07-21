<template>
  <div class="panel">
    <div class="page-header">
      <h2>总结中心</h2>
      <el-button type="primary" @click="openAddDialog">新增总结</el-button>
    </div>

    <el-card style="margin-top: 16px">
      <el-table :data="dataList" border v-loading="loading">
        <el-table-column label="序号" width="60" align="center">
          <template #default="{ $index }">{{ $index + 1 }}</template>
        </el-table-column>
        <el-table-column label="项目" width="140">
          <template #default="{ row }">
            {{ getProjectName(row.projectId) }}
          </template>
        </el-table-column>
        <el-table-column label="任务" width="140">
          <template #default="{ row }">
            {{ row.taskId ? getTaskName(row.taskId) : '—' }}
          </template>
        </el-table-column>
        <el-table-column label="类型" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="row.summaryType === '最终总结' ? 'success' : 'warning'" size="small">
              {{ row.summaryType }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="content" label="内容" min-width="240" show-overflow-tooltip />
        <el-table-column label="创建人" width="100" align="center">
          <template #default="{ row }">
            {{ getUserName(row.createdBy) }}
          </template>
        </el-table-column>
        <el-table-column label="时间" width="160" align="center">
          <template #default="{ row }">{{ row.createTime }}</template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增总结弹窗（仅有新增，无编辑/删除） -->
    <el-dialog v-model="dialogVisible" title="新增总结" width="520px" @closed="resetForm">
      <el-form :model="form" label-width="90px">
        <el-form-item label="选择项目" required>
          <el-select v-model="form.projectId" style="width: 100%" placeholder="请选择项目"
                     @change="onProjectChange">
            <el-option v-for="p in projectOptions" :key="p.id" :label="p.name" :value="p.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="关联任务">
          <el-select v-model="form.taskId" style="width: 100%" placeholder="可选，不选则为项目总结" clearable>
            <el-option v-for="t in filteredTaskOptions" :key="t.id" :label="t.title" :value="t.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="总结类型" required>
          <el-select v-model="form.summaryType" style="width: 100%">
            <el-option label="阶段总结" value="阶段总结" />
            <el-option label="最终总结" value="最终总结" />
          </el-select>
        </el-form-item>
        <el-form-item label="总结内容" required>
          <el-input v-model="form.content" type="textarea" :rows="6" placeholder="请输入总结内容" />
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
defineOptions({ name: 'SummaryPanel' })

import { ref, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useMainStore } from '@/stores'
import request from '@/utils/request'

const store = useMainStore()
const dataList = ref([])
const loading = ref(false)

// ========== 下拉选项 ==========
const projectOptions = ref([])
const taskOptions = ref([])
const userOptions = ref([])

// [模块四]: 实现 fetchOptions，调用 GET /api/projects、GET /api/tasks、GET /api/users
//   分别填充 projectOptions / taskOptions / userOptions
const fetchOptions = async () => {
  try {
    const [projectsRes, tasksRes, usersRes] = await Promise.all([
      request.get('/projects'),
      request.get('/tasks'),
      request.get('/users')
    ])
    projectOptions.value = projectsRes.data.data
    taskOptions.value = tasksRes.data.data
    userOptions.value = usersRes.data.data
  } catch (e) {
    ElMessage.error('加载下拉选项失败')
  }
}

// 根据选中项目过滤任务列表
const filteredTaskOptions = computed(() => {
  if (!form.value.projectId) return taskOptions.value
  return taskOptions.value.filter(t => t.projectId === form.value.projectId)
})

function onProjectChange() { form.value.taskId = null }

function getProjectName(projectId) {
  const p = projectOptions.value.find(item => item.id === projectId)
  return p ? p.name : ''
}
function getTaskName(taskId) {
  const t = taskOptions.value.find(item => item.id === taskId)
  return t ? t.title : ''
}
function getUserName(userId) {
  const u = userOptions.value.find(item => item.id === userId)
  return u ? (u.realName || u.username) : ''
}

// ========== 弹窗状态 ==========
const dialogVisible = ref(false)
const submitting = ref(false)
const form = ref(getEmptyForm())

function getEmptyForm() { return { projectId: null, taskId: null, summaryType: '阶段总结', content: '' } }
function resetForm() { form.value = getEmptyForm() }

// ========== 列表查询 ==========
const fetchList = async () => {
  loading.value = true
  try {
    const res = await request.get('/summaries')
    dataList.value = res.data.data
  } catch (e) {
    ElMessage.error('加载总结列表失败')
  } finally {
    loading.value = false
  }
}

// ========== 新增 ==========
function openAddDialog() { dialogVisible.value = true }

const submitForm = async () => {
  if (!form.value.projectId) {
    ElMessage.warning('请选择项目')
    return
  }
  if (!form.value.content) {
    ElMessage.warning('请输入总结内容')
    return
  }
  submitting.value = true
  try {
    await request.post('/summaries', form.value)
    ElMessage.success('新增成功')
    dialogVisible.value = false
    fetchList()
  } catch (e) {
    ElMessage.error('新增总结失败')
  } finally {
    submitting.value = false
  }
}

onMounted(() => {
  fetchOptions()
  fetchList()
})
</script>

<style scoped>
.panel { padding: 20px; }
.page-header { display: flex; justify-content: space-between; align-items: center; }
.page-header h2 { margin: 0; font-size: 20px; color: #303133; }
</style>
