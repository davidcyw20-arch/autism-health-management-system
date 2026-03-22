<template>
  <div>
    <div class="dashboard-banner card-panel">
      <div>
        <h2>欢迎进入毕业设计演示系统</h2>
        <p>你可以从儿童档案、训练记录、随访与评估多个角度展示完整业务闭环。</p>
      </div>
      <el-tag type="success" size="large">现代医疗信息平台风格</el-tag>
    </div>
    <div class="stat-grid">
      <div class="stat-card" v-for="item in cards" :key="item.label">
        <div class="stat-value">{{ item.value }}</div>
        <div class="stat-label">{{ item.label }}</div>
      </div>
    </div>
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="card-panel">
          <h3>快捷入口</h3>
          <div class="quick-grid">
            <el-button v-for="item in shortcuts" :key="item.path" @click="$router.push(item.path)">{{ item.label }}</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="card-panel">
          <h3>统计说明</h3>
          <p>建议在答辩时重点展示训练次数趋势、随访频率与阶段评估得分变化。</p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
const cards = [
  { label: '儿童档案数', value: 3 },
  { label: '本月训练次数', value: 5 },
  { label: '本月随访次数', value: 3 },
  { label: '评估记录数', value: 3 }
]
const shortcuts = [
  { label: '儿童档案', path: '/children' },
  { label: '健康记录', path: '/health' },
  { label: '康复训练', path: '/rehab' },
  { label: '统计分析', path: '/statistics' }
]
</script>
