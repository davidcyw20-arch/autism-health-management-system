import request from './request'

export const getAssessmentList = params => request.get('/assessments', { params })
export const getAssessmentDetail = id => request.get(`/assessments/${id}`)
export const addAssessment = data => request.post('/assessments', data)
export const updateAssessment = (id, data) => request.put(`/assessments/${id}`, data)
export const deleteAssessment = id => request.delete(`/assessments/${id}`)
