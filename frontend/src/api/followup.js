import request from './request'

export const getFollowupList = params => request.get('/followups', { params })
export const getFollowupDetail = id => request.get(`/followups/${id}`)
export const addFollowup = data => request.post('/followups', data)
export const updateFollowup = (id, data) => request.put(`/followups/${id}`, data)
export const deleteFollowup = id => request.delete(`/followups/${id}`)
