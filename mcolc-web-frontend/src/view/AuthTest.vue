<template>
  <div>
    <iframe ref="authFrame" :src="authUrl" @load="handleFrameLoad" sandbox="allow-forms allow-scripts allow-same-origin allow-popups"></iframe>
  </div>
</template>

<script>
export default {
  name: "AuthTest",
  data() {
    return {
      authUrl: 'https://login.live.com/oauth20_authorize.srf\n' +
          ' ?client_id=00000000402b5328\n' +
          ' &response_type=code\n' +
          ' &scope=service%3A%3Auser.auth.xboxlive.com%3A%3AMBI_SSL\n' +
          ' &redirect_uri=https%3A%2F%2Flogin.live.com%2Foauth20_desktop.srf',
    };
  },
  methods: {
    handleFrameLoad() {
      // 监听 iframe 中 URL 的变化
      const frameUrl = this.$refs.authFrame.contentWindow.location.href;

      // 检查 URL 中是否包含授权码
      if (frameUrl.includes('code=')) {
        const codeIndex = frameUrl.indexOf('code=');
        const code = frameUrl.substring(codeIndex + 5); // 提取授权码
        // 在这里处理授权码，例如发送到后端服务器进行进一步处理
        this.$message.info(code)
        this.$router.replace('/');
      }
    },
  },
};
</script>

<style scoped>
iframe {
  width: 100%;
  height: 100%;
  border: none; /* 去掉 iframe 边框 */
}
</style>