<!-- <template>
    <div class="container">
      <div class="image-grid">
        <div v-for="(image, index) in images" :key="index" class="image-container">
            <template v-if="image">
                <el-image :src="image.src" :alt="image.alt" class="image"></el-image>
                <div v-if="image.count > 1" class="label">{{ image.count }}</div>
            </template>
        </div>
      </div>
      <div class="image-grid">
        <div class="arm-image-container">
          <template v-if="image">
            <el-image :src="offHandimage[0].src" :alt="offHandimage[0].alt" class="image"></el-image>
            <div v-if="offHandimage[0].count > 1" class="label">{{ offHandimage[0].count }}</div>
          </template> 
        </div>
      </div>
    </div>
</template> -->

<template>
  <div class="container">
  <div class="table">
    <table>
      <tr>
        <td v-for="(item, colIndex) in images" :key="colIndex" :class="{ 'cell': item }">
          <div class="cell-content">
                  <img v-if="item" :src="item.src" alt="Item Image" class="item-img" />
                  <img v-else class="empty-item">
                  <div  v-if="item && item.count > 1" class="item-number">{{ item.count }}</div>
          </div>
        </td>
      </tr>
    </table>
  </div>
</div>
</template>
  
<style scoped>
  .container {
    width: 700px; /* 指定容器的宽度 */
    height: 100px; /* 指定容器的高度 */
    display: flex;
    justify-content: space-between; /*向左顶格 */
    align-items: center; /* 垂直居中 */
  }
  
  .image-grid {
    display: flex;
    flex-wrap: wrap;
  }
  
  .image-container {
    position: relative; /* 使内部元素相对于此容器定位 */
    width: 69px; /* 每个图片容器的宽度，减去间距 */
    margin: 0.3px; /* 图片容器之间的间距 */
    box-sizing: border-box; /* 边框大小计算包括在元素的宽度和高度之内 */
    border: 1px solid #1d1d1c; /* 图片容器的边框样式 */
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
    width: 69px; /* 每个图片容器的宽度，减去间距 */
    margin: 0.3px; /* 图片容器之间的间距 */
    box-sizing: border-box; /* 边框大小计算包括在元素的宽度和高度之内 */
    border: 1px solid #1d1d1c; /* 更改为绿色边框 */
    padding: 10px; /* 图片容器内的填充 */
    background-color: rgba(75, 72, 72, 0.8); /* 标签背景色 */

  }

  </style>
  
  
  

    <script>
    export default {
      data() {
        return {
          images: [
          ],
          offHandimage:[
          ]
        }
      },
      props: {
        armour:{
            default: []
          },
          offHand:{
            default: []
          }
      },
      mounted() {
        // 加载盔甲
        for(var index=0;index<this.armour.length;index++){
            if(this.armour[index] !=null){
                this.images.push({
                      src: '/api/item/'+this.armour[index].key+"/icon",
                      name: this.armour[index].name,
                      damage: this.armour[index].damage,
                      maxDamage: this.armour[index].maxDamage,
                      count: this.armour[index].count,
                      alt: this.armour[index].name
                      })
              }
              else{
                this.images.push(null);
              }
        }

        // offHand
        for(var index=0;index<this.offHand.length;index++){
            if(this.offHand[index] !=null){
                this.offHandimage.push({
                      src: '/api/item/'+this.offHand[index].key+"/icon",
                      name: this.offHand[index].name,
                      damage: this.offHand[index].damage,
                      maxDamage: this.offHand[index].maxDamage,
                      count: this.offHand[index].count,
                      alt: this.offHand[index].name
                      })
              }
              else{
                this.offHandimage.push(null);
              }
        }

    }
    };
    </script>
    
    