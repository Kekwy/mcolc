import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/view/Login.vue";
import LoginSuccess from "@/view/LoginSuccess.vue";

// 安装路由
Vue.use(VueRouter)

// 定义路由
const routes = [
    {
        path: '/login',
        name: 'Login',
        // 路由的组件
        component: Login,
    },
    {
        path: '/login-success/:authName',
        name: 'LoginSuccess',
        component: LoginSuccess,
    },
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router