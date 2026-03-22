<template><CrudPage :columns="columns" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '用户名', prop: 'username' },
  { label: '姓名', prop: 'realName' },
  { label: '角色', prop: 'roleCode' },
  { label: '电话', prop: 'phone' }
]
const tableData = [{ username: 'admin', realName: '系统管理员', roleCode: 'admin', phone: '13800000001' }]
</script>
