import request from './request'

export const getUserList = params => request.get('/users', { params })
