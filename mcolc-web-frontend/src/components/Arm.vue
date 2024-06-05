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
        <tr >
          <td v-for="(item, colIndex) in images" :key="colIndex" :class="{ 'cell': item }">
            <div class="cell-content">
                <div class="item-wrapper">
                    <el-tooltip class="toolitem" effect="dark" content="item.name" placement="top-start">
                        <div slot="content">{{item.name}}</div>
                        <img v-if="item.name!='tempEmpty'" :src="item.src" alt="Item Image" class="item-img" />
                        </el-tooltip>
                    <img v-if="item.name=='tempEmpty'" class="empty-item" :src="item.src">
                    <div v-if="item.name!='tempEmpty' && item.count > 1" class="item-number">{{ item.count }}</div>
                </div>
                <div v-if="item.name!='tempEmpty' && item.count==1 && item.maxDamage!=0 " class="process-layout"> 
                  <el-progress :define-back-color="black" :stroke-width="3" :show-text='false' :percentage="item.durability" status="success"></el-progress>
                </div>
              </div>
          </td>
        </tr>
      </table>

      </div>
  
      <div>


        <table>
          <tr >
            <td v-for="(item, colIndex) in offHandimage" :key="colIndex" :class="{ 'cell': item }">
              <div class="cell-content">
                  <div class="item-wrapper">
                      <el-tooltip class="toolitem" effect="dark" content="item.name" placement="top-start">
                          <div slot="content">{{item.name}}</div>
                          <img v-if="item.name!='tempEmpty'" :src="item.src" alt="Item Image" class="item-img" />
                          </el-tooltip>
                      <img v-if="item.name=='tempEmpty'" class="empty-item" :src="item.src">
                      <div v-if="item.name!='tempEmpty' && item.count > 1" class="item-number">{{ item.count }}</div>
                  </div>
                  <div v-if="item.name!='tempEmpty' && item.count==1 && item.maxDamage!=0 " class="process-layout"> 
                    <el-progress :define-back-color="black" :stroke-width="3" :show-text='false' :percentage="item.durability" status="success"></el-progress>
                  </div>
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
  flex-direction: column;
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
  width: 600px;
  
}
.container {
  width: 577px; /* 指定容器的宽度 */
  height: 65px; /* 指定容器的高度 */
  display: flex;
  justify-content: space-between; /*向左顶格 */
  align-items: center; /* 垂直居中 */
}
.table table {
  width: 257px; /* 指定容器的宽度 */
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

.process-layout {
  background-color: white;
  position: absolute;
  bottom: 0px;
  width: 80%; 
}

.toolitem {
  margin: 0px;
}

.item-wrapper {
  position: relative;
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
                      alt: this.armour[index].name,
                      durability: (this.armour[index].maxDamage-this.armour[index].damage)/this.armour[index].maxDamage*100
                      })
              }
              else{     
                this.image.push({
                  src: "/images/minecraft_empty.png",
                  name: "tempEmpty",
                });

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
                      alt: this.offHand[index].name,
                      durability: (this.offHand[index].maxDamage-this.offHand[index].damage)/this.offHand[index].maxDamage*100
                      })
              }
              else{
                this.offHandimage.push({
                  src: "/images/minecraft_empty.png",
                  name: "tempEmpty",
                });

              }
        }

    }
    };
    </script>
    
    