<template>
  <div >

    <div class="trace_procee_container">
      <h1>溯源概况</h1>
      <div>

        <!--块类统计-->
        <el-row :gutter="20">
          <el-col :span="6">
            <el-card class="box-card">
              <h3>防伪码总数</h3>
              <div>
                <i class="el-icon-s-flag" style="color: purple"></i>
                <!--            <span v-text="dataList.countOrder"></span>-->
                <CountTo :startVal="dataList.startVal" :endVal="dataList.traceCodeCount" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card">
              <h3>比去年增长</h3>
              <div>
                <i class="el-icon-upload2" style="color: purple"></i>
                <!--            <span v-text="dataList.countUser"></span>-->
                <CountTo :startVal="dataList.startVal" :endVal="dataList.traceCodeIncrease" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card">
              <h3>扫码总次数</h3>
              <div>
                <i class="el-icon-s-flag" style="color: green"></i>
                <CountTo :startVal="dataList.startVal" :endVal="dataList.traceCount" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card">
              <h3>比去年增长</h3>
              <div>
                <i class="el-icon-upload2" style="color: green"></i>
                <CountTo :startVal="dataList.startVal" :endVal="dataList.traceCodeIncrease" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <!--块类统计-->
        <el-row :gutter="20">
          <el-col :span="6">
            <el-card class="box-card">
              <h3>蓝色预警</h3>
              <div>
                <i class="el-icon-warning" style="color: blue"></i>
                <!--            <span v-text="dataList.countOrder"></span>-->
                <CountTo :startVal="dataList.startVal" :endVal="dataList.warningLevelList.blue" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card">
              <h3>黄色预警</h3>
              <div>
                <i class="el-icon-warning" style="color: yellow"></i>
                <!--            <span v-text="dataList.countUser"></span>-->
                <CountTo :startVal="dataList.startVal" :endVal="dataList.warningLevelList.yellow" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card">
              <h3>橙色预警</h3>
              <div>
                <i class="el-icon-warning" style="color: orange"></i>
                <CountTo :startVal="dataList.startVal" :endVal="dataList.warningLevelList.orange" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card">
              <h3>红色预警</h3>
              <div>
                <i class="el-icon-warning" style="color: red"></i>
                <CountTo :startVal="dataList.startVal" :endVal="dataList.warningLevelList.red" :duration='dataList.duration'></CountTo>
              </div>
            </el-card>
          </el-col>
        </el-row>


      </div>

      <div class="echarts-container">
        <div class="pie-container">
          <div style="margin-top: 40px"></div>
          <warning_level_pie v-if="!loading" :chart-data="traceCodeCountByWarningLevelPie" />

        </div>

        <div class="line-container">
          <el-select v-model="select_value" filterable placeholder="请选择" @change="handleChange">
            <el-option
              v-for="item in seed_name_options"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
          <annual_changes_line v-if="!loading" :trace-count-by-n-y="traceCountByNY"/>
        </div>
      </div>


    </div>

  </div>

</template>

<script>
import CountTo from 'vue-count-to'
import warning_level_pie from '../../../views/common/warning_level_pie'
import annual_changes_line from '../../../views/common/annual_changes_line'

export default {
  name:"trace-process",
  data() {
    return {
      dataList:{
        startData:0,
        duration:3000,
        traceCodeCount:0,
        traceCodeIncrease:0,
        traceCount:0,
        traceIncrease:0,
        warningLevelList:{
          blue:0,
          yellow:0,
          orange:0,
          red:0
        }
      },
      dataTemp:[],
      traceCodeCountByWarningLevelPie: [],
      loading: true,

      seed_name_options: [],
      select_value: '优质花生1号',
      traceCountByNY: [
        // {"year":2014,"count":5},
        // {"year":2015,"count":6},
        // {"year":2016,"count":3},
        // {"year":2017,"count":5},
        // {"year":2018,"count":2},
        // {"year":2019,"count":2},
        // {"year":2020,"count":3},
        // {"year":2021,"count":5},
        // {"year":2022,"count":4},
        // {"year":2023,"count":1},
      ],
    };

  },
  components:{
    CountTo,
    warning_level_pie,
    annual_changes_line
  },

  mounted () {
    this.getTraceCount()
    this.getTraceCountByYear()
    this.getTraceCodeCount()
    this.getTraceCodeCountByYear()
    this.getTraceCodeCountByWarningLevel()
    this.getTraceSeedName()
    this.getTraceCountByNY(this.select_value)

    console.log(this.traceCodeCountByWarningLevelPie)
    this.timer = setInterval(() => {
      this.endVal = this.endVal * 2
    }, 4000)
    console.log(this.traceCodeCountByWarningLevelPie)

  },
  destroyed () {
    clearInterval(this.timer)
  },



  methods:{
    handleChange() {
      this.getTraceCountByNY(this.select_value)
      console.log(this.traceCountByNY)

      // 在这里执行相应的操作，例如异步请求等
    },
    getCountByYear(year) {
      const record = this.dataTemp.find(item => item.year === year); // 查找指定年份的数据记录
      return record ? record.count : 0; // 如果找到了记录，则返回count值，否则返回0
    },
    //获取溯源总数
    getTraceCount(){
      console.log("获取溯源总数")
      this.$http({
        url: this.$http.adornUrl('/seed-platform/traceinfo/getTraceCount'),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList.traceCount = data.traceCount
          console.log(data.traceCount)
        } else {
          console.log(data.code)
        }
      })
    },
    //获取每年的溯源总数
    getTraceCountByYear(){
      this.$http({
        url: this.$http.adornUrl('/seed-platform/traceinfo/getTraceCountByYear'),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataTemp = data.traceCountByYear
          const currentYear = new Date().getFullYear(); // 获取当前年份
          const currentCount = this.getCountByYear(currentYear); // 获取当前年份的count值
          const lastYear = currentYear - 1; // 计算去年的年份
          const lastCount = this.getCountByYear(lastYear); // 获取去年的count值
          const growth = currentCount - lastCount; // 计算增长量
          this.dataList.traceIncrease = growth
        } else {
          console.log(data.code)
        }
      })
    },
    //获取防伪码总数
    getTraceCodeCount(){
      this.$http({
        url: this.$http.adornUrl('/seed-platform/tracecodeinfo/getTraceCodeCount'),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList.traceCodeCount = data.traceCodeCount
        } else {
          console.log(data.code)
        }
      })
    },
    //获取每年的防伪码总数
    getTraceCodeCountByYear(){
      this.$http({
        url: this.$http.adornUrl('/seed-platform/tracecodeinfo/getTraceCodeCountByYear'),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataTemp = data.traceCodeCountByYear
          const currentYear = new Date().getFullYear(); // 获取当前年份
          const currentCount = this.getCountByYear(currentYear); // 获取当前年份的count值
          const lastYear = currentYear - 1; // 计算去年的年份
          const lastCount = this.getCountByYear(lastYear); // 获取去年的count值
          const growth = currentCount - lastCount; // 计算增长量
          this.dataList.traceCodeIncrease = growth
        } else {
          console.log(data.code)
        }
      })
    },
    //获取每种预警等级的防伪码总数
    getTraceCodeCountByWarningLevel(){
      this.$http({
        url: this.$http.adornUrl('/seed-platform/tracecodeinfo/getTraceCodeCountByWarningLevel'),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 0) {

          this.traceCodeCountByWarningLevelPie = data.traceCodeCountByWarningLevel
          this.loading = false;
          for (let i=0;i<data.traceCodeCountByWarningLevel.length;i++){
            if (data.traceCodeCountByWarningLevel[i].warning_level == "蓝色"){
              this.dataList.warningLevelList.blue = data.traceCodeCountByWarningLevel[i].count
            }
            else if (data.traceCodeCountByWarningLevel[i].warning_level == "黄色"){
              this.dataList.warningLevelList.yellow = data.traceCodeCountByWarningLevel[i].count
            }
            else if (data.traceCodeCountByWarningLevel[i].warning_level == "橙色"){
              this.dataList.warningLevelList.orange = data.traceCodeCountByWarningLevel[i].count
            }
            else {
              this.dataList.warningLevelList.red = data.traceCodeCountByWarningLevel[i].count
            }
          }



        } else {
          console.log(data.code)
          this.loading = false;
        }
      })
    },

    //获取近10年指定种子每年的溯源数
    getTraceCountByNY(name){
      this.$http({
        url: this.$http.adornUrl('/seed-platform/traceinfo/getTraceCountByNY?name='+name),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.traceCountByNY = data.traceCountByNY
          this.loading = false;
        } else {
          console.log(data.code)
          this.loading = false;
        }
      })
    },

    //获取种子列表
    getTraceSeedName(){
      this.$http({
        url: this.$http.adornUrl('/seed-platform/traceinfo/getTraceSeedName'),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 0) {
          console.log(data)
          this.seed_name_options = data.traceSeedName
        } else {
          console.log(data.code)
        }
      })
    },

  }
}
</script>

<style scoped>
.echarts-container{
  display: flex;
}

.echarts-container .pie-container{
   width: 50%;
}
.echarts-container .line-container{
  width: 50%;
}

.el-card {
  margin-bottom: 20px;
  text-align: center;
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
