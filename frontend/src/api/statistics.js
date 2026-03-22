import request from './request'

export const getDashboardStatistics = () => request.get('/statistics/dashboard')
