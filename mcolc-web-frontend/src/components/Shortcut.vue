<template>
  <div class="container">
  <div class="table">
    <table>
      <tr>
        <td v-for="(item, colIndex) in images" :key="colIndex" :class="{ 'cell': item }">
          <div class="cell-content">
                  <img v-if="item" :src="item.src" alt="Item Image" class="item-img" />
                  <img v-else class="empty-item">
                  <div  v-if="item.name!='tempEmpty' && item.count > 1" class="item-number">{{ item.count }}</div>
          </div>
        </td>
      </tr>
    </table>
  </div>
</div>
</template>

<style scoped>
.cell-content {
  position: relative; /* 为了定位内部的数字 */
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.empty-item {
  width: 64px;
  height: 64px;
  background-color: rgba(75, 72, 72, 0.8);;
  border-color:rgba(75, 72, 72, 0.8);;
}
.table {
  width: 540px;
}
.container {
  width: 577px; /* 指定容器的宽度 */
  height: 65px; /* 指定容器的高度 */
  display: flex;
  justify-content: space-between; /*向左顶格 */
  align-items: center; /* 垂直居中 */
}
.table table {
  width: 577px; /* 指定容器的宽度 */
  height: 65px; /* 指定容器的高度 */
  border-collapse: collapse;
  background-color: rgba(75, 72, 72, 0.8);;
}

.table td {
  border: 0.1px solid white;
}

.item-img {
  width: 64px;
  height: 64px;
  display: block;
  margin: 0 auto;
  background-color: rgba(75, 72, 72, 0.8);
}

.item-number {
  position: absolute;
  bottom: 0px;
  right: 0px;
  background-color: white;
  padding: 1px 1px;

  font-size: 0.8em; /* 调整字体大小 */
}
</style>





  <script>
  export default {
    data() {
      return {
        images: [
        ]
      }
    },
    props: {
      hotBar:{
            default: []
          },
      },
      mounted() {
        // 加载快捷键
        for(var index=0;index<this.hotBar.length;index++){
            if(this.hotBar[index] !=null){
                this.images.push({
                      src: '/api/item/'+this.hotBar[index].key+"/icon",
                      name: this.hotBar[index].name,
                      damage: this.hotBar[index].damage,
                      maxDamage: this.hotBar[index].maxDamage,
                      count: this.hotBar[index].count,
                      alt: this.hotBar[index].name
                      })
              }
              else{
                this.images.push({
                  src: "/images/minecraft_empty.png",
                  name: "tempEmpty",
                });
              }
            console.log(this.images);
      }
    }
  }
  </script>
  
  