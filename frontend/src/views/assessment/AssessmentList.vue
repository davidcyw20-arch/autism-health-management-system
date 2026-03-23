<template>
  <CrudPage
    :title="pageTitle"
    :description="pageDescription"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getAssessmentList"
    :detail-api="getAssessmentDetail"
    :create-api="addAssessment"
    :update-api="updateAssessment"
    :delete-api="deleteAssessment"
    :table-data-fallback="fallbackData"
    :editable-roles="editableRoles"
    :query-builder="buildQueryParams"
  />
</template>

<script setup>
import { computed } from 'vue'
import CrudPage from '@/components/CrudPage.vue'
import { addAssessment, deleteAssessment, getAssessmentDetail, getAssessmentList, updateAssessment } from '@/api/assessment'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()
const roleCode = computed(() => userStore.userInfo.roleCode || 'parent')
const editableRoles = ['admin', 'doctor']
const pageTitle = computed(() => (roleCode.value === 'parent' ? '我的评估记录' : '评估记录管理'))
const pageDescription = computed(() => (roleCode.value === 'parent'
  ? '查看自己关联儿童的阶段评估结果、量表得分与干预建议。'
  : '维护阶段评估、量表得分与干预建议。'))

const buildQueryParams = (params, userInfo) => (roleCode.value === 'parent'
  ? { ...params, parentUserId: userInfo.userId }
  : params)

const assessmentTypeOptions = [
  { label: '初诊评估', value: '初诊评估' },
  { label: '阶段评估', value: '阶段评估' },
  { label: '结案评估', value: '结案评估' }
]

const scoreField = (label, prop) => ({ label, prop, type: 'number', min: 0, max: 100, precision: 1 })

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '评估日期', prop: 'assessmentDate' },
  { label: '评估类型', prop: 'assessmentType' },
  { label: '总分', prop: 'totalScore' }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '评估类型', prop: 'assessmentType', type: 'select', options: assessmentTypeOptions }
]
const formFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '评估日期', prop: 'assessmentDate', type: 'date' },
  { label: '评估类型', prop: 'assessmentType', type: 'select', options: assessmentTypeOptions },
  scoreField('语言得分', 'languageScore'),
  scoreField('社交得分', 'socialScore'),
  scoreField('行为得分', 'behaviorScore'),
  scoreField('认知得分', 'cognitionScore'),
  scoreField('感觉得分', 'sensoryScore'),
  { label: '总分', prop: 'totalScore', type: 'number', min: 0, max: 500, precision: 1 },
  { label: '评估结论', prop: 'assessmentConclusion', type: 'textarea', rows: 3, maxlength: 200 },
  { label: '干预建议', prop: 'interventionSuggestion', type: 'textarea', rows: 3, maxlength: 220 }
]
const fallbackData = [
  { id: 1, childId: 1, assessmentDate: '2025-03-12', assessmentType: '阶段评估', totalScore: 330 }
]
</script>
