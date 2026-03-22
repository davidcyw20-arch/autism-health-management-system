import { createRouter, createWebHistory } from 'vue-router'

const roleRouteMeta = roles => ({ roles })

const routes = [
  { path: '/login', component: () => import('@/views/Login.vue') },
  {
    path: '/',
    component: () => import('@/layout/AdminLayout.vue'),
    redirect: '/dashboard',
    children: [
      { path: 'dashboard', component: () => import('@/views/Dashboard.vue'), meta: roleRouteMeta(['admin', 'doctor', 'parent']) },
      { path: 'children', component: () => import('@/views/child/ChildList.vue'), meta: roleRouteMeta(['admin', 'doctor', 'parent']) },
      { path: 'parents', component: () => import('@/views/parent/ParentList.vue'), meta: roleRouteMeta(['admin', 'doctor']) },
      { path: 'diagnosis', component: () => import('@/views/diagnosis/DiagnosisList.vue'), meta: roleRouteMeta(['admin', 'doctor']) },
      { path: 'health', component: () => import('@/views/health/HealthRecordList.vue'), meta: roleRouteMeta(['admin', 'doctor', 'parent']) },
      { path: 'rehab', component: () => import('@/views/rehab/RehabList.vue'), meta: roleRouteMeta(['admin', 'doctor', 'parent']) },
      { path: 'followups', component: () => import('@/views/followup/FollowupList.vue'), meta: roleRouteMeta(['admin', 'doctor', 'parent']) },
      { path: 'assessments', component: () => import('@/views/assessment/AssessmentList.vue'), meta: roleRouteMeta(['admin', 'doctor', 'parent']) },
      { path: 'statistics', component: () => import('@/views/statistics/StatisticsView.vue'), meta: roleRouteMeta(['admin', 'doctor']) },
      { path: 'users', component: () => import('@/views/user/UserList.vue'), meta: roleRouteMeta(['admin']) },
      { path: 'logs', component: () => import('@/views/log/LogList.vue'), meta: roleRouteMeta(['admin']) }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  const userInfo = JSON.parse(localStorage.getItem('userInfo') || '{}')
  const roleCode = userInfo.roleCode || 'parent'

  if (to.path !== '/login' && !token) {
    next('/login')
    return
  }

  const allowedRoles = to.meta?.roles
  if (to.path !== '/login' && Array.isArray(allowedRoles) && !allowedRoles.includes(roleCode)) {
    next('/dashboard')
    return
  }

  next()
})

export default router
