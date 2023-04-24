<template>
<div >
  <div class="trace_procee_container">
    <h1>溯源流程</h1>
    <div class="trace_procee_img">
      <img src="../../../assets/img/溯源流程可视化.png" alt="">
    </div>
  </div>

  <div class="trace_procee_container">
    <h1>溯源概况</h1>
    <div>
      <el-row :gutter="20">
        <el-col :span="6">
          <div>
            <el-statistic group-separator="," :precision="2" :value="value2" :title="title"></el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div>
            <el-statistic title="男女比">
              <template slot="formatter"> 456/2 </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div>
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value1" :title="title">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: red"></i>
              </template>
              <template slot="suffix">
                <i class="el-icon-s-flag" style="color: blue"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div>
            <el-statistic :value="like ? 521 : 520" title="Feedback">
              <template slot="suffix">
              <span @click="like = !like" class="like">
                <i class="el-icon-star-on" style="color:red" v-show="!!like"></i>
                <i class="el-icon-star-off" v-show="!like"></i>
              </span>
              </template>
            </el-statistic>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>

</div>

</template>

<script>
export default {
  data() {
    return {
      like: true,
      value1: 4154.564,
      value2: 2222,
      title: '今年的增长',
    };
  },
}
</script>

<style scoped>

.like {
  cursor: pointer;
  font-size: 25px;
  display: inline-block;
}

.trace_procee_container {
  display: flex;
  flex-direction: column;

}

.trace_procee_container h1{
  font-size: 20px;

}

.trace_procee_container h1::after {
  content: "";
  display: block;
  height: 1px;
  width: 6.7%;
  background-color: #000000;
  margin-top: 10px;
}

.trace_procee_img {
  display: flex;
  justify-content: center;
}

.trace_procee_img img {
  max-width: 80%;
  display: block;

}
</style>
