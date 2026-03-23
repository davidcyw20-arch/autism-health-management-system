<template>
  <div class="login-page">
    <div class="login-hero">
      <div class="hero-badge">Autism Health Platform</div>
      <div class="login-hero-panel">
        <div class="login-hero-copy">
          <h1>孤独症儿童健康信息管理系统</h1>
          <p>围绕儿童建档、健康监测、康复训练、随访评估与家庭协同，构建更专业、更温和、更可信赖的健康管理工作台。</p>
        </div>
        <div class="hero-features">
          <span>电子档案闭环管理</span>
          <span>医生 / 康复师协同</span>
          <span>评估趋势可视化</span>
        </div>
        <div class="login-hero-metrics">
          <div class="hero-metric-card">
            <strong>12+</strong>
            <span>核心业务模块</span>
          </div>
          <div class="hero-metric-card">
            <strong>3</strong>
            <span>角色权限体系</span>
          </div>
          <div class="hero-metric-card">
            <strong>24h</strong>
            <span>健康数据可追踪</span>
          </div>
        </div>
        <div class="login-hero-points">
          <div class="hero-point">
            <span class="point-icon">01</span>
            <div>
              <h3>儿童健康全景视图</h3>
              <p>统一承载儿童档案、诊断结果、健康记录与评估结果，减少信息分散。</p>
            </div>
          </div>
          <div class="hero-point">
            <span class="point-icon">02</span>
            <div>
              <h3>康复过程连续记录</h3>
              <p>支持训练、随访、评估记录闭环沉淀，便于长期观察儿童变化趋势。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="login-side">
      <el-card class="login-card">
        <template #header>
          <div class="login-card-header">
            <div class="login-card-heading">
              <div class="login-card-brand">
                <span class="login-card-brand-icon">{{ activeTab === 'login' ? 'AH' : 'RG' }}</span>
                <div class="login-card-brand-text">
                  <span>Autism Health Platform</span>
                  <small>Secure Access Portal</small>
                </div>
              </div>
              <div class="login-card-title">{{ activeTab === 'login' ? '欢迎登录' : '创建账号' }}</div>
              <div class="login-card-subtitle">
                {{ activeTab === 'login' ? '请输入账号信息以继续访问系统。' : '注册后将以家长身份进入平台。' }}
              </div>
            </div>
            <el-tag type="primary" effect="light">安全访问</el-tag>
          </div>
        </template>

        <el-tabs v-model="activeTab" stretch class="login-tabs">
          <el-tab-pane label="登录" name="login">
            <el-form :model="loginForm" @submit.prevent class="login-form">
              <el-form-item label="用户名">
                <el-input v-model="loginForm.username" placeholder="请输入用户名" />
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="loginForm.password" type="password" show-password placeholder="请输入密码" />
              </el-form-item>
              <el-button type="primary" class="w-full login-submit" @click="handleLogin">登录系统</el-button>
              <div class="login-tip">测试账号：admin / doctor01 / parent01，初始密码统一为 123456</div>
              <div class="login-helper-text">建议使用管理员账号首次登录检查系统功能是否正常。</div>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="注册" name="register">
            <el-form :model="registerForm" @submit.prevent class="login-form">
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
              <el-button type="primary" class="w-full login-submit" @click="handleRegister">注册账号</el-button>
              <div class="login-tip">注册成功后，系统会自动分配为家长角色。</div>
              <div class="login-helper-text">请使用常用手机号注册，便于后续家长信息关联与系统通知。</div>
            </el-form>
          </el-tab-pane>
        </el-tabs>
        <div class="login-card-footer">
          <span>Medical Health Management Suite</span>
          <span>Version 1.0</span>
        </div>
      </el-card>
    </div>
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
const loginForm = reactive({ username: '', password: '' })
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
