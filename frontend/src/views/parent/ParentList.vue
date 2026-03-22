<template><CrudPage title="家长信息管理" description="维护家长、监护关系和紧急联系方式。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '家长姓名', prop: 'parentName' }, { label: '关系', prop: 'relationType' }, { label: '电话', prop: 'phone' }, { label: '职业', prop: 'occupation' }]
const searchFields = [{ label: '家长姓名', prop: 'parentName' }, { label: '电话', prop: 'phone' }]
const tableData = [{ parentName: '张敏', relationType: '母亲', phone: '13800000004', occupation: '会计' }, { parentName: '刘强', relationType: '父亲', phone: '13800000005', occupation: '教师' }]
</script>
