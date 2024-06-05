<template>
  <div class="container">
  <div class="table">
    <table>
      <tr v-for="(row, index) in tableData" :key="index">
        <td v-for="(item, colIndex) in row" :key="colIndex" :class="{ 'cell': item }">
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
  background-color: rgba(75, 72, 72, 0.8);;
  border-color:rgba(75, 72, 72, 0.8);;
}

.empty-item {
  width: 64px;
  height: 64px;
}
.table {
  width: 540px;
}
.container {
  width: 577px; /* 指定容器的宽度 */
  height: 190px; /* 指定容器的高度 */
  display: flex;
  justify-content: space-between; /*向左顶格 */
  align-items: center; /* 垂直居中 */
}
.table table {
  width: 577px; /* 指定容器的宽度 */
  height: 190px; /* 指定容器的高度 */
  border-collapse: collapse;
  background-color: rgba(75, 72, 72, 0.8);;
}

.table td {
  border: 0.1px solid white;
}

.item-img {
  width: 50px;
  height: 50px;
  display: block;
  margin: 0 auto;
}

.item-wrapper {
  position: relative;
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
</style>

<script>
  export default {
    data() {
      return {
        tableData: []
      }
    },
    props: {
        goods:{
          default: []
        }
    },
    methods:{},
    mounted() {
      this.tableData=[[],[],[]]
      for(var index=0;index<this.goods.length;index++){
            var row=Math.floor(index/9);
            var col=index%9;
            if(this.goods[index] !=null){
                this.tableData[row].push({
                          src: '/api/item/'+this.goods[index].key+"/icon",
                          name: this.goods[index].name,
                          damage: this.goods[index].damage,
                          maxDamage: this.goods[index].maxDamage,
                          count: this.goods[index].count,
                          alt: this.goods[index].name,
                          durability: (this.goods[index].maxDamage-this.goods[index].damage)/this.goods[index].maxDamage*100
                          });
                }
            else{
              this.tableData[row].push({
                src: "/images/minecraft_empty.png",
                name: "tempEmpty",
              });
            }
      }
      console.log(this.tableData);
    }
  };
  </script>
  
  