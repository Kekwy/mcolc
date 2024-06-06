<template>
  <div style="height: 100%; background-color: #F9FBFA;">
    <div style="display: flex; justify-content: center; align-items: center; width: 100%; height: 200px; background-color: #F9FBFA;">
      <div style="width: 100px; height: 100px; background-color: #F9FBFA; padding: 10px; border-radius: 50%; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); display: flex; justify-content: center; align-items: center;">
        <AppAvatar/>
      </div>
    </div>
    
    

    <!-- 用户头像和用户名 -->
    <div style="display: flex; align-items: center; padding: 20px;">
      <div style="margin-right: 15px;">
        <AuthImg  :authSrc="avatarUrl" style="width: 40px; height: 40px;"/>
        <!-- <el-avatar :src="avatarUrl"></el-avatar> -->
      </div>
      <div class="like" style="color: #000000;">{{this.userName}}</div>
    </div>

    <el-menu
    default-active="1"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    background-color="#F9FBFA"
    text-color="#000000"
    style="flex: 1;"
  >
    <el-menu-item index="1" @click="activateMenuItem(1)" :class="{ 'active-menu-item': activeIndex == 1 }">
      <i class="el-icon-menu"></i>
      <span slot="title">角色</span>
    </el-menu-item>

    <el-menu-item index="2" @click="activateMenuItem(2)" :class="{ 'active-menu-item': activeIndex == 2 }">
      <i class="el-icon-document"></i>
      <span slot="title">库存</span>
    </el-menu-item>
  </el-menu>


  </div>
</template>




<script>

  export default {
    name: "LeftMenu",
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      activateMenuItem(index) {

        this.activeIndex = index.toString(); // 更新激活的菜单项
      },
      handleOpen() {
        // 处理菜单展开事件
      },
      handleClose() {
        // 处理菜单关闭事件
      }
    },
    data() {
      const avatarUrl='/api/player/avatar';
      return {
        avatarUrl: avatarUrl,
        activeIndex: '1'
      }
    },
    computed: {
    },
    components: { 
      AppAvatar,
      AuthImg   
    },

    props: {
      userName: {
        type: String,
        default: "..." // 默认
      }
    },
  }


import AppAvatar from "@/components/AppAvatar"
import AuthImg  from "@/components/AuthImg.vue"
</script>



<style scoped>
.like {
  font-size: 16px;
}

.active-menu-item {
  background-color: #007bff !important;
  color: #ffffff !important; /* 悬停状态下文字颜色为白色 */
}
</style>