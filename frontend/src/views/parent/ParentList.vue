<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '家长姓名', prop: 'parentName' },
  { label: '关系', prop: 'relationType' },
  { label: '电话', prop: 'phone' },
  { label: '职业', prop: 'occupation' }
]
const tableData = [{ parentName: '张敏', relationType: '母亲', phone: '13800000004', occupation: '会计' }]
</script>
