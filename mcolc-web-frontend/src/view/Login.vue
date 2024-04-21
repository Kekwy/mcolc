<template>
<!-- 设置随机背景图片 -->
  <div class="common-layout" id="building" :style="{ backgroundImage: 'url(\'' + this.backgroundImageUrl + '\')' }">
    <el-container>
      <el-header>Header</el-header>
      <el-main>Main</el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
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
login {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
}

body {
  margin: 0;
  padding: 0;
  border: 0
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

</style>