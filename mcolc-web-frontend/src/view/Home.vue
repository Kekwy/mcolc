<template>
  <div v-if="completed" class="common-layout">
    <el-container style="height: 100vh; border: 1px solid #eee">
      <el-aside width="200px" style="flex: 0 0 200px;">
        <LeftMenu :userName="userName"/>
      </el-aside>
      <el-container style="flex: 1;">
        <el-header><Header/></el-header>
        <el-main><MainFrame  :health="usrhealth" :hunger="hunger"   :level="level" :currentLevel="currentLevel" :goods="goods"></MainFrame></el-main>
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
      completed:false,
      inventory: "nihao1",
      userName: "...",
      usrhealth : 1.2,
      hunger : 10,
      level : 12,
      currentLevel : 37.23,
      goods: [],
      armor: [],
      offhand : null,
      hotbar : []

    }
  },
  created() {
    if (!window.localStorage.getItem('access_token')) {
      this.$router.replace('/login');
    } else {
      this.$getRequest('/player/details').then(response => {
        let data = response.payload;

        this.userName=data.name;

        this.usrhealth=data.health;

        this.hunger=data.hunger;

        this.level=data.experience.level;

        this.currentLevel=data.experience.progress*100;


        this.goods=data.inventory.main;

        this.armor=data.inventory.armor;

        this.offhand=data.inventory.offHand;

        this.hotbar=data.inventory.hotBar;


        this.completed=true;

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