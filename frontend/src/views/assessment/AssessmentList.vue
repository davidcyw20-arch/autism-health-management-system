<template><CrudPage title="评估记录管理" description="维护阶段评估、量表得分与干预建议。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '儿童ID', prop: 'childId' }, { label: '评估日期', prop: 'assessmentDate' }, { label: '评估类型', prop: 'assessmentType' }, { label: '总分', prop: 'totalScore' }]
const searchFields = [{ label: '儿童ID', prop: 'childId' }, { label: '评估类型', prop: 'assessmentType' }]
const tableData = [{ childId: 1, assessmentDate: '2025-03-12', assessmentType: '阶段评估', totalScore: 330 }, { childId: 2, assessmentDate: '2025-03-12', assessmentType: '阶段评估', totalScore: 379 }]
</script>
