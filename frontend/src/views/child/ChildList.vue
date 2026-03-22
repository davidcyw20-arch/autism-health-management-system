<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '档案编号', prop: 'childNo' },
  { label: '姓名', prop: 'childName' },
  { label: '孤独症程度', prop: 'autismLevel' },
  { label: '学校/机构', prop: 'schoolName' }
]
const tableData = [{ childNo: 'CH20250001', childName: '张晨曦', autismLevel: '中度', schoolName: '星光融合幼儿园' }]
</script>
