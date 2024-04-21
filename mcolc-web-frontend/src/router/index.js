import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/view/Login.vue";

// 安装路由
Vue.use(VueRouter)

// 定义路由
const routes = [
    {
        path: '/',
        name: 'Login',
        // 路由的组件
        component: Login,
    },
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router