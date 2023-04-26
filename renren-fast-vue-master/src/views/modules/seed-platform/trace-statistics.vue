<template>
  <div >

    <div class="trace_procee_container">
      <h1>溯源概况</h1>
      <div>
        <el-row :gutter="20">
          <el-col :span="6">
            <div>
              <el-statistic group-separator="," :precision="0" :value=parseInt(value1) title="总防伪码数"></el-statistic>
            </div>
          </el-col>

          <el-col :span="6">
            <div>
              <el-statistic group-separator="," :precision="0" :value=parseInt(value2) title="比上年增长"></el-statistic>
            </div>
          </el-col>

          <el-col :span="6">
            <div>
              <el-statistic group-separator="," :precision="0" :value=parseInt(value3) title="总溯源数"></el-statistic>
            </div>
          </el-col>

          <el-col :span="6">
            <div>
              <el-statistic group-separator="," :precision="0" :value=parseInt(value4) title="比上年增长"></el-statistic>
            </div>
          </el-col>

          <el-col :span="6">
            <div style="margin-top: 10%">
              <el-statistic group-separator="," :precision="0" :value=parseInt(value5) title="红色预警"></el-statistic>
            </div>
          </el-col>

          <el-col :span="6">
            <div style="margin-top: 10%">
              <el-statistic group-separator="," :precision="0" :value=parseInt(value6) title="橙色预警"></el-statistic>
            </div>
          </el-col>

          <el-col :span="6">
            <div style="margin-top: 10%">
              <el-statistic group-separator="," :precision="0" :value=parseInt(value7) title="黄色预警"></el-statistic>
            </div>
          </el-col>

          <el-col :span="6">
            <div style="margin-top: 10%">
              <el-statistic group-separator="," :precision="0" :value=parseInt(value8) title="蓝色预警"></el-statistic>
            </div>
          </el-col>

        </el-row>

        <div class="trace_procee_container">
          <h1>数据统计</h1>
          <div class="trace_procee_img">
            <img src="../../../assets/img/溯源数据统计.png" alt="">
          </div>
        </div>

      </div>
    </div>

  </div>

</template>

<script>
export default {
  name:"trace-process",
  data() {
    return {
      value1: 3124,
      value2: 245,
      value3: 5612,
      value4: 1211,
      value5: 23,
      value6: 121,
      value7: 231,
      value8: 782,

    };
  },
}
</script>

<style scoped>


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
  height: 2px;
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
