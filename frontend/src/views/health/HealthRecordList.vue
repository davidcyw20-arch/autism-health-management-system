<template>
  <CrudPage
    title="健康记录管理"
    description="维护体温、睡眠、情绪、异常症状等日常健康信息。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getHealthList"
    :detail-api="getHealthDetail"
    :create-api="addHealth"
    :update-api="updateHealth"
    :delete-api="deleteHealth"
    :table-data-fallback="fallbackData"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addHealth, deleteHealth, getHealthDetail, getHealthList, updateHealth } from '@/api/health'

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '记录日期', prop: 'recordDate' },
  { label: '体温', prop: 'temperature' },
  { label: '情绪状态', prop: 'emotionState' }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '情绪状态', prop: 'emotionState' }
]
const formFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '记录日期', prop: 'recordDate' },
  { label: '体温', prop: 'temperature' },
  { label: '心率', prop: 'heartRate' },
  { label: '情绪状态', prop: 'emotionState' }
]
const fallbackData = [
  { id: 1, childId: 1, recordDate: '2025-03-01', temperature: 36.5, emotionState: '平稳' }
]
</script>
