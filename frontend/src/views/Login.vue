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
          <span>{{ activeTab === 'login' ? '系统登录' : '用户注册' }}</span>
          <el-tag type="primary" effect="light">安全访问</el-tag>
        </div>
      </template>

      <el-tabs v-model="activeTab" stretch>
        <el-tab-pane label="登录" name="login">
          <el-form :model="loginForm" @submit.prevent>
            <el-form-item label="用户名">
              <el-input v-model="loginForm.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="loginForm.password" type="password" show-password placeholder="请输入密码" />
            </el-form-item>
            <el-button type="primary" class="w-full" @click="handleLogin">登录系统</el-button>
            <div class="login-tip">默认账号：admin / doctor01 / parent01，密码统一为 123456</div>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="register">
          <el-form :model="registerForm" @submit.prevent>
            <el-form-item label="用户名">
              <el-input v-model="registerForm.username" placeholder="请输入用户名" />
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="registerForm.password" type="password" show-password placeholder="请输入密码" />
            </el-form-item>
            <el-form-item label="真实姓名">
              <el-input v-model="registerForm.realName" placeholder="请输入真实姓名" />
            </el-form-item>
            <el-form-item label="手机号">
              <el-input v-model="registerForm.phone" placeholder="请输入手机号" />
            </el-form-item>
            <el-button type="primary" class="w-full" @click="handleRegister">注册账号</el-button>
            <div class="login-tip">注册成功后将以家长角色接入系统。</div>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { loginApi, registerApi } from '@/api/auth'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()
const activeTab = ref('login')
const loginForm = reactive({ username: 'admin', password: '123456' })
const registerForm = reactive({ username: '', password: '', realName: '', phone: '' })

const handleLogin = async () => {
  const { data } = await loginApi(loginForm)
  userStore.setLoginInfo(data.token, data)
  ElMessage.success('登录成功')
  router.push('/dashboard')
}

const handleRegister = async () => {
  await registerApi(registerForm)
  ElMessage.success('注册成功，请使用新账号登录')
  loginForm.username = registerForm.username
  loginForm.password = registerForm.password
  activeTab.value = 'login'
}
</script>
