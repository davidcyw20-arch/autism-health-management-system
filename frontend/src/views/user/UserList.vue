<template><CrudPage title="用户管理" description="维护管理员、医生/康复师与家长账号信息。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '用户名', prop: 'username' }, { label: '姓名', prop: 'realName' }, { label: '角色', prop: 'roleCode' }, { label: '电话', prop: 'phone' }]
const searchFields = [{ label: '用户名', prop: 'username' }, { label: '角色', prop: 'roleCode' }]
const tableData = [{ username: 'admin', realName: '系统管理员', roleCode: 'admin', phone: '13800000001' }, { username: 'doctor01', realName: '李医生', roleCode: 'doctor', phone: '13800000002' }]
</script>
