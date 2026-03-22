<template>
  <div class="layout">
    <aside class="sidebar">
      <div class="logo">孤独症健康管理</div>
      <el-menu :default-active="$route.path" router>
        <el-menu-item index="/dashboard">首页仪表盘</el-menu-item>
        <el-menu-item index="/children">儿童档案</el-menu-item>
        <el-menu-item index="/parents">家长信息</el-menu-item>
        <el-menu-item index="/diagnosis">诊断信息</el-menu-item>
        <el-menu-item index="/health">健康记录</el-menu-item>
        <el-menu-item index="/rehab">康复训练</el-menu-item>
        <el-menu-item index="/followups">随访记录</el-menu-item>
        <el-menu-item index="/assessments">评估记录</el-menu-item>
        <el-menu-item index="/statistics">统计分析</el-menu-item>
        <el-menu-item index="/users">用户管理</el-menu-item>
        <el-menu-item index="/logs">系统日志</el-menu-item>
      </el-menu>
    </aside>
    <main class="main">
      <header class="header">
        <div>欢迎使用孤独症儿童健康信息管理系统</div>
        <el-button type="primary" plain @click="logout">退出登录</el-button>
      </header>
      <section class="content">
        <router-view />
      </section>
    </main>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()

const logout = () => {
  userStore.logout()
  router.push('/login')
}
</script>
