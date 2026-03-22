import { createRouter, createWebHistory } from 'vue-router'
const routes = [
  { path: '/login', component: () => import('@/views/Login.vue') },
  {
    path: '/',
    component: () => import('@/layout/AdminLayout.vue'),
    redirect: '/dashboard',
    children: [
      { path: 'dashboard', component: () => import('@/views/Dashboard.vue') },
      { path: 'children', component: () => import('@/views/child/ChildList.vue') },
      { path: 'parents', component: () => import('@/views/parent/ParentList.vue') },
      { path: 'diagnosis', component: () => import('@/views/diagnosis/DiagnosisList.vue') },
      { path: 'health', component: () => import('@/views/health/HealthRecordList.vue') },
      { path: 'rehab', component: () => import('@/views/rehab/RehabList.vue') },
      { path: 'followups', component: () => import('@/views/followup/FollowupList.vue') },
      { path: 'assessments', component: () => import('@/views/assessment/AssessmentList.vue') },
      { path: 'statistics', component: () => import('@/views/statistics/StatisticsView.vue') },
      { path: 'users', component: () => import('@/views/user/UserList.vue') },
      { path: 'logs', component: () => import('@/views/log/LogList.vue') }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.path !== '/login' && !token) {
    next('/login')
    return
  }
  next()
})

export default router
