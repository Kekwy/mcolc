import axios from "axios";
import {Message} from "element-ui";

// 导入路由
import router from "../router";

// 请求拦截器
axios.interceptors.request.use(config => {
    // 如果存在 token，请求携带这个 token
    if (window.sessionStorage.getItem('tokenStr')) {
        config.headers['Authorization'] = window.sessionStorage.getItem('tokenStr');
    }
    return config;
}, error => {
    console.log(error);
})

// 进行响应拦截对响应进行统一的处理
// success 表示成功访问到后端的接口，并不一定表示目标操作成功
axios.interceptors.response.use(success => {
    // 业务逻辑错误
    if (success.status && success.status === 200) {
        // json 对象（success.data）中包含：code、message、object
        if (success.data.code === 500 || success.data.code === 401 || success.data.code === 403) {
            Message.error({message: success.data.message});
            return;
        }
        // 判断后端是否发来提示信息
        if (success.data.message) {
            Message.success({message: success.data.message});
        }
    }
    return success.data;
    // 后端接口没有成功调用
}, error => {
    if (error.response.code === 504 || error.response.code === 404) {
        Message.error({message: '核心已转储doge'});
    } else if (error.response.code === 403) {
        Message.error({message: '权限不足，请联系管理员！'});
    } else if (error.response.code === 401) {
        Message.error({message: '尚未登录，请先登录。'});
        // 通过路由跳转至登录页
        router.replace('/').then(r => {
        });
    } else {
        if (error.response.data.message) {
            Message.error({message: error.response.data.message});
        } else {
            Message.error({message: '未知错误！'});
        }
    }
});

// post 请求的前置路径
let base = '';

// 封装请求
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
    });
}

export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params,
    });
}

export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        data: params,
    });
}

export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        data: params,
    });
}