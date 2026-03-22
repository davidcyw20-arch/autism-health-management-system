<template><CrudPage title="诊断信息管理" description="维护诊断机构、诊断结果与干预建议。" :columns="columns" :search-fields="searchFields" :form-fields="columns" :table-data="tableData" /></template>
<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [{ label: '儿童ID', prop: 'childId' }, { label: '诊断日期', prop: 'diagnosisDate' }, { label: '诊断机构', prop: 'diagnosisHospital' }, { label: '诊断结果', prop: 'diagnosisResult' }]
const searchFields = [{ label: '儿童ID', prop: 'childId' }, { label: '诊断机构', prop: 'diagnosisHospital' }]
const tableData = [{ childId: 1, diagnosisDate: '2024-12-20', diagnosisHospital: '杭州市儿童医院', diagnosisResult: '符合孤独症谱系障碍诊断标准' }]
</script>
