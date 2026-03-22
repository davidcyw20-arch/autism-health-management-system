<template>
  <div class="login-page">
    <div class="login-hero">
      <h1>孤独症儿童健康信息管理系统</h1>
      <p>面向本科毕业设计展示的现代化医疗健康管理平台。</p>
    </div>
    <el-card class="login-card">
      <template #header>系统登录</template>
      <el-form :model="form" @submit.prevent>
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password" show-password placeholder="请输入密码" />
        </el-form-item>
        <el-button type="primary" class="w-full" @click="handleLogin">登录</el-button>
        <div class="login-tip">测试账号：admin / doctor01 / parent01，密码统一为 123456</div>
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
