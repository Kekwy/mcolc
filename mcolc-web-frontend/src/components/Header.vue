<template>
    <el-header style="text-align: right; font-size: 12px; background-color: #ffffff;">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>账号</span>
      </el-header>
</template>



<script>
    export default {
    name: "Header",
    methods: {
    }
  }

</script>

<style scoped>
.el-header {
  background-color: #ffffff;
  color: #ffffff;
  line-height: 60px;
}
</style>