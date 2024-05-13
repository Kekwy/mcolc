# mcolc
> Mincraft Online Community.
>
> 为你的Minecraft服务器一键部署定制化的在线社区平台。

## 分工

可弟WZ：架构设计、框架搭建、前端开发、mod 开发。

小黄牛：后端开发、前端开发。

## 问题记录

gradle

需要留意 resources/fabric.mod.json 中的配置信息。

blessing skin 皮肤站的 oauth2 登录问题 https://blessing.netlify.app/api/oauth.html

https://docs.spring.io/spring-security/site/docs/5.2.12.RELEASE/reference/html/oauth2.html

https://www.cnblogs.com/xiaogblog/p/16718191.html spring 打包时的配置文件问题

作为Http响应体中的对象，其get方法必须有get前缀。

后端 oauth2 认证流程待优化。

https://blog.csdn.net/yiifaa/article/details/77939282 resttemplate 传输表单

227ed512-1401-316b-824a-436671ad6fae

// TODO：需要处理第三方登录的情况。

【错误的】需要使用 JSON 进行序列化的属性对应的getter和setter方法必须要包含get与set前缀

问题记录：

MC的oauth认证需要用指定的client id，详见wiki；

对于回调url确定的oauth应用，想要在前端项目中获取其code参数，可以考虑使用前端项目管理一个独立的web容器（弹窗），并监听其加载完成事件。

// TODO: 想办法增强鲁棒性


## 参考资料

https://blog.biduang.cn/archives/60/

https://learn.microsoft.com/zh-cn/entra/identity-platform/v2-oauth2-auth-code-flow

https://minecraft.fandom.com/zh/wiki/%E6%95%99%E7%A8%8B/%E7%BC%96%E5%86%99%E5%90%AF%E5%8A%A8%E5%99%A8

https://zhuanlan.zhihu.com/p/344830045

https://www.bilibili.com/read/cv8722213/

https://www.curseforge.com/minecraft/mc-mods/iconexporter 导出方块图标