<template>
  <div class="card-panel">
    <h3>统计分析页面</h3>
    <p>建议接入 ECharts 展示健康趋势、训练类型占比、随访频次与评估总分变化。</p>
    <el-row :gutter="20">
      <el-col :span="12"><div class="chart-box">健康趋势图区域</div></el-col>
      <el-col :span="12"><div class="chart-box">训练占比图区域</div></el-col>
    </el-row>
  </div>
</template>
