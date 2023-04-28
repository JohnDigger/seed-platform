<template>
  <div>
    <!--块类统计-->
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card">
          <h3>种子总量</h3>
          <div>
            <i class="el-icon-s-flag" style="color: purple"></i>
            <!--            <span v-text="dataList.countOrder"></span>-->
            <CountTo :startVal="dataList.startVal" :endVal="dataList.newSeedCount" :duration='dataList.duration'></CountTo>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>新品总数</h3>
          <div>
            <i class="el-icon-user-solid" style="color: green"></i>
            <!--            <span v-text="dataList.countUser"></span>-->
            <CountTo :startVal="dataList.startVal" :endVal="dataList.seedInfoCount" :duration='dataList.duration'></CountTo>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>二维码总量</h3>
          <div>
            <i class="el-icon-s-flag" style="color: red"></i>
            <CountTo :startVal="dataList.startVal" :endVal="dataList.codeCount" :duration='dataList.duration'></CountTo>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>溯源次数</h3>
          <div>
            <i class="el-icon-star-on" style="color: blue"></i>
            <CountTo :startVal="dataList.startVal" :endVal="dataList.traceCount" :duration='dataList.duration'></CountTo>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!--acharts统计图-->
    <el-row :gutter="20">
      <el-col :span="16">
        <el-card class="box-card">
          <echarts1/>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <echarts2/>
        </el-card>
        <el-card class="box-card">
          <echarts3/>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import echarts1 from './echarts1'
import echarts2 from './echarts2'
import echarts3 from './echarts3'
import CountTo from 'vue-count-to'

export default {
  name: 'home',
  data () {
    return {
      dataList: {
        newSeedCount: 0,
        seedInfoCount: 0,
        codeCount: 0,
        traceCount: 0,
        startVal: 0,
        endVal: 0,
        duration: 3000,
        timer: null

      }
    }
  },
  components: {
    echarts1,
    echarts2,
    echarts3,
    CountTo
  },
  mounted () {
    this.init()
    this.timer = setInterval(() => {
      this.endVal = this.endVal * 2
    }, 4000)
  },
  destroyed () {
    clearInterval(this.timer)
  },
  methods: {
    init () {
      this.$nextTick(() => {
        this.$http({
          url: this.$http.adornUrl(`/seed-platform/tseedgrowenvironment/top`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList.newSeedCount = data.newSeedCount
            this.dataList.seedInfoCount = data.seedInfoCount
            this.dataList.codeCount = data.codeCount
            this.dataList.traceCount = data.traceCount
          }
        })
      })
    }
  }
}
</script>
<style scoped>
.el-card {
  margin-bottom: 20px;
  text-align: center;
}

</style>
