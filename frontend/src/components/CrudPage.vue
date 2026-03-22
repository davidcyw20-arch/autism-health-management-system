<template>
  <div class="card-panel">
    <div class="toolbar">
      <el-input v-model="keyword" placeholder="请输入关键词搜索" style="width: 240px" />
      <div>
        <el-button type="primary" @click="dialogVisible = true">新增</el-button>
      </div>
    </div>
    <el-table :data="tableData" border>
      <el-table-column v-for="col in columns" :key="col.prop" :prop="col.prop" :label="col.label" />
      <el-table-column label="操作" width="240">
        <template #default="scope">
          <el-button link type="primary" @click="viewRow(scope.row)">详情</el-button>
          <el-button link type="warning" @click="editRow(scope.row)">编辑</el-button>
          <el-button link type="danger" @click="removeRow(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" title="信息维护" width="520px">
      <el-form :model="form">
        <el-form-item v-for="field in formFields" :key="field.prop" :label="field.label">
          <el-input v-model="form[field.prop]" :placeholder="`请输入${field.label}`" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogVisible = false">保存</el-button>
      </template>
    </el-dialog>

    <el-drawer v-model="drawerVisible" title="详情查看" size="35%">
      <el-descriptions :column="1" border>
        <el-descriptions-item v-for="col in columns" :key="col.prop" :label="col.label">
          {{ currentRow[col.prop] }}
        </el-descriptions-item>
      </el-descriptions>
    </el-drawer>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'

const props = defineProps({
  columns: { type: Array, default: () => [] },
  formFields: { type: Array, default: () => [] },
  tableData: { type: Array, default: () => [] }
})

const keyword = ref('')
const dialogVisible = ref(false)
const drawerVisible = ref(false)
const currentRow = reactive({})
const form = reactive({})

const viewRow = row => {
  Object.assign(currentRow, row)
  drawerVisible.value = true
}
const editRow = row => {
  Object.assign(form, row)
  dialogVisible.value = true
}
const removeRow = row => {
  ElMessageBox.confirm(`确认删除 ${row[props.columns[0]?.prop] || '当前记录'} 吗？`, '提示').then(() => {
    ElMessage.success('删除成功（演示数据）')
  })
}
</script>
