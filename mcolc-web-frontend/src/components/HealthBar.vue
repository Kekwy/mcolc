<template>
  <div class="container" style="margin-top: 10px;">
        <div v-for="(image, index) in HealthBarList" :key="index" class="image-container">
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
        full_image:"/images/health_full.png",
        full_alt:"health_full",

        half_image:"/images/health_half.png",
        half_alt:"health_half",

        empty_image:"/images/health_empty.png",
        empty_alt:"health_empty",

        HealthBarList:[]
      }
  },
  mounted(){
    this.hp=this.hp/2;
    this.addHealthPicture(this.HealthBarList); // 添加血条对应的图片序列

  },
  props: {
    hp: {
      type: Number,
      default: 5 // 默认血量为5
    },
    maxHp: {
      type: Number,
      default: 10 // 默认最大血量为20
    }
  },
  methods:{
    addHealthPicture(HealthBarList) {
          const full = Math.round(this.hp);
          const half = Math.round((this.hp - full) * 2);
          const empty = Math.max(0, this.maxHp - full - half);
            for (let i = 0; i < full; i++) {
              HealthBarList.push({src:this.full_image,alt:this.full_alt});
            }
            if (half > 0) {
              HealthBarList.push({src:this.half_image,alt:this.half_alt});
            }
            for (let i = 0; i < empty; i++) {
              HealthBarList.push({src:this.empty_image,alt:this.empty_alt});
            }
      }

    }
  }
</script>