import request from './request'

export const getChildList = params => request.get('/children', { params })
export const getChildDetail = id => request.get(`/children/${id}`)
export const addChild = data => request.post('/children', data)
export const updateChild = (id, data) => request.put(`/children/${id}`, data)
export const deleteChild = id => request.delete(`/children/${id}`)
