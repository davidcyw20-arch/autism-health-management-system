<template>
  <div class="card-panel">
    <div class="page-header">
      <div>
        <h2>{{ title }}</h2>
        <p>{{ description }}</p>
      </div>
      <el-button type="primary" @click="openAdd">新增</el-button>
    </div>

    <el-form :inline="true" :model="searchForm" class="search-bar">
      <el-form-item v-for="field in searchFields" :key="field.prop" :label="field.label">
        <el-input v-model="searchForm[field.prop]" :placeholder="field.placeholder || `请输入${field.label}`" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <el-button @click="handleReset">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="tableData" border>
      <el-table-column v-for="col in columns" :key="col.prop" :prop="col.prop" :label="col.label" :min-width="col.width || 120" />
      <el-table-column label="操作" width="240" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="viewRow(scope.row)">详情</el-button>
          <el-button link type="warning" @click="editRow(scope.row)">编辑</el-button>
          <el-button link type="danger" @click="removeRow(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-wrap">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next"
        :total="pageState.total"
        v-model:current-page="pageState.current"
        v-model:page-size="pageState.size"
        :page-sizes="[5, 10, 20]"
        @current-change="fetchData"
        @size-change="fetchData"
      />
    </div>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="560px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="110px">
        <el-form-item v-for="field in formFields" :key="field.prop" :label="field.label" :prop="field.prop">
          <el-input v-model="form[field.prop]" :placeholder="field.placeholder || `请输入${field.label}`" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveForm">保存</el-button>
      </template>
    </el-dialog>

    <el-drawer v-model="drawerVisible" title="详情查看" size="35%">
      <el-descriptions :column="1" border>
        <el-descriptions-item v-for="col in columns" :key="col.prop" :label="col.label">
          {{ currentRow[col.prop] || '--' }}
        </el-descriptions-item>
      </el-descriptions>
    </el-drawer>
  </div>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { buildRequiredRules } from '@/utils/formRules'

const props = defineProps({
  title: { type: String, default: '模块管理' },
  description: { type: String, default: '支持列表展示、搜索、新增、编辑、删除与详情查看。' },
  columns: { type: Array, default: () => [] },
  searchFields: { type: Array, default: () => [] },
  formFields: { type: Array, default: () => [] },
  listApi: { type: Function, default: null },
  detailApi: { type: Function, default: null },
  createApi: { type: Function, default: null },
  updateApi: { type: Function, default: null },
  deleteApi: { type: Function, default: null },
  tableDataFallback: { type: Array, default: () => [] }
})

const formRef = ref()
const loading = ref(false)
const dialogVisible = ref(false)
const drawerVisible = ref(false)
const dialogTitle = ref('新增信息')
const currentRow = reactive({})
const form = reactive({})
const searchForm = reactive({})
const tableData = ref([])
const pageState = reactive({ current: 1, size: 5, total: 0 })

const rules = computed(() => buildRequiredRules(props.formFields))

const buildParams = () => ({
  current: pageState.current,
  size: pageState.size,
  ...searchForm
})

const normalizePageData = payload => {
  if (payload?.records) {
    tableData.value = payload.records
    pageState.total = Number(payload.total || 0)
  } else if (Array.isArray(payload)) {
    tableData.value = payload
    pageState.total = payload.length
  } else {
    tableData.value = props.tableDataFallback
    pageState.total = props.tableDataFallback.length
  }
}

const fetchData = async () => {
  if (!props.listApi) {
    tableData.value = props.tableDataFallback
    pageState.total = props.tableDataFallback.length
    return
  }
  loading.value = true
  try {
    const res = await props.listApi(buildParams())
    normalizePageData(res.data)
  } catch (error) {
    tableData.value = props.tableDataFallback
    pageState.total = props.tableDataFallback.length
  } finally {
    loading.value = false
  }
}

const handleSearch = async () => {
  pageState.current = 1
  await fetchData()
  ElMessage.success('查询完成')
}

const handleReset = async () => {
  Object.keys(searchForm).forEach(key => { searchForm[key] = '' })
  pageState.current = 1
  await fetchData()
}

const openAdd = () => {
  dialogTitle.value = '新增信息'
  Object.keys(form).forEach(key => delete form[key])
  dialogVisible.value = true
}

const viewRow = async row => {
  if (props.detailApi && row.id) {
    const res = await props.detailApi(row.id)
    Object.assign(currentRow, res.data || row)
  } else {
    Object.assign(currentRow, row)
  }
  drawerVisible.value = true
}

const editRow = row => {
  dialogTitle.value = '编辑信息'
  Object.assign(form, row)
  dialogVisible.value = true
}

const saveForm = async () => {
  if (formRef.value) {
    await formRef.value.validate()
  }
  if (form.id && props.updateApi) {
    await props.updateApi(form.id, form)
  } else if (props.createApi) {
    await props.createApi(form)
  }
  dialogVisible.value = false
  ElMessage.success('保存成功')
  await fetchData()
}

const removeRow = row => {
  ElMessageBox.confirm(`确认删除“${row[props.columns[0]?.prop] || '当前记录'}”吗？`, '删除确认', { type: 'warning' }).then(async () => {
    if (props.deleteApi && row.id) {
      await props.deleteApi(row.id)
    }
    ElMessage.success('删除成功')
    fetchData()
  })
}

onMounted(fetchData)
</script>
