<template>
  <div :style="{ backgroundImage: 'url(\'' + '/images/background0.png' + '\')' }">
    <el-col>
      <!-- EXP -->
      <el-row >
            <el-col :span="6"><div class="grid-content bg-purple-dark">EXP:</div></el-col>
            <el-col :span="10"><el-progress :percentage="ExpPercentage" :color="ExpCustomColor" :stroke-width="10"></el-progress></el-col>
            <el-col :span="4"><span>37级</span></el-col>
          
          </el-row>

      <!-- HP -->
      <el-row>
        <el-col :span="6">
          <div class="grid-content bg-purple-dark" style="margin-top: 5px;">血条:</div>
        </el-col>

        <el-col :span="10">
              <div>
                <HealthBar :hp="Hp" :maxHp="HpMax" />
              </div>
        </el-col>

      </el-row>

      <!-- Hg -->
      <el-row>
        <el-col :span="6">
          <div class="grid-content bg-purple-dark" style="margin-top: 5px;">保持度:</div>
        </el-col>

        <el-col :span="10">
              <div>
                <HgBar :hg="Hg" :maxHg="HgMax" />
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
                <Shortcut />
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
                <Arm />
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
          <Goods/>
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
  height: 35vh; /* 可视区域的高度 */
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
      ExpPercentage : 50, //经验值占比
      ExpLevel : 37, //等级
      ExpCustomColor: '#409eff', // 等级条的颜色
  
      Hp: 6.5, // 血条占比
      HpMax:10, //总体血量

      Hg: 4.5, // 保持度占比
      HgMax:10 //总体保持度


    };
  },
    mounted() {
    }
  }
</script>