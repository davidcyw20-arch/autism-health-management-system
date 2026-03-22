<template><CrudPage title="康复训练记录管理" description="维护训练类型、主题、时长与训练表现。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '儿童ID', prop: 'childId' }, { label: '训练日期', prop: 'trainingDate' }, { label: '训练类型', prop: 'trainingType' }, { label: '训练主题', prop: 'trainingTheme' }]
const searchFields = [{ label: '训练类型', prop: 'trainingType' }, { label: '训练日期', prop: 'trainingDate' }]
const tableData = [{ childId: 1, trainingDate: '2025-03-03', trainingType: '语言训练', trainingTheme: '主动表达练习' }, { childId: 2, trainingDate: '2025-03-11', trainingType: '认知训练', trainingTheme: '颜色与分类' }]
</script>
