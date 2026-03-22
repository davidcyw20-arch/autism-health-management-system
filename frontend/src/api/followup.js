import request from './request'

export const getFollowupList = params => request.get('/followups', { params })
