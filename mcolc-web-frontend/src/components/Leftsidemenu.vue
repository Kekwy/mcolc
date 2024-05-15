<template>
  <div style="height: 100%; background-color: #fce4ec;">
    <div style="display: flex; justify-content: center; align-items: center; width: 100%; height: 200px; background-color: #f3e5f5;">
      <div style="width: 100px; height: 100px; background-color: #fff; padding: 10px; border-radius: 50%; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); display: flex; justify-content: center; align-items: center;">
        <AppAvatar/>
      </div>
    </div>
    
    

    <!-- 用户头像和用户名 -->
    <div style="display: flex; align-items: center; padding: 20px;">
      <div style="margin-right: 15px;">
        <el-avatar :src="avatarUrl"></el-avatar>
      </div>
      <div class="like" style="color: #880e4f;">{{this.userName}}</div>
    </div>

     <!-- 主体菜单 -->
     <el-menu
     default-active="2"
     class="el-menu-vertical-demo"
     @open="handleOpen"
     @close="handleClose"
     background-color="#ad1457"
     text-color="#fff"
     active-text-color="#ff5252"
     style="flex: 1;"
   >
     <el-menu-item index="1">
       <i class="el-icon-menu"></i>
       <span slot="title">角色</span>
     </el-menu-item>

     <el-menu-item index="2">
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
      }
    },
    data() {
      const avatarUrl='/images/avatar.png';
      return {
        avatarUrl: avatarUrl
      }
    },
    computed: {
    },
    components: { 
      AppAvatar },

    props: {
      userName: {
        type: String,
        default: "..." // 默认
      }
    },
  }


import AppAvatar from "@/components/AppAvatar"
</script>



<style scoped>
.like {
  font-size: 18px;
}


</style>