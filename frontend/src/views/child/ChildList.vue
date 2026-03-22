<template>
  <CrudPage
    title="儿童档案管理"
    description="用于维护孤独症儿童基本档案、分级信息与就读机构信息。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getChildList"
    :detail-api="getChildDetail"
    :create-api="addChild"
    :update-api="updateChild"
    :delete-api="deleteChild"
    :editable-roles="['admin', 'doctor']"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addChild, deleteChild, getChildDetail, getChildList, updateChild } from '@/api/child'

const genderOptions = [
  { label: '男', value: 1 },
  { label: '女', value: 2 }
]

const levelOptions = [
  { label: '轻度', value: '轻度' },
  { label: '中度', value: '中度' },
  { label: '重度', value: '重度' }
]

const statusOptions = [
  { label: '在管', value: 1 },
  { label: '结档', value: 0 }
]

const columns = [
  { label: '档案编号', prop: 'childNo', width: 140 },
  { label: '姓名', prop: 'childName' },
  { label: '性别', prop: 'gender' },
  { label: '出生日期', prop: 'birthDate', width: 120 },
  { label: '孤独症程度', prop: 'autismLevel' },
  { label: '学校/机构', prop: 'schoolName', width: 180 },
  { label: '状态', prop: 'status' }
]

const searchFields = [
  { label: '档案编号', prop: 'childNo' },
  { label: '姓名', prop: 'childName' },
  { label: '孤独症程度', prop: 'autismLevel', type: 'select', options: levelOptions },
  { label: '状态', prop: 'status', type: 'select', options: statusOptions }
]

const formFields = [
  { label: '档案编号', prop: 'childNo', maxlength: 32 },
  { label: '儿童姓名', prop: 'childName', maxlength: 50 },
  { label: '性别', prop: 'gender', type: 'select', options: genderOptions },
  { label: '出生日期', prop: 'birthDate', type: 'date' },
  { label: '孤独症程度', prop: 'autismLevel', type: 'select', options: levelOptions },
  { label: '学校/机构', prop: 'schoolName', maxlength: 100, required: false },
  { label: '家庭住址', prop: 'address', type: 'textarea', rows: 2, maxlength: 200, required: false },
  { label: '状态', prop: 'status', type: 'select', options: statusOptions, defaultValue: 1 },
  { label: '备注', prop: 'remark', type: 'textarea', rows: 3, maxlength: 200, required: false }
]
</script>
