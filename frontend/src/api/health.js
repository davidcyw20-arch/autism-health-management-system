import request from './request'

export const getHealthList = params => request.get('/health-records', { params })
