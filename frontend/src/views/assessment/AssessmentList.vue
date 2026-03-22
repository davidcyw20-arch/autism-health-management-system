<template>
  <CrudPage
    title="评估记录管理"
    description="维护阶段评估、量表得分与干预建议。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getAssessmentList"
    :detail-api="getAssessmentDetail"
    :create-api="addAssessment"
    :update-api="updateAssessment"
    :delete-api="deleteAssessment"
    :table-data-fallback="fallbackData"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addAssessment, deleteAssessment, getAssessmentDetail, getAssessmentList, updateAssessment } from '@/api/assessment'

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '评估日期', prop: 'assessmentDate' },
  { label: '评估类型', prop: 'assessmentType' },
  { label: '总分', prop: 'totalScore' }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '评估类型', prop: 'assessmentType' }
]
const formFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '评估日期', prop: 'assessmentDate' },
  { label: '评估类型', prop: 'assessmentType' },
  { label: '总分', prop: 'totalScore' },
  { label: '评估结论', prop: 'assessmentConclusion' }
]
const fallbackData = [
  { id: 1, childId: 1, assessmentDate: '2025-03-12', assessmentType: '阶段评估', totalScore: 330 }
]
</script>
