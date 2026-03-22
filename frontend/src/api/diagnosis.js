import request from './request'

export const getDiagnosisList = params => request.get('/diagnosis', { params })
export const getDiagnosisDetail = id => request.get(`/diagnosis/${id}`)
export const addDiagnosis = data => request.post('/diagnosis', data)
export const updateDiagnosis = (id, data) => request.put(`/diagnosis/${id}`, data)
export const deleteDiagnosis = id => request.delete(`/diagnosis/${id}`)
