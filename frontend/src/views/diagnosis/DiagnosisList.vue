<template>
  <CrudPage
    title="诊断信息管理"
    description="维护诊断机构、诊断结果与干预建议。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getDiagnosisList"
    :detail-api="getDiagnosisDetail"
    :create-api="addDiagnosis"
    :update-api="updateDiagnosis"
    :delete-api="deleteDiagnosis"
    :editable-roles="['admin', 'doctor']"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addDiagnosis, deleteDiagnosis, getDiagnosisDetail, getDiagnosisList, updateDiagnosis } from '@/api/diagnosis'

const levelOptions = [
  { label: '轻度', value: '轻度' },
  { label: '中度', value: '中度' },
  { label: '重度', value: '重度' }
]

const columns = [
  { label: 'ID', prop: 'id', width: 80 },
  { label: '儿童ID', prop: 'childId' },
  { label: '诊断日期', prop: 'diagnosisDate', width: 120 },
  { label: '诊断机构', prop: 'diagnosisHospital', width: 180 },
  { label: '诊断医生', prop: 'diagnosisDoctor' },
  { label: '孤独症等级', prop: 'autismLevel' }
]

const searchFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '诊断机构', prop: 'diagnosisHospital' },
  { label: '孤独症等级', prop: 'autismLevel', type: 'select', options: levelOptions }
]

const formFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '诊断日期', prop: 'diagnosisDate', type: 'date' },
  { label: '诊断机构', prop: 'diagnosisHospital', maxlength: 100 },
  { label: '诊断医生', prop: 'diagnosisDoctor', maxlength: 50, required: false },
  { label: '诊断结果', prop: 'diagnosisResult', type: 'textarea', rows: 3, maxlength: 255 },
  { label: '孤独症等级', prop: 'autismLevel', type: 'select', options: levelOptions },
  { label: '共病情况', prop: 'comorbidity', type: 'textarea', rows: 2, maxlength: 255, required: false },
  { label: '干预建议', prop: 'suggestion', type: 'textarea', rows: 3, maxlength: 255, required: false }
]
</script>
