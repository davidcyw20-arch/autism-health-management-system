import request from './request'

export const getParentList = params => request.get('/parents', { params })
export const getParentDetail = id => request.get(`/parents/${id}`)
export const addParent = data => request.post('/parents', data)
export const updateParent = (id, data) => request.put(`/parents/${id}`, data)
export const deleteParent = id => request.delete(`/parents/${id}`)
