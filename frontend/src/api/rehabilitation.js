import request from './request'

export const getRehabilitationList = params => request.get('/rehabilitation', { params })
export const getRehabilitationDetail = id => request.get(`/rehabilitation/${id}`)
export const addRehabilitation = data => request.post('/rehabilitation', data)
export const updateRehabilitation = (id, data) => request.put(`/rehabilitation/${id}`, data)
export const deleteRehabilitation = id => request.delete(`/rehabilitation/${id}`)
