<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '儿童ID', prop: 'childId' },
  { label: '训练日期', prop: 'trainingDate' },
  { label: '训练类型', prop: 'trainingType' },
  { label: '训练主题', prop: 'trainingTheme' }
]
const tableData = [{ childId: 1, trainingDate: '2025-03-03', trainingType: '语言训练', trainingTheme: '主动表达练习' }]
</script>
