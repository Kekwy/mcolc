<template>
  <!-- 设置随机背景图片 -->
  <div class="common-layout" id="building" :style="{ backgroundImage: 'url(\'' + this.backgroundImageUrl + '\')' }">
    <el-card class="welcome-card" shadow="always">
      <div slot="header" class="card-header">
        <p class="welcome-text">Welcome to MCOLC!</p>
      </div>
      <div class="icon-container">
        <el-tooltip content="使用微软账号登录" placement=bottom>
          <img decoding="async" class="icon" src="/icons/microsoft.svg" alt=""
               @click="toMicrosoft">
        </el-tooltip>
        <el-tooltip content="使用NMO皮肤站登录" placement=bottom>
          <img decoding="async"
               src="https://www.nmo.net.cn/wp-content/uploads/2023/12/nmo_logo-1024x470.png" alt="" class="icon1"
               @click="toNmoSkin">
        </el-tooltip>
      </div>
    </el-card>
    <el-dialog
        title="提示"
        :visible.sync="showPopup"
        width="30%"
        :before-close="handleClose">
      <iframe ref="popupIframe" width="600" height="400"></iframe>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showPopup = false">取 消</el-button>
        <el-button type="primary" @click="showPopup = false">确 定</el-button>
      </span>
    </el-dialog>
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
    // const popup = window.open(this.AUTH_URL);


    // 都是对 dom 的操作而不是浏览器窗口，跨域失败，跨域失败，全是跨域失败！
    // popup.close();
    // popup.setInterval(()=>{
    //
    // });
    // window.document.domain = 'www.baidu.com';
    // this.$message.info(popup.location.href);
    // const intervalId = window.setInterval(() => {
    //   // // const popupUrl = popup.location.href;
    //   // const popupUrl = popup.document.URL;
    //   // this.$message.info(popupUrl);
    //   // if (popupUrl.includes('code')) {
    //   //   popup.close()
    //   // }
    //   popup.addEventListener('resize', () => {
    //     this.$message.info('fadsf');
    //   });
    // }, 1000);
    //
    // 给新窗口添加 load 事件监听器
    // if (!popup.closed) {
    //   this.$message.info('sadfsad');
    // }
    // popup.addEventListener('pageshow', ()=>{
    //   // window.close();
    //   popup.close();
    //   console.log('sadfdsaf')
    //   // // 实时获取新窗口的 URL
    //   // const popupUrl = popup.location.href;
    //   // this.$message.info('弹窗 URL：' + popupUrl);
    //   //
    //   // // 在这里可以根据弹窗的 URL 进行相应的操作
    //   // // 例如根据特定的 URL 触发关闭窗口
    //   // if (popupUrl.includes('closePopup')) {
    //   //   popup.close(); // 关闭窗口
    //   // }
    // }, true);


    if (window.localStorage.getItem('accessToken')) {
      this.$router.replace('/');
    }
    // Message.success(this.backgroundImageUrl);
  },
  data() {
    return {
      backgroundImageIndexBound: [0, 2],
      showPopup: false,
      AUTH_URL: 'https://login.live.com/oauth20_authorize.srf' +
                ' ?client_id=00000000402b5328' +
                ' &response_type=code' +
                ' &scope=service%3A%3Auser.auth.xboxlive.com%3A%3AMBI_SSL' +
                ' &redirect_uri=https%3A%2F%2Fwww.baidu.com'//https%3A%2F%2Flogin.live.com%2Foauth20_desktop.srf'
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
    toNmoSkin() {
      this.$getRequest('/auth/nmo-skin').then(response => {
        if (response.code === 200) {
          window.location.href = response.payload.redirect;
        } else if (response.code === 404) {
          Message.error(response.message);
        }
      });
    },
    toMicrosoft() {
      // this.showPopup = true;
      // this.$refs.popupIframe.src = 'https://login.live.com/oauth20_authorize.srf\n' +
      //     ' ?client_id=00000000402b5328\n' +
      //     ' &response_type=code\n' +
      //     ' &scope=service%3A%3Auser.auth.xboxlive.com%3A%3AMBI_SSL\n' +
      //     ' &redirect_uri=https%3A%2F%2Flogin.live.com%2Foauth20_desktop.srf';
      this.$getRequest('/auth').then(response => {
        window.location.href = response.payload.redirect;
      });
    },
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
  height: 150px;
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

.welcome-text {
  margin-top: -17px;
}

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
  height: 10px;
  display: flex;
  justify-content: center;
}


.icon-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 90px;
}

.icon {
  width: 36px;
  object-fit: contain;

  margin: 0 10px;
}

.icon1 {
  width: 60px;
  object-fit: contain;

  margin: 0 10px;
}

</style>