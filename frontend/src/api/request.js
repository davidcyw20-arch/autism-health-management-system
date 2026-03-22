import axios from 'axios'
import { ElMessage } from 'element-plus'

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL || '/api'

const service = axios.create({
  baseURL: apiBaseUrl,
  timeout: 10000
})

const fallbackMessageByStatus = {
  400: '请求参数有误，请检查后重试',
  401: '登录状态已失效，请重新登录',
  403: '当前账号无权限访问该功能',
  404: '请求的服务不存在，请确认接口地址与后端服务',
  500: '后端服务处理请求失败，请检查接口参数、后端日志或数据库初始化状态',
  502: '前端代理未连接到后端，请确认 Spring Boot 服务已启动',
  503: '后端服务暂不可用，请确认 8080 端口服务已启动',
  504: '请求后端超时，请稍后重试'
}

const pickMessage = (payload, status) => {
  if (typeof payload === 'string') {
    const trimmed = payload.trim()
    if (trimmed && trimmed !== 'text' && trimmed.length <= 80) {
      return trimmed
    }
  }

  if (payload && typeof payload === 'object') {
    const candidate = payload.message || payload.msg || payload.error
    if (typeof candidate === 'string') {
      const trimmed = candidate.trim()
      if (trimmed && trimmed !== 'text') {
        return trimmed
      }
    }
  }

  return fallbackMessageByStatus[status] || '网络异常，请检查前后端服务是否都已启动'
}

service.interceptors.request.use(config => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
}, error => Promise.reject(error))

service.interceptors.response.use(
  response => {
    const res = response.data
    if (!res || typeof res !== 'object') {
      ElMessage.error(pickMessage(res, response.status))
      return Promise.reject(res)
    }

    if (res.code !== 200) {
      ElMessage.error(pickMessage(res, res.code || response.status))
      if (res.code === 401) {
        localStorage.removeItem('token')
        localStorage.removeItem('userInfo')
        window.location.href = '/login'
      }
      return Promise.reject(res)
    }
    return res
  },
  error => {
    const status = error?.response?.status
    const message = pickMessage(error?.response?.data || error?.message || error, status)

    if (status === 401) {
      ElMessage.error(message)
      localStorage.removeItem('token')
      localStorage.removeItem('userInfo')
      window.location.href = '/login'
    } else {
      ElMessage.error(message)
    }
    return Promise.reject(error)
  }
)

export default service
