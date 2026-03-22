<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '模块名称', prop: 'moduleName' },
  { label: '操作类型', prop: 'operationType' },
  { label: '操作用户', prop: 'username' },
  { label: '状态', prop: 'operationStatus' }
]
const tableData = [{ moduleName: '用户管理', operationType: '新增', username: 'admin', operationStatus: '成功' }]
</script>
