import request from './request'

export const getParentList = params => request.get('/parents', { params })
