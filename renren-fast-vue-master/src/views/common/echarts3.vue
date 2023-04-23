<template>
  <div style="width: auto;height: 185px" id="echarts3">
  </div>
</template>

<script>
import * as echarts from 'echarts'
export default {
  name: 'echarts3',
  data () {
    return {
      // option配置
      charts: {},
      date: [],
      count: []
    }
  },
  mounted () {
    this.echartsInit()
    this.init()
  },
  methods: {
    echartsInit () {
      // 在生命周期中挂载
      this.charts = echarts.init(document.getElementById('echarts3'))
      this.charts.setOption({
        title: {
          text: '近七日销售情况',
          left: 'center'
        },
        color: '#73c0de',
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line',
            areaStyle: {}
          }
        ]
      })
    },
    init () {
      this.$nextTick(() => {
        this.$http({
          url: this.$http.adornUrl(`/product/panel/getRecent`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            data.data.forEach(ele => {
              this.date.push(ele.date)
              this.count.push(ele.count)
            })
            this.charts.setOption({
              xAxis: {
                data: this.date
              },
              series: [{
                data: this.count
              }]
            })
          }
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
