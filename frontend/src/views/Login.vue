<template>
  <div class="login-page">
    <div class="login-hero">
      <div class="hero-badge">Autism Health Platform</div>
      <h1>孤独症儿童健康信息管理系统</h1>
      <p>聚焦儿童档案、健康监测、康复训练、随访评估与统计分析的一体化管理平台。</p>
      <div class="hero-features">
        <span>电子档案管理</span>
        <span>多角色协同</span>
        <span>趋势统计分析</span>
      </div>
    </div>
    <el-card class="login-card">
      <template #header>
        <div class="login-card-header">
          <span>系统登录</span>
          <el-tag type="primary" effect="light">安全访问</el-tag>
        </div>
      </template>
      <el-form :model="form" @submit.prevent>
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password" show-password placeholder="请输入密码" />
        </el-form-item>
        <el-button type="primary" class="w-full" @click="handleLogin">登录系统</el-button>
        <div class="login-tip">默认账号：admin / doctor01 / parent01，密码统一为 123456</div>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { loginApi } from '@/api/auth'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()
const form = reactive({ username: 'admin', password: '123456' })

const handleLogin = async () => {
  const { data } = await loginApi(form)
  userStore.setLoginInfo(data.token, data)
  ElMessage.success('登录成功')
  router.push('/dashboard')
}
</script>
