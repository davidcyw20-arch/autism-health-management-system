<template>
  <div class="layout">
    <aside class="sidebar">
      <div class="logo-wrap">
        <div class="logo-icon">AH</div>
        <div>
          <div class="logo">孤独症健康管理</div>
          <div class="logo-subtitle">Autism Health Platform</div>
        </div>
      </div>
      <div class="role-panel">
        <div class="role-title">当前身份</div>
        <div class="role-name">{{ roleLabel }}</div>
      </div>
      <el-menu :default-active="$route.path" router>
        <el-menu-item v-for="item in visibleMenus" :key="item.path" :index="item.path">{{ item.label }}</el-menu-item>
      </el-menu>
    </aside>
    <main class="main">
      <header class="header">
        <div>
          <div class="header-title">孤独症儿童健康信息管理系统</div>
          <div class="header-subtitle">多角色协同 · 健康档案 · 康复训练 · 随访评估</div>
        </div>
        <div class="header-actions">
          <el-tag :type="roleTagType" effect="light">{{ roleLabel }}</el-tag>
          <el-tag type="success" effect="light">运行中</el-tag>
          <el-button type="primary" plain @click="logout">退出登录</el-button>
        </div>
      </header>
      <section class="content">
        <router-view />
      </section>
    </main>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()
const roleCode = computed(() => userStore.userInfo.roleCode || 'parent')

const allMenus = [
  { path: '/dashboard', label: '首页仪表盘', roles: ['admin', 'doctor', 'parent'] },
  { path: '/children', label: '儿童档案', roles: ['admin', 'doctor', 'parent'] },
  { path: '/parents', label: '家长信息', roles: ['admin', 'doctor'] },
  { path: '/diagnosis', label: '诊断信息', roles: ['admin', 'doctor'] },
  { path: '/health', label: '健康记录', roles: ['admin', 'doctor', 'parent'] },
  { path: '/rehab', label: '康复训练', roles: ['admin', 'doctor', 'parent'] },
  { path: '/followups', label: '随访记录', roles: ['admin', 'doctor', 'parent'] },
  { path: '/assessments', label: '评估记录', roles: ['admin', 'doctor', 'parent'] },
  { path: '/statistics', label: '统计分析', roles: ['admin', 'doctor'] },
  { path: '/users', label: '用户管理', roles: ['admin'] },
  { path: '/logs', label: '系统日志', roles: ['admin'] }
]

const visibleMenus = computed(() => allMenus.filter(item => item.roles.includes(roleCode.value)))
const roleLabel = computed(() => ({ admin: '系统管理员', doctor: '医生 / 康复师', parent: '家长用户' }[roleCode.value] || '家长用户'))
const roleTagType = computed(() => ({ admin: 'danger', doctor: 'primary', parent: 'success' }[roleCode.value] || 'success'))

const logout = () => {
  userStore.logout()
  router.push('/login')
}
</script>
