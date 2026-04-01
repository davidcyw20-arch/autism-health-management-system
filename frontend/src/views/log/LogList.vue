<template>
  <CrudPage
    title="系统日志管理"
    description="查看关键操作日志与异常追踪信息。"
    :columns="columns"
    :search-fields="searchFields"
    :form-fields="[]"
    :list-api="getLogList"
    :editable-roles="[]"
  />
</template>

<script setup>
import CrudPage from '@/components/CrudPage.vue'
import { getLogList } from '@/api/log'

const statusOptions = [
  { label: '成功', value: 1 },
  { label: '失败', value: 0 }
]

const columns = [
  { label: 'ID', prop: 'id', width: 80 },
  { label: '模块名称', prop: 'moduleName', width: 140 },
  { label: '操作类型', prop: 'operationType', width: 120 },
  { label: '操作用户', prop: 'username', width: 120 },
  { label: '请求地址', prop: 'requestUri', width: 180 },
  { label: '状态', prop: 'operationStatus', width: 100 },
  { label: '创建时间', prop: 'createTime', width: 180 }
]

const searchFields = [
  { label: '模块名称', prop: 'moduleName' },
  { label: '操作用户', prop: 'username' },
  { label: '状态', prop: 'operationStatus', type: 'select', options: statusOptions }
]
</script>
