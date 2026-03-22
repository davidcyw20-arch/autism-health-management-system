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

    <el-table :data="filteredTableData" border>
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
      <el-pagination background layout="total, prev, pager, next" :total="filteredTableData.length" :page-size="10" />
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
import { computed, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const props = defineProps({
  title: { type: String, default: '模块管理' },
  description: { type: String, default: '支持列表展示、搜索、新增、编辑、删除与详情查看。' },
  columns: { type: Array, default: () => [] },
  searchFields: { type: Array, default: () => [] },
  formFields: { type: Array, default: () => [] },
  tableData: { type: Array, default: () => [] }
})

const formRef = ref()
const dialogVisible = ref(false)
const drawerVisible = ref(false)
const dialogTitle = ref('新增信息')
const currentRow = reactive({})
const form = reactive({})
const searchForm = reactive({})

const rules = computed(() => props.formFields.reduce((acc, item) => {
  acc[item.prop] = [{ required: true, message: `请输入${item.label}`, trigger: 'blur' }]
  return acc
}, {}))

const filteredTableData = computed(() => {
  return props.tableData.filter(row => {
    return props.searchFields.every(field => {
      const keyword = searchForm[field.prop]
      if (!keyword) return true
      return String(row[field.prop] ?? '').includes(keyword)
    })
  })
})

const handleSearch = () => ElMessage.success('已按条件筛选演示数据')
const handleReset = () => {
  Object.keys(searchForm).forEach(key => { searchForm[key] = '' })
}

const openAdd = () => {
  dialogTitle.value = '新增信息'
  Object.keys(form).forEach(key => delete form[key])
  dialogVisible.value = true
}

const viewRow = row => {
  Object.assign(currentRow, row)
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
  dialogVisible.value = false
  ElMessage.success('保存成功（演示页面）')
}

const removeRow = row => {
  ElMessageBox.confirm(`确认删除“${row[props.columns[0]?.prop] || '当前记录'}”吗？`, '删除确认', { type: 'warning' }).then(() => {
    ElMessage.success('删除成功（演示页面）')
  })
}
</script>
