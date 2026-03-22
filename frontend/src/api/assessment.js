import request from './request'

export const getAssessmentList = params => request.get('/assessments', { params })
