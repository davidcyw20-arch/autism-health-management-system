<template><CrudPage title="随访记录管理" description="维护随访方式、家长反馈、问题发现与处理建议。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '儿童ID', prop: 'childId' }, { label: '随访日期', prop: 'followUpDate' }, { label: '随访方式', prop: 'followUpMethod' }, { label: '康复进展', prop: 'recoveryProgress' }]
const searchFields = [{ label: '儿童ID', prop: 'childId' }, { label: '随访方式', prop: 'followUpMethod' }]
const tableData = [{ childId: 1, followUpDate: '2025-03-05', followUpMethod: '电话', recoveryProgress: '语言表达有小幅提升' }, { childId: 3, followUpDate: '2025-03-07', followUpMethod: '视频', recoveryProgress: '感觉统合训练有进步' }]
</script>
