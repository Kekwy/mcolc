import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "@/store";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'font-awesome/css/font-awesome.css';

import axios from 'axios'
import {deleteRequest, getRequest, postRequest, putRequest} from "@/util/api";
import {initMenu} from "@/util/menus";

Vue.prototype.$axios = axios

// 是否在浏览器控制台提示开发环境
Vue.config.productionTip = false

Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;

Vue.use(ElementUI);

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app') // 手动挂载

router.beforeEach((to, from, next) => {
    if (window.sessionStorage.getItem('tokenStr')) {
        initMenu(router, store);
        // 判断用户信息是否存在
        if (!window.sessionStorage.getItem('user')) {
            return getRequest('/admin/info').then(resp=>{
                // 存入用户信息
                window.sessionStorage.setItem('user', JSON.stringify(resp));
                next();
            })
        }
        next();
    } else {
        // 执行跳转
        next();
        // 中断当前跳转
        // next(false);
        // next('/');
        // next(error);
    }
});