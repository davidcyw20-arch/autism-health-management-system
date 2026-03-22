<template>
  <CrudPage
    title="儿童档案管理"
    description="用于维护孤独症儿童基本档案、分级信息与就读机构信息。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :table-data="tableData"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
const columns = [
  { label: '档案编号', prop: 'childNo' },
  { label: '姓名', prop: 'childName' },
  { label: '孤独症程度', prop: 'autismLevel' },
  { label: '学校/机构', prop: 'schoolName' }
]
const searchFields = [
  { label: '档案编号', prop: 'childNo' },
  { label: '姓名', prop: 'childName' }
]
const formFields = [...columns]
const tableData = [
  { childNo: 'CH20250001', childName: '张晨曦', autismLevel: '中度', schoolName: '星光融合幼儿园' },
  { childNo: 'CH20250002', childName: '刘雨桐', autismLevel: '轻度', schoolName: '海曙区启智学校' }
]
</script>
