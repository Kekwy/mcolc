<template>
  <div>Login Success!</div>
</template>

<script>

export default {
  name: "LoginSuccess",
  mounted() {
    let authName = this.$route.params.authName;
    this.$getRequest('/auth/' + authName + '/token?code=' + this.$route.query.code).then(response => {
      if (response.payload.token) {
        window.localStorage.setItem('accessToken', response.payload.token);
        window.localStorage.setItem('authService', authName);
      }
      this.$router.replace('/');
    });
    // // 根据参数进行路由跳转
    // if (this.code === 'xxx') {
    //   this.$router.push('/some/path')
    // } else {
    //   this.$router.push('/another/path')
    // }
  }
}
</script>

<style scoped>

</style>