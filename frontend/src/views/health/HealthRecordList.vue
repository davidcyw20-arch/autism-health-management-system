<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '儿童ID', prop: 'childId' },
  { label: '记录日期', prop: 'recordDate' },
  { label: '体温', prop: 'temperature' },
  { label: '情绪状态', prop: 'emotionState' }
]
const tableData = [{ childId: 1, recordDate: '2025-03-01', temperature: 36.5, emotionState: '平稳' }]
</script>
