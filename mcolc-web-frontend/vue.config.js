// 本文件更改之后需要重启项目

const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})

let proxyObj = {}

// 定义代理
proxyObj['/api'] = {
    // websocket
    ws: false,

    // 将请求头中的 host 修改为目标端口
    changeOrigin: true,
    // [查资料，待补充]，不重写请求地址
    pathRewrite: { // pacthReWrite......
        '^/api': '/'
    },
    // 目标地址
    target: 'http://172.27.156.143:8081'

}

// 通过 node.js 实现转发
module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj,
        allowedHosts: ['stcos.kekwy.com', 'mcolc.kekwy.com'],
    }
}