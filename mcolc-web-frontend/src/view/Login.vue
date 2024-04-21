<template>
  <!-- 设置随机背景图片 -->
  <div class="common-layout" id="building" :style="{ backgroundImage: 'url(\'' + this.backgroundImageUrl + '\')' }">
    <el-card class="welcome-card" shadow="always">
      <div slot="header" class="card-header">Welcome to MCOLC!</div>
      <div>
        <el-tooltip content="使用微软账号登录" placement=bottom>
          <font-awesome-icon :icon="['fas', 'square']" />
          <i class="fab fa-github fa-lg inner-icon"></i>
        </el-tooltip>
        <el-tooltip content="使用NMO皮肤站登录" placement=bottom>
          <img class="icon" src="github_logo.png" alt="NMO_Skin">
        </el-tooltip>
      </div>
    </el-card>
  </div>
  <!--    <el-container>-->
  <!--      <el-header>Header</el-header>-->
  <!--      <el-main>-->
  <!--        <el-card style="width: 480px" shadow="always">Always</el-card>-->
  <!--      </el-main>-->
  <!--      <el-footer>Footer</el-footer>-->
  <!--    </el-container>-->

</template>

<script>
import {Message} from "element-ui";

export default {
  name: "Login",
  mounted() {
    // Message.success(this.backgroundImageUrl);
  },
  data() {
    return {
      backgroundImageIndexBound: [0, 2],
    }
  },
  computed: {
    backgroundImageUrl() {
      return '/images/background' +
          this.random(
              this.backgroundImageIndexBound[0],
              this.backgroundImageIndexBound[1])
          + '.png';
    }
  },
  methods: {
    random(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    }
  }
}
</script>

<style scoped>

.welcome-card {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 15px;
  background-clip: padding-box;
  /*margin: 180px auto;*/
  width: 450px;
  height: 200px;
  padding: 15px 35px 15px 35px;
  border: 1px solid #eaeaea;
  font-family: "Minecraft", serif;
  /*font-size: 2.1rem;*/
  font-weight: bold;
  text-align: center;
  /*color: #495057;*/
  align-items: center;
  background-color: rgba(255, 255, 255, 0.5);
}

login {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
}

.centered-card {

}

body {
  margin: 0;
  padding: 0;
  border: 0;
}

#logo a:hover {
  color: #42a5f5;
}

#building {
  width: 100%;
  height: 100%;
  position: fixed;
  background-repeat: no-repeat;
  /* 使背景图片居中并保持比例 */
  background-size: cover;
  background-position: center;
}

/*.welcome-card {*/
/*  max-width: 600px;*/
/*  width: 100%;*/
/*}*/

.card-header {
  font-size: 30px;
  font-weight: bold;
  text-align: center;
}


/*.icon-container {*/
/*  display: flex;*/
/*  justify-content: center;*/
/*  align-items: center;*/
/*}*/

/*.icon {*/
/*  width: 40px;*/
/*  height: 40px;*/
/*  margin: 0 10px;*/
/*}*/


</style>