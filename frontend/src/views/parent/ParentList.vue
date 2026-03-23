<template>
  <CrudPage
    :title="pageTitle"
    :description="pageDescription"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getParentList"
    :detail-api="getParentDetail"
    :create-api="addParent"
    :update-api="updateParent"
    :delete-api="deleteParent"
    :editable-roles="editableRoles"
    :query-builder="buildQueryParams"
  />
</template>

<script setup>
import { computed } from 'vue'
import CrudPage from '@/components/CrudPage.vue'
import { addParent, deleteParent, getParentDetail, getParentList, updateParent } from '@/api/parent'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()
const roleCode = computed(() => userStore.userInfo.roleCode || 'parent')
const editableRoles = ['admin', 'doctor']
const pageTitle = computed(() => (roleCode.value === 'parent' ? '我的家长信息' : '家长信息管理'))
const pageDescription = computed(() => (roleCode.value === 'parent'
  ? '查看当前家长账号已绑定的基础信息、联系方式与紧急联系人。'
  : '维护家长、监护关系和紧急联系方式。'))

const buildQueryParams = (params, userInfo) => (roleCode.value === 'parent'
  ? { ...params, userId: userInfo.userId }
  : params)

const genderOptions = [
  { label: '男', value: 1 },
  { label: '女', value: 2 }
]

const relationOptions = [
  { label: '父亲', value: '父亲' },
  { label: '母亲', value: '母亲' },
  { label: '祖父母', value: '祖父母' },
  { label: '其他监护人', value: '其他监护人' }
]

const columns = [
  { label: 'ID', prop: 'id', width: 80 },
  { label: '家长姓名', prop: 'parentName', width: 120 },
  { label: '关系', prop: 'relationType' },
  { label: '电话', prop: 'phone', width: 140 },
  { label: '微信', prop: 'wechat', width: 140 },
  { label: '职业', prop: 'occupation' }
]

const searchFields = [
  { label: '家长姓名', prop: 'parentName' },
  { label: '电话', prop: 'phone' },
  { label: '关系', prop: 'relationType', type: 'select', options: relationOptions }
]

const formFields = [
  { label: '关联用户ID', prop: 'userId', type: 'number', min: 1, required: false },
  { label: '家长姓名', prop: 'parentName', maxlength: 50 },
  { label: '性别', prop: 'gender', type: 'select', options: genderOptions, required: false },
  { label: '关系类型', prop: 'relationType', type: 'select', options: relationOptions },
  { label: '联系电话', prop: 'phone', maxlength: 20 },
  { label: '微信', prop: 'wechat', maxlength: 50, required: false },
  { label: '职业', prop: 'occupation', maxlength: 50, required: false },
  { label: '学历', prop: 'educationLevel', maxlength: 50, required: false },
  { label: '地址', prop: 'address', type: 'textarea', rows: 2, maxlength: 255, required: false },
  { label: '紧急联系人', prop: 'emergencyContact', maxlength: 50, required: false },
  { label: '紧急联系电话', prop: 'emergencyPhone', maxlength: 20, required: false },
  { label: '备注', prop: 'remark', type: 'textarea', rows: 3, maxlength: 255, required: false }
]
</script>
