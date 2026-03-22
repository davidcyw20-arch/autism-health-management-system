import request from './request'

export const getUserList = params => request.get('/users', { params })
export const getUserDetail = id => request.get(`/users/${id}`)
export const addUser = data => request.post('/users', data)
export const updateUser = (id, data) => request.put(`/users/${id}`, data)
export const deleteUser = id => request.delete(`/users/${id}`)
