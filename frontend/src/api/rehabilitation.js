import request from './request'

export const getRehabilitationList = params => request.get('/rehabilitation', { params })
