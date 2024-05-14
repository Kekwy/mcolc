<template>
  <div class="container">
    <div class="image-grid">
        <div v-for="(image, index) in images" :key="index" class="image-container">
            <el-image v-if="image" :src="image.src" :alt="image.alt" class="image"></el-image>
            <el-image v-else class="image"></el-image>
            <div v-if="image" class="label">{{ image.count }}</div>
        </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 700px; /* 指定容器的宽度 */
  height: 200px; /* 指定容器的高度 */
  display: flex;
  justify-content: flex-start; /* 向左顶格 */
  align-items: center; /* 垂直居中 */
}

.image-grid {
  display: flex;
  flex-wrap: wrap;
}

.image-container {
  position: relative; /* 使内部元素相对于此容器定位 */
  width: calc(10% - 2px); /* 每个图片容器的宽度，减去间距 */
  margin: 2px; /* 图片容器之间的间距 */
  box-sizing: border-box; /* 边框大小计算包括在元素的宽度和高度之内 */
  border: 4px solid #1d1d1c; /* 图片容器的边框样式 */
  padding: 10px; /* 图片容器内的填充 */
  background-color: rgba(75, 72, 72, 0.8); /* 标签背景色 */
}

.image {
  width: 95%; /* 图片宽度占满容器 */
}

.label {
  position: absolute; /* 相对于父容器绝对定位 */
  bottom: 1px; /* 距离容器底部的距离 */
  right: 1px; /* 距离容器右侧的距离 */
  background-color: rgba(255, 255, 255, 0.8); /* 标签背景色 */
  padding: 3px 5px; /* 标签内填充 */
  border-radius: 3px; /* 圆角 */
  font-size: 10px;
}

</style>

<script>
  export default {
    data() {
      return {
        images: [
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 1', label: ' 1' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 2', label: ' 2' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 3', label: ' 3' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 4', label: ' 4' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 5', label: ' 5' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 6', label: ' 6' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 7', label: ' 7' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 8', label: ' 8' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 9', label: ' 9' },

          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 10', label: ' 10' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 11', label: ' 11' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 12', label: ' 12' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 13', label: ' 13' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 14', label: ' 14' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 15', label: ' 15' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 16', label: ' 16' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 17', label: ' 17' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 18', label: ' 18' },

          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 19', label: ' 19' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 20', label: ' 20' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 21', label: ' 21' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 22', label: ' 22' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 23', label: ' 23' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 24', label: ' 24' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 25', label: ' 25' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 26', label: ' 26' },
          // { src: '/images/minecraft__dark_oak_log.png', alt: 'Image 27', label: ' 27' },
        ]
      }
    },
    props: {
      goods:{
        default: []
      }
    },
    methods:{
      getPcitureName(pathStr){
        const parts = pathStr.split(".");
        return parts[parts.length - 1];
      }
    },
    mounted() {
      for(var index=0;index<this.goods.length;index++){
          if(this.goods[index] !=null){
              this.images.push({
                    src: '/api/item/'+this.goods[index].key+"/icon",
                    name: this.goods[index].name,
                    damage: this.goods[index].damage,
                    maxDamage: this.goods[index].maxDamage,
                    count: this.goods[index].count,
                    alt: this.goods[index].name
                    })
            }
            else{
              this.images.push(null);
            }
      }
    }
  };
  </script>
  
  