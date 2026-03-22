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
        <ChartCard title="最近 7 日健康记录趋势" subtitle="适合展示健康数据变化" :option="healthOption" />
      </el-col>
      <el-col :span="12">
        <ChartCard title="月度随访与训练对比" subtitle="适合答辩展示管理成效" :option="barOption" />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import ChartCard from '@/components/ChartCard.vue'

const cards = ref([
  { label: '在管儿童数', value: 3 },
  { label: '本月训练次数', value: 5 },
  { label: '本月随访次数', value: 3 },
  { label: '评估记录数', value: 3 }
])

const healthOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  xAxis: { type: 'category', data: ['03-01', '03-02', '03-03', '03-04', '03-05', '03-06', '03-07'] },
  yAxis: { type: 'value' },
  series: [{ type: 'line', smooth: true, data: [6, 8, 7, 9, 10, 11, 8], areaStyle: {}, itemStyle: { color: '#1f6feb' } }]
}))

const barOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  legend: { data: ['随访次数', '训练次数'] },
  xAxis: { type: 'category', data: ['一月', '二月', '三月', '四月'] },
  yAxis: { type: 'value' },
  series: [
    { name: '随访次数', type: 'bar', data: [6, 8, 3, 0], itemStyle: { color: '#4fb3a8' } },
    { name: '训练次数', type: 'bar', data: [12, 9, 5, 0], itemStyle: { color: '#1f6feb' } }
  ]
}))
</script>
