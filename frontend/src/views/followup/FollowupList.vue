<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '儿童ID', prop: 'childId' },
  { label: '随访日期', prop: 'followUpDate' },
  { label: '随访方式', prop: 'followUpMethod' },
  { label: '康复进展', prop: 'recoveryProgress' }
]
const tableData = [{ childId: 1, followUpDate: '2025-03-05', followUpMethod: '电话', recoveryProgress: '语言表达有小幅提升' }]
</script>
