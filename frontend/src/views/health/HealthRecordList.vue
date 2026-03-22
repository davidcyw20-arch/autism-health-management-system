<template><CrudPage title="健康记录管理" description="维护体温、睡眠、情绪、异常症状等日常健康信息。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '儿童ID', prop: 'childId' }, { label: '记录日期', prop: 'recordDate' }, { label: '体温', prop: 'temperature' }, { label: '情绪状态', prop: 'emotionState' }]
const searchFields = [{ label: '儿童ID', prop: 'childId' }, { label: '记录日期', prop: 'recordDate' }]
const tableData = [{ childId: 1, recordDate: '2025-03-01', temperature: 36.5, emotionState: '平稳' }, { childId: 2, recordDate: '2025-03-02', temperature: 36.4, emotionState: '积极' }]
</script>
