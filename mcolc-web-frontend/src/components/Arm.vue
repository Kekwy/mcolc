<template>
    <div class="container">
      <div class="image-grid">
          <div v-for="(image, index) in images" :key="index" class="image-container">
              <el-image :src="image.src" :alt="image.alt" class="image"></el-image>
              <div class="label">{{ image.label }}</div>
          </div>
      </div>
      <div class="image-grid">
        <div class="arm-image-container">
          <el-image :src="armimage[0].src" :alt="armimage[0].alt" class="image"></el-image>
          <div class="label">{{ armimage[0].label }}</div>
        </div>
      </div>
    </div>
</template>
  
<style scoped>
  .container {
    width: 700px; /* 指定容器的宽度 */
    height: 100px; /* 指定容器的高度 */
    display: flex;
    justify-content: flex-start; /*向左顶格 */
    align-items: center; /* 垂直居中 */
  }
  
  .image-grid {
    display: flex;
    flex-wrap: wrap;
  }
  
  .image-container {
    position: relative; /* 使内部元素相对于此容器定位 */
    width: calc(12.5% - 2px); /* 每个图片容器的宽度，减去间距 */
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
  
  .arm-image-container {
    position: relative; /* 使内部元素相对于此容器定位 */
    width: calc(50% - 2px); /* 每个图片容器的宽度，减去间距 */
    margin: 2px; /* 图片容器之间的间距 */
    box-sizing: border-box; /* 边框大小计算包括在元素的宽度和高度之内 */
    border: 4px solid #1d1d1c; /* 更改为绿色边框 */
    padding: 10px; /* 图片容器内的填充 */
    background-color: rgba(75, 72, 72, 0.8); /* 标签背景色 */
  }

  </style>
  
  
  
  
  
    <script>
    export default {
      data() {
        return {
          images: [
            { src: '/images/minecraft__dark_oak_sapling.png', alt: 'Image 1', label: ' 1' },
            { src: '/images/minecraft__dark_oak_sapling.png', alt: 'Image 2', label: ' 2' },
            { src: '/images/minecraft__dark_oak_sapling.png', alt: 'Image 3', label: ' 3' },
            { src: '/images/minecraft__dark_oak_sapling.png', alt: 'Image 4', label: ' 4' },
          ],
          armimage:[
            { src: '/images/minecraft__dark_oak_sapling.png', alt: 'Image 5', label: ' 5' },
          ]
        }
      }
    };
    </script>
    
    