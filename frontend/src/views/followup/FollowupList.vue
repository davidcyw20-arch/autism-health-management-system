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
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addFollowup, deleteFollowup, getFollowupDetail, getFollowupList, updateFollowup } from '@/api/followup'

const columns = [
  { label: 'ID', prop: 'id' },
  { label: '儿童ID', prop: 'childId' },
  { label: '随访日期', prop: 'followUpDate' },
  { label: '随访方式', prop: 'followUpMethod' },
  { label: '康复进展', prop: 'recoveryProgress' }
]
const searchFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '随访方式', prop: 'followUpMethod' }
]
const formFields = [
  { label: '儿童ID', prop: 'childId' },
  { label: '随访日期', prop: 'followUpDate' },
  { label: '随访方式', prop: 'followUpMethod' },
  { label: '康复进展', prop: 'recoveryProgress' },
  { label: '家长反馈', prop: 'familyFeedback' }
]
const fallbackData = [
  { id: 1, childId: 1, followUpDate: '2025-03-05', followUpMethod: '电话', recoveryProgress: '语言表达有小幅提升' }
]
</script>
