<template>
  <div>{{ this.inventory }}</div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      inventory: null,
    }
  },
  mounted() {
    if (!window.localStorage.getItem('accessToken')) {
      this.$router.replace('/login');
    } else {
      this.$getRequest('/user/inventory').then(response => {
        this.inventory = response.payload;
      });
    }
  }
}
</script>

<style scoped>

</style>