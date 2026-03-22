<template>
  <CrudPage
    title="康复训练记录管理"
    description="维护训练类型、主题、时长与训练表现。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getRehabilitationList"
    :detail-api="getRehabilitationDetail"
    :create-api="addRehabilitation"
    :update-api="updateRehabilitation"
    :delete-api="deleteRehabilitation"
    :table-data-fallback="fallbackData"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addRehabilitation, deleteRehabilitation, getRehabilitationDetail, getRehabilitationList, updateRehabilitation } from '@/api/rehabilitation'

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '训练日期', prop: 'trainingDate' },
  { label: '训练类型', prop: 'trainingType' },
  { label: '训练主题', prop: 'trainingTheme' }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '训练类型', prop: 'trainingType' }
]
const formFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '训练日期', prop: 'trainingDate' },
  { label: '训练类型', prop: 'trainingType' },
  { label: '训练主题', prop: 'trainingTheme' },
  { label: '训练时长', prop: 'durationMinutes' }
]
const fallbackData = [
  { id: 1, childId: 1, trainingDate: '2025-03-03', trainingType: '语言训练', trainingTheme: '主动表达练习' }
]
</script>
