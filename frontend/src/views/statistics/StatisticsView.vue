<template>
  <div>
    <el-row :gutter="20" class="mb-20">
      <el-col :span="6" v-for="item in cards" :key="item.label">
        <div class="stat-card">
          <div class="stat-value">{{ item.value }}</div>
          <div class="stat-label">{{ item.label }}</div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <ChartCard title="健康记录趋势" subtitle="真实统计接口数据" :option="healthOption" />
      </el-col>
      <el-col :span="12">
        <ChartCard title="训练与随访对比" subtitle="月度聚合展示" :option="barOption" />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import ChartCard from '@/components/ChartCard.vue'
import { getDashboardStatistics } from '@/api/statistics'

const dashboard = ref({
  childCount: 0,
  activeCaseCount: 0,
  monthlyFollowUpCount: 0,
  monthlyTrainingCount: 0,
  healthTrend: [],
  monthlyTrainingTrend: [],
  monthlyFollowUpTrend: []
})

const cards = computed(() => [
  { label: '在管儿童数', value: dashboard.value.activeCaseCount || 0 },
  { label: '儿童总数', value: dashboard.value.childCount || 0 },
  { label: '本月训练次数', value: dashboard.value.monthlyTrainingCount || 0 },
  { label: '本月随访次数', value: dashboard.value.monthlyFollowUpCount || 0 }
])

const healthOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  xAxis: { type: 'category', data: dashboard.value.healthTrend.map(item => item.label) },
  yAxis: { type: 'value' },
  series: [{ type: 'line', smooth: true, data: dashboard.value.healthTrend.map(item => item.value), areaStyle: {}, itemStyle: { color: '#1f6feb' } }]
}))

const barOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  legend: { data: ['随访次数', '训练次数'] },
  xAxis: { type: 'category', data: dashboard.value.monthlyTrainingTrend.map(item => item.label) },
  yAxis: { type: 'value' },
  series: [
    { name: '随访次数', type: 'bar', data: dashboard.value.monthlyFollowUpTrend.map(item => item.value), itemStyle: { color: '#4fb3a8' } },
    { name: '训练次数', type: 'bar', data: dashboard.value.monthlyTrainingTrend.map(item => item.value), itemStyle: { color: '#1f6feb' } }
  ]
}))

onMounted(async () => {
  const res = await getDashboardStatistics()
  dashboard.value = res.data || dashboard.value
})
</script>
