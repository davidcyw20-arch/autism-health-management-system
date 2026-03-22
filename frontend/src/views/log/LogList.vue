<template><CrudPage title="系统日志管理" description="查看关键操作日志与异常追踪信息。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '模块名称', prop: 'moduleName' }, { label: '操作类型', prop: 'operationType' }, { label: '操作用户', prop: 'username' }, { label: '状态', prop: 'operationStatus' }]
const searchFields = [{ label: '模块名称', prop: 'moduleName' }, { label: '操作用户', prop: 'username' }]
const tableData = [{ moduleName: '用户管理', operationType: '新增', username: 'admin', operationStatus: '成功' }, { moduleName: '儿童档案管理', operationType: '查询', username: 'doctor01', operationStatus: '成功' }]
</script>
