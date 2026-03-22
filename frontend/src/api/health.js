import request from './request'

export const getHealthList = params => request.get('/health-records', { params })
export const getHealthDetail = id => request.get(`/health-records/${id}`)
export const addHealth = data => request.post('/health-records', data)
export const updateHealth = (id, data) => request.put(`/health-records/${id}`, data)
export const deleteHealth = id => request.delete(`/health-records/${id}`)
