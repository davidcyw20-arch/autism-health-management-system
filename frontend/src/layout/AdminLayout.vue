<template>
  <div class="layout">
    <aside class="sidebar">
      <div class="sidebar-inner">
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
          <div class="role-desc">{{ roleDescription }}</div>
        </div>

        <div class="nav-section-title">功能导航</div>
        <el-menu class="sidebar-menu" :default-active="$route.path" router>
          <el-menu-item v-for="item in visibleMenus" :key="item.path" :index="item.path">
            <span class="menu-dot" />
            <span>{{ item.label }}</span>
          </el-menu-item>
        </el-menu>
      </div>
    </aside>

    <main class="main">
      <header class="header">
        <div class="header-main">
          <div class="header-badge">医疗健康管理平台</div>
          <div class="header-title-row">
            <div>
              <div class="header-title">孤独症儿童健康信息管理系统</div>
              <div class="header-subtitle">多角色协同 · 健康档案 · 康复训练 · 随访评估</div>
            </div>
          </div>
        </div>

        <div class="header-actions">
          <div class="status-chip">
            <span class="status-dot" />
            <span>系统运行中</span>
          </div>
          <el-tag :type="roleTagType" effect="light" round>{{ roleLabel }}</el-tag>
          <el-button class="header-logout" @click="logout">退出登录</el-button>
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
  { path: '/parents', label: '家长信息', roles: ['admin', 'doctor', 'parent'] },
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
const roleDescription = computed(() => ({
  admin: '负责平台配置、用户权限与运营监管。',
  doctor: '聚焦儿童健康、训练、评估与随访管理。',
  parent: '查看儿童成长记录并协同日常健康管理。'
}[roleCode.value] || '查看儿童成长记录并协同日常健康管理。'))
const roleTagType = computed(() => ({ admin: 'danger', doctor: 'primary', parent: 'success' }[roleCode.value] || 'success'))

const logout = () => {
  userStore.logout()
  router.push('/login')
}
</script>
