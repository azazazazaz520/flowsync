<template>
  <div class="panel">
    <div class="page-header">
      <h2>进度跟踪</h2>
      <el-button type="primary" @click="openAddDialog">新增进度</el-button>
    </div>

    <!-- 任务筛选 -->
    <el-card style="margin-top: 16px; margin-bottom: 16px">
      <el-form inline>
        <el-form-item label="筛选任务">
          <el-select v-model="filterTaskId" placeholder="全部任务" clearable style="width: 260px"
                     @change="fetchList">
            <el-option v-for="t in taskOptions" :key="t.id" :label="t.title" :value="t.id" />
          </el-select>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card>
      <el-table :data="dataList" border v-loading="loading">
        <el-table-column label="序号" width="60" align="center">
          <template #default="{ $index }">{{ $index + 1 }}</template>
        </el-table-column>
        <el-table-column label="任务" min-width="160">
          <template #default="{ row }">
            {{ getTaskName(row.taskId) }}
          </template>
        </el-table-column>
        <el-table-column label="进度" width="200">
          <template #default="{ row }">
            <el-progress :percentage="row.progressPercent"
                         :color="row.progressPercent === 100 ? '#67c23a' : '#409eff'" />
          </template>
        </el-table-column>
        <el-table-column prop="content" label="说明" min-width="200" show-overflow-tooltip />
        <el-table-column label="记录人" width="100" align="center">
          <template #default="{ row }">
            {{ getUserName(row.operatorId) }}
          </template>
        </el-table-column>
        <el-table-column label="时间" width="160" align="center">
          <template #default="{ row }">{{ row.createTime }}</template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增进度弹窗（仅有新增，无编辑/删除） -->
    <el-dialog v-model="dialogVisible" title="新增进度" width="460px" @closed="resetForm">
      <el-form :model="form" label-width="100px">
        <el-form-item label="选择任务" required>
          <el-select v-model="form.taskId" style="width: 100%" placeholder="请选择任务">
            <el-option v-for="t in taskOptions" :key="t.id" :label="t.title" :value="t.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="进度百分比" required>
          <el-input-number v-model="form.progressPercent" :min="0" :max="100" style="width: 100%" />
        </el-form-item>
        <el-form-item label="进度说明" required>
          <el-input v-model="form.content" type="textarea" :rows="4" placeholder="请描述当前进度" />
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
defineOptions({ name: 'TaskLogPanel' })

import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useMainStore } from '@/stores'
import request from '@/utils/request'

const store = useMainStore()
const dataList = ref([])
const loading = ref(false)
const filterTaskId = ref(null)

// ========== 下拉选项 ==========
const taskOptions = ref([])
const userOptions = ref([])

// TODO[模块三]: 实现 fetchOptions，调用 GET /api/tasks 和 GET /api/users 填充 taskOptions / userOptions
const fetchOptions = async () => {
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

function getEmptyForm() { return { taskId: null, progressPercent: 0, content: '' } }
function resetForm() { form.value = getEmptyForm() }

// ========== 列表查询 ==========
const fetchList = async () => {
  // TODO[模块三]: 调用 GET /api/task-logs?taskId=xxx（若 filterTaskId 有值则传参）
  //   将返回的 res.data 赋值给 dataList
}

// ========== 新增 ==========
function openAddDialog() { dialogVisible.value = true }

const submitForm = async () => {
  // TODO[模块三]: 调用 POST /api/task-logs 提交 form.value
  //   成功后关闭弹窗并刷新列表
}

onMounted(() => {
  // TODO[模块三]: 调用 fetchOptions() 和 fetchList()
})
</script>

<style scoped>
.panel { padding: 20px; }
.page-header { display: flex; justify-content: space-between; align-items: center; }
.page-header h2 { margin: 0; font-size: 20px; color: #303133; }
</style>
