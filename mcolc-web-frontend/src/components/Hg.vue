<template>
  <div class="container" style="margin-top: 10px;">
        <div v-for="(image, index) in HgBarList" :key="index" class="image-container">
            <el-image :src="image.src" :alt="image.alt" class="image"></el-image>
        </div>
  </div>
</template>


<style scoped>
.health-bar {
  display: flex;
  align-items: center;
}
.container {
  width: 200px; /* 指定容器的宽度 */
  height:15px; /* 指定容器的高度 */
  display: flex;
  justify-content: flex-start; /* 向左顶格 */
  align-items: center; /* 垂直居中 */
}
.image-container {
  position: relative; /* 使内部元素相对于此容器定位 */
  width: calc(20% - 2px); /* 每个图片容器的宽度，减去间距 */
  box-sizing: border-box; /* 边框大小计算包括在元素的宽度和高度之内 */
}
.image {
  width: 100%; /* 图片宽度占满容器 */
}
</style>





<script>
export default {
  data() {
      return {
        hunger_full_image:"/images/hunger_full.png",
        hunger_full_alt:"health_full",

        hunger_half_image:"/images/hunger_half.png",
        hunger_full_alt:"hunger_half",

        hunger_empty_image:"/images/hunger_empty.png",
        hunger_empty_alt:"hunger_empty",

        HgBarList:[]
      }
  },
  mounted(){
    this.addHgPicture(this.HgBarList); // 添加血条对应的图片序列
  },
  props: {
    hg: {
      type: Number,
      default: 5 // 默认血量为5
    },
    maxHg: {
      type: Number,
      default: 10 // 默认最大Hg10
    }
  },
  methods:{
    addHgPicture(HgBarList) {
          const full = Math.floor(this.hg);
          const half = Math.round((this.hg - full) * 2);
          const empty = Math.max(0, this.maxHg - full - half);
            for (let i = 0; i < full; i++) {
              HgBarList.push({src:this.hunger_full_image,alt:this.hunger_full_alt});
            }
            if (half > 0) {
              HgBarList.push({src:this.hunger_half_image,alt:this.hunger_half_alt});
            }
            for (let i = 0; i < empty; i++) {
              HgBarList.push({src:this.hunger_empty_image,alt:this.hunger_empty_alt});
            }
      }

    }
  }
</script>