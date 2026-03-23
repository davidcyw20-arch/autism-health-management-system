<template>
  <CrudPage
    :title="pageTitle"
    :description="pageDescription"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getRehabilitationList"
    :detail-api="getRehabilitationDetail"
    :create-api="addRehabilitation"
    :update-api="updateRehabilitation"
    :delete-api="deleteRehabilitation"
    :table-data-fallback="fallbackData"
    :editable-roles="editableRoles"
    :query-builder="buildQueryParams"
  />
</template>

<script setup>
import { computed } from 'vue'
import CrudPage from '@/components/CrudPage.vue'
import { addRehabilitation, deleteRehabilitation, getRehabilitationDetail, getRehabilitationList, updateRehabilitation } from '@/api/rehabilitation'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()
const roleCode = computed(() => userStore.userInfo.roleCode || 'parent')
const editableRoles = ['admin', 'doctor']
const pageTitle = computed(() => (roleCode.value === 'parent' ? '我的康复训练记录' : '康复训练记录管理'))
const pageDescription = computed(() => (roleCode.value === 'parent'
  ? '查看自己关联儿童的训练类型、主题、时长与阶段表现。'
  : '维护训练类型、主题、时长与训练表现。'))

const buildQueryParams = (params, userInfo) => (roleCode.value === 'parent'
  ? { ...params, parentUserId: userInfo.userId }
  : params)

const trainingTypeOptions = [
  { label: '语言训练', value: '语言训练' },
  { label: '社交训练', value: '社交训练' },
  { label: '感统训练', value: '感统训练' },
  { label: '认知训练', value: '认知训练' }
]

const participationOptions = [
  { label: '积极参与', value: '积极参与' },
  { label: '基本配合', value: '基本配合' },
  { label: '需引导', value: '需引导' }
]

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '训练日期', prop: 'trainingDate' },
  { label: '训练类型', prop: 'trainingType' },
  { label: '训练主题', prop: 'trainingTheme' },
  { label: '时长(分钟)', prop: 'durationMinutes' }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '训练类型', prop: 'trainingType', type: 'select', options: trainingTypeOptions }
]
const formFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '训练日期', prop: 'trainingDate', type: 'date' },
  { label: '训练类型', prop: 'trainingType', type: 'select', options: trainingTypeOptions },
  { label: '训练主题', prop: 'trainingTheme', maxlength: 50 },
  { label: '训练时长', prop: 'durationMinutes', type: 'number', min: 1, max: 240 },
  { label: '参与程度', prop: 'participationLevel', type: 'select', options: participationOptions },
  { label: '训练目标', prop: 'trainingGoal', type: 'textarea', rows: 2, maxlength: 120 },
  { label: '表现总结', prop: 'performanceSummary', type: 'textarea', rows: 3, maxlength: 200 },
  { label: '下次计划', prop: 'nextPlan', type: 'textarea', rows: 3, maxlength: 200 }
]
const fallbackData = [
  { id: 1, childId: 1, trainingDate: '2025-03-03', trainingType: '语言训练', trainingTheme: '主动表达练习', durationMinutes: 45 }
]
</script>
