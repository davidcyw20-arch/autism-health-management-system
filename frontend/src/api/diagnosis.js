import request from './request'

export const getDiagnosisList = params => request.get('/diagnosis', { params })
