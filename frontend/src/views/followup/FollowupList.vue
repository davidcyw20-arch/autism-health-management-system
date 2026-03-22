<template>
  <CrudPage
    title="随访记录管理"
    description="维护随访方式、家长反馈、问题发现与处理建议。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getFollowupList"
    :detail-api="getFollowupDetail"
    :create-api="addFollowup"
    :update-api="updateFollowup"
    :delete-api="deleteFollowup"
    :table-data-fallback="fallbackData"
    :editable-roles="['admin', 'doctor']"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addFollowup, deleteFollowup, getFollowupDetail, getFollowupList, updateFollowup } from '@/api/followup'

const methodOptions = [
  { label: '电话', value: '电话' },
  { label: '门诊', value: '门诊' },
  { label: '上门', value: '上门' },
  { label: '视频', value: '视频' }
]

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '随访日期', prop: 'followUpDate' },
  { label: '随访方式', prop: 'followUpMethod' },
  { label: '康复进展', prop: 'recoveryProgress', width: 180 }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '随访方式', prop: 'followUpMethod', type: 'select', options: methodOptions }
]
const formFields = [
  { label: '儿童ID', prop: 'childId', type: 'number', min: 1 },
  { label: '随访日期', prop: 'followUpDate', type: 'date' },
  { label: '随访方式', prop: 'followUpMethod', type: 'select', options: methodOptions },
  { label: '康复进展', prop: 'recoveryProgress', type: 'textarea', rows: 2, maxlength: 150 },
  { label: '用药情况', prop: 'medicationStatus', type: 'textarea', rows: 2, maxlength: 120 },
  { label: '家长反馈', prop: 'familyFeedback', type: 'textarea', rows: 3, maxlength: 200 },
  { label: '发现问题', prop: 'problemFound', type: 'textarea', rows: 3, maxlength: 160 },
  { label: '处理建议', prop: 'handlingAdvice', type: 'textarea', rows: 3, maxlength: 200 },
  { label: '下次随访', prop: 'nextFollowUpDate', type: 'date' }
]
const fallbackData = [
  { id: 1, childId: 1, followUpDate: '2025-03-05', followUpMethod: '电话', recoveryProgress: '语言表达有小幅提升' }
]
</script>
