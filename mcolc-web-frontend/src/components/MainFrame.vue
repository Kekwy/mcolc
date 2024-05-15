<template>
  <div :style="{ backgroundImage: 'url(\'' + '/images/background0.png' + '\')' }">
    <el-col>
      <!-- EXP -->
      <el-row >
            <el-col :span="6"><div class="grid-content bg-purple-dark">EXP:</div></el-col>
            <el-col :span="10"><el-progress :percentage="currentLevel" :color="ExpCustomColor" :stroke-width="10" :show-text="false"></el-progress></el-col>
            <el-col :span="6"><span :span="4" style="text-align:right;">{{this.level}}级</span></el-col>
          
          </el-row>

      <!-- HP -->
      <el-row>
        <el-col :span="6">
          <div class="grid-content bg-purple-dark" style="margin-top: 5px;">生命值:</div>
        </el-col>

        <el-col :span="5">
              <div>
                <HealthBar :hp="health" :maxHp="HpMax" />
              </div>
        </el-col>

      </el-row>

      <!-- Hg -->
      <el-row>
        <el-col :span="6">
          <div class="grid-content bg-purple-dark" style="margin-top: 5px;">饱食度:</div>
        </el-col>

        <el-col :span="10">
              <div>
                <HgBar :hg="hunger" :maxHg="HgMax" />
              </div>
        </el-col>

      </el-row>





      <!-- 快捷键 -->
      <el-row class="shortcut-row">
        <el-col :span="2">
          <div class="grid-content bg-purple-dark" >快捷:</div>
        </el-col>

        <el-col class="shortcut-row-item">
              <div>
                <Shortcut :hotBar="hotBar"/>
              </div>
        </el-col>
      </el-row>

      <!-- 武器 -->
      <el-row class="arm-row">
        <el-col :span="2">
          <div class="grid-content bg-purple-dark">装备:</div>
        </el-col>

        <el-col class="arm-row-item">
              <div>
                <Arm :armour="armor" :offHand="offHand"/>
              </div>
        </el-col>
      </el-row>

      <el-divider></el-divider>


      <!-- 物品栏 -->
      <el-row class="goods-row">
        <el-col :span="2">
          <div class="grid-content bg-purple-dark">物品栏:</div>
        </el-col>

        <el-col class="goods-row-item">
          <Goods :goods="goods"/>
        </el-col>
      </el-row>
  </el-col>
</div>
  
</template>

<style scoped>




.goods-row {
  display: flex;
  justify-content: center;
  align-items: center;
}
.goods-row-item {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 31vh; /* 可视区域的高度 */
}




.shortcut-row-item {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 15vh; /* 可视区域的高度 */
}
.shortcut-row {
  display: flex;
  justify-content: center;
  align-items: center;
}



.arm-row {
  display: flex;
  justify-content: center;
  align-items: center;
}
.arm-row-item {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 10vh; /* 可视区域的高度 */
}


</style>

<script>
import HealthBar from '@/components/HealthBar';
import HgBar from '@/components/Hg';
import Shortcut from '@/components/Shortcut'
import Goods from '@/components/Goods'
import Arm from '@/components/Arm'

  export default {
    methods: {
        random(min, max) {
          return Math.floor(Math.random() * (max - min + 1)) + min;
        }
    },
    components: {
    HealthBar,HgBar,Shortcut,Goods,Arm
    },
    computed: {
      backgroundImageUrl() {
        return '/images/background' +
            this.random(
                this.backgroundImageIndexBound[0],
                this.backgroundImageIndexBound[1])
            + '.png';
      }
    },
    data() {
        return {
          ExpCustomColor: '#409eff', // 等级条的颜色
      
          HpMax:10, //总体血量

          HgMax:10, //总体保持度

          };
    },
    mounted() {
    },
    props: {
      health: {
        default: 0 // 默认
      },
      hunger:{
        default: 0.5 // 默认
      },
      level:{
        default:10
      },
      currentLevel:{
        default:0.3
      },
      goods:{
        default: []
      },
      armor:{
        default: []
      },
      offHand:{
        default:[]
      },
      hotBar:{
        default: []
      }
    }
  }
</script>