<template>
  <div>
    <div class="dashboard-banner card-panel">
      <div>
        <div class="hero-badge">{{ roleLabel }}</div>
        <h2>{{ welcomeTitle }}</h2>
        <p>{{ welcomeDesc }}</p>
      </div>
      <div class="dashboard-highlight">
        <div class="highlight-item"><strong>{{ cards[0].value }}</strong><span>{{ cards[0].label }}</span></div>
        <div class="highlight-item"><strong>{{ cards[1].value }}</strong><span>{{ cards[1].label }}</span></div>
      </div>
    </div>

    <div class="stat-grid">
      <div class="stat-card gradient-card" v-for="item in cards" :key="item.label">
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
        <div class="card-panel soft-panel">
          <h3>系统特点</h3>
          <ul class="feature-list">
            <li v-for="item in features" :key="item">{{ item }}</li>
          </ul>
        </div>
      </el-col>
      <el-col :span="14">
        <ChartCard :title="chartTitle" subtitle="展示阶段干预与管理效果变化" :option="chartOption" />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useUserStore } from '@/store/user'
import ChartCard from '@/components/ChartCard.vue'

const userStore = useUserStore()
const roleCode = computed(() => userStore.userInfo.roleCode || 'parent')
const roleLabel = computed(() => ({ admin: '系统管理视角', doctor: '医生 / 康复师工作台', parent: '家长服务视角' }[roleCode.value]))
const welcomeTitle = computed(() => ({
  admin: '欢迎进入系统管理中心',
  doctor: '欢迎进入健康与康复工作台',
  parent: '欢迎查看儿童健康服务中心'
}[roleCode.value]))
const welcomeDesc = computed(() => ({
  admin: '聚焦用户、档案、统计和日志监管，帮助管理系统整体运行情况。',
  doctor: '聚焦儿童档案、健康记录、康复训练、随访和评估，提升业务处理效率。',
  parent: '聚焦儿童健康情况、训练记录和随访评估结果，帮助家庭及时掌握成长状态。'
}[roleCode.value]))

const cards = computed(() => ({
  admin: [
    { label: '用户总数', value: 5 },
    { label: '在管儿童', value: 3 },
    { label: '本月训练', value: 5 },
    { label: '日志记录', value: 5 }
  ],
  doctor: [
    { label: '负责儿童', value: 3 },
    { label: '健康记录', value: 5 },
    { label: '随访次数', value: 3 },
    { label: '评估记录', value: 3 }
  ],
  parent: [
    { label: '我的孩子', value: 1 },
    { label: '健康记录', value: 2 },
    { label: '训练记录', value: 2 },
    { label: '随访结果', value: 1 }
  ]
}[roleCode.value]))

const shortcuts = computed(() => ({
  admin: [
    { label: '用户管理', path: '/users' },
    { label: '儿童档案', path: '/children' },
    { label: '统计分析', path: '/statistics' },
    { label: '系统日志', path: '/logs' }
  ],
  doctor: [
    { label: '儿童档案', path: '/children' },
    { label: '健康记录', path: '/health' },
    { label: '康复训练', path: '/rehab' },
    { label: '评估记录', path: '/assessments' }
  ],
  parent: [
    { label: '儿童档案', path: '/children' },
    { label: '健康记录', path: '/health' },
    { label: '随访记录', path: '/followups' },
    { label: '评估记录', path: '/assessments' }
  ]
}[roleCode.value]))

const features = computed(() => ({
  admin: ['统一维护用户与角色信息', '查看系统运行日志与统计数据', '保障平台数据规范化运行'],
  doctor: ['维护儿童健康与康复全过程记录', '跟踪阶段性随访与评估结果', '辅助制定个体化干预计划'],
  parent: ['及时查看儿童健康与训练情况', '掌握随访和评估反馈', '配合医生进行日常健康管理']
}[roleCode.value]))

const chartTitle = computed(() => ({ admin: '平台统计趋势', doctor: '评估总分趋势', parent: '近期成长趋势' }[roleCode.value]))
const chartOption = computed(() => ({
  tooltip: { trigger: 'axis' },
  xAxis: { type: 'category', data: ['入组', '阶段一', '阶段二'] },
  yAxis: { type: 'value' },
  series: [{ type: 'line', smooth: true, areaStyle: {}, data: roleCode.value === 'parent' ? [60, 72, 80] : [257, 330, 379], itemStyle: { color: roleCode.value === 'parent' ? '#2f6bff' : '#4fb3a8' } }]
}))
</script>
