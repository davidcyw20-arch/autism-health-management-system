<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '儿童ID', prop: 'childId' },
  { label: '评估日期', prop: 'assessmentDate' },
  { label: '评估类型', prop: 'assessmentType' },
  { label: '总分', prop: 'totalScore' }
]
const tableData = [{ childId: 1, assessmentDate: '2025-03-12', assessmentType: '阶段评估', totalScore: 330 }]
</script>
