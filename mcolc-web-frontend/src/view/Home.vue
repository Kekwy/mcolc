<template>
  <div class="common-layout">
    <el-container style="height: 100vh; border: 1px solid #eee">
      <el-aside width="200px" style="flex: 0 0 200px;">
        <LeftMenu />
      </el-aside>
      <el-container style="flex: 1;">
        <el-header><Header/></el-header>
        <el-main><MainFrame></MainFrame></el-main>
        <el-footer><Footer/></el-footer>
      </el-container>
    </el-container>
  </div>
</template>


<script>
export default {
  name: "Home",
  components:
      { LeftMenu,Header,MainFrame,Footer },
  data() {
    return {
      inventory: "nihao1",
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
import LeftMenu from '@/components/Leftsidemenu'
import Header from '@/components/Header'
import MainFrame from '@/components/MainFrame'
import Footer from '@/components/Footer'

</script>

<style scoped>
.el-aside {
  color: #333;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
</style>