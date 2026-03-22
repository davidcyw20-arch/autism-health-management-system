<template>
  <div>
    <div class="dashboard-banner card-panel">
      <div>
        <h2>欢迎进入毕业设计演示系统</h2>
        <p>本页可集中展示欢迎信息、统计卡片、快捷入口和趋势图表，适合开场演示。</p>
      </div>
      <el-tag type="success" size="large">医疗信息平台风格</el-tag>
    </div>

    <div class="stat-grid">
      <div class="stat-card" v-for="item in cards" :key="item.label">
        <div class="stat-value">{{ item.value }}</div>
        <div class="stat-label">{{ item.label }}</div>
      </div>
    </div>

    <el-row :gutter="20">
      <el-col :span="10">
        <div class="card-panel">
          <h3>快捷入口</h3>
          <div class="quick-grid">
            <el-button v-for="item in shortcuts" :key="item.path" type="primary" plain @click="$router.push(item.path)">{{ item.label }}</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="14">
        <ChartCard title="评估总分趋势" subtitle="展示阶段干预效果变化" :option="chartOption" />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import ChartCard from '@/components/ChartCard.vue'

const cards = [
  { label: '儿童档案数', value: 3 },
  { label: '健康记录数', value: 5 },
  { label: '本月训练次数', value: 5 },
  { label: '本月随访次数', value: 3 }
]
const shortcuts = [
  { label: '儿童档案', path: '/children' },
  { label: '家长信息', path: '/parents' },
  { label: '康复训练', path: '/rehab' },
  { label: '统计分析', path: '/statistics' }
]
const chartOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  xAxis: { type: 'category', data: ['入组评估', '第一次阶段评估', '第二次阶段评估'] },
  yAxis: { type: 'value' },
  series: [{ type: 'line', smooth: true, areaStyle: {}, data: [257, 330, 379], itemStyle: { color: '#4fb3a8' } }]
}))
</script>
