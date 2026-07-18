import axios from 'axios'
import { useMainStore } from '@/stores'

const request = axios.create({
  baseURL: '/api',
  timeout: 10000
})

// 请求拦截器：注入 token 和 currentUserId
request.interceptors.request.use(config => {
  const store = useMainStore()
  if (store.token) {
    config.headers.Authorization = store.token
  }
  if (store.currentUserId) {
    if (config.method === 'get' || config.method === 'delete') {
      config.params = { ...config.params, currentUserId: store.currentUserId }
    }
  }
  return config
}, error => Promise.reject(error))

// 响应拦截器：自动解包
request.interceptors.response.use(
  res => res.data,
  err => {
    console.error('[请求失败]', err.response?.data || err.message)
    return Promise.reject(err)
  }
)

export default request
