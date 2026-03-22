<template>
  <CrudPage
    title="用户管理"
    description="维护管理员、医生/康复师与家长账号信息。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="formFields"
    :list-api="getUserList"
    :detail-api="getUserDetail"
    :create-api="addUser"
    :update-api="updateUser"
    :delete-api="deleteUser"
    :editable-roles="['admin']"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { addUser, deleteUser, getUserDetail, getUserList, updateUser } from '@/api/user'

const genderOptions = [
  { label: '男', value: 1 },
  { label: '女', value: 2 }
]

const roleOptions = [
  { label: '管理员', value: 'admin' },
  { label: '医生', value: 'doctor' },
  { label: '康复师', value: 'therapist' },
  { label: '家长', value: 'parent' }
]

const statusOptions = [
  { label: '启用', value: 1 },
  { label: '禁用', value: 0 }
]

const columns = [
  { label: 'ID', prop: 'id', width: 80 },
  { label: '用户名', prop: 'username', width: 140 },
  { label: '姓名', prop: 'realName', width: 120 },
  { label: '角色', prop: 'roleCode' },
  { label: '电话', prop: 'phone', width: 140 },
  { label: '状态', prop: 'status' }
]

const searchFields = [
  { label: '用户名', prop: 'username' },
  { label: '姓名', prop: 'realName' },
  { label: '角色', prop: 'roleCode', type: 'select', options: roleOptions },
  { label: '状态', prop: 'status', type: 'select', options: statusOptions }
]

const formFields = [
  { label: '用户名', prop: 'username', maxlength: 50 },
  { label: '密码', prop: 'password', maxlength: 100, required: false },
  { label: '真实姓名', prop: 'realName', maxlength: 50 },
  { label: '性别', prop: 'gender', type: 'select', options: genderOptions, required: false },
  { label: '角色编码', prop: 'roleCode', type: 'select', options: roleOptions },
  { label: '联系电话', prop: 'phone', maxlength: 20, required: false },
  { label: '邮箱', prop: 'email', maxlength: 100, required: false },
  { label: '账号状态', prop: 'status', type: 'select', options: statusOptions, defaultValue: 1 },
  { label: '备注', prop: 'remark', type: 'textarea', rows: 3, maxlength: 255, required: false }
]
</script>
