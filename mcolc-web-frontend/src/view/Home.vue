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
    if (!window.localStorage.getItem('access_token')) {
      this.$router.replace('/login');
    } else {
      this.$getRequest('/item/inventory').then(response => {
        this.inventory = response.payload;
      });
    }
  }
}
</script>

<style scoped>

</style>