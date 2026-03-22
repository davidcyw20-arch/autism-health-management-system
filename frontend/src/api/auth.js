import request from './request'

export const loginApi = data => request.post('/auth/login', data)
