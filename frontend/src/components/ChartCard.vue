<template>
  <div class="card-panel">
    <div class="chart-header">
      <h3>{{ title }}</h3>
      <span class="chart-subtitle">{{ subtitle }}</span>
    </div>
    <div ref="chartRef" class="chart-canvas"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onBeforeUnmount, onMounted, ref, watch } from 'vue'

const props = defineProps({
  title: { type: String, default: '' },
  subtitle: { type: String, default: '' },
  option: { type: Object, required: true }
})

const chartRef = ref()
let instance = null

const renderChart = () => {
  if (!chartRef.value) return
  if (!instance) {
    instance = echarts.init(chartRef.value)
  }
  instance.setOption(props.option)
}

onMounted(() => {
  renderChart()
  window.addEventListener('resize', renderChart)
})

watch(() => props.option, () => renderChart(), { deep: true })

onBeforeUnmount(() => {
  window.removeEventListener('resize', renderChart)
  if (instance) {
    instance.dispose()
    instance = null
  }
})
</script>
