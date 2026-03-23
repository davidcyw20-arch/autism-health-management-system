<template>
  <CrudPage
    :title="pageTitle"
    :description="pageDescription"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getHealthList"
    :detail-api="getHealthDetail"
    :create-api="addHealth"
    :update-api="updateHealth"
    :delete-api="deleteHealth"
    :table-data-fallback="fallbackData"
    :editable-roles="editableRoles"
    :query-builder="buildQueryParams"
  />
</template>

<script setup>
import { computed } from 'vue'
import CrudPage from '@/components/CrudPage.vue'
import { addHealth, deleteHealth, getHealthDetail, getHealthList, updateHealth } from '@/api/health'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()
const roleCode = computed(() => userStore.userInfo.roleCode || 'parent')
const editableRoles = ['admin', 'doctor', 'parent']
const pageTitle = computed(() => (roleCode.value === 'parent' ? '我的健康记录' : '健康记录管理'))
const pageDescription = computed(() => (roleCode.value === 'parent'
  ? '查看并补充自己关联儿童的体温、睡眠、情绪与异常症状记录。'
  : '维护体温、睡眠、情绪、异常症状等日常健康信息。'))

const buildQueryParams = (params, userInfo) => (roleCode.value === 'parent'
  ? { ...params, parentUserId: userInfo.userId }
  : params)

const emotionOptions = [
  { label: '平稳', value: '平稳' },
  { label: '愉快', value: '愉快' },
  { label: '焦虑', value: '焦虑' },
  { label: '烦躁', value: '烦躁' }
]

const appetiteOptions = [
  { label: '良好', value: '良好' },
  { label: '一般', value: '一般' },
  { label: '较差', value: '较差' }
]

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '记录日期', prop: 'recordDate' },
  { label: '体温(℃)', prop: 'temperature' },
  { label: '睡眠时长(h)', prop: 'sleepHours' },
  { label: '情绪状态', prop: 'emotionState' }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '情绪状态', prop: 'emotionState', type: 'select', options: emotionOptions }
]
const formFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '记录日期', prop: 'recordDate', type: 'date' },
  { label: '体温', prop: 'temperature', type: 'number', min: 34, max: 43, precision: 1 },
  { label: '心率', prop: 'heartRate', type: 'number', min: 40, max: 180 },
  { label: '睡眠时长', prop: 'sleepHours', type: 'number', min: 0, max: 24, precision: 1 },
  { label: '食欲情况', prop: 'appetiteLevel', type: 'select', options: appetiteOptions },
  { label: '情绪状态', prop: 'emotionState', type: 'select', options: emotionOptions },
  { label: '异常症状', prop: 'abnormalSymptom', type: 'textarea', rows: 2, maxlength: 120 },
  { label: '护理建议', prop: 'careAdvice', type: 'textarea', rows: 3, maxlength: 200 }
]
const fallbackData = [
  { id: 1, childId: 1, recordDate: '2025-03-01', temperature: 36.5, sleepHours: 8.5, emotionState: '平稳' }
]
</script>
