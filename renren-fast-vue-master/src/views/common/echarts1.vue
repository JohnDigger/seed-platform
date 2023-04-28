<template>
  <div style="width: auto;height: 470px" id="echarts1">
  </div>
</template>

<script>
import echarts from 'echarts'
const colors = ['#5470C6', '#EE6666']
export default {
  name: 'echarts1',
  data () {
    return {
      thisYear: [],
      thisYearData: [],
      // option配置
      lastYear: [],
      lastYearData: [],
      applyChart: {}
    }
  },
  mounted () {
    this.init()
    this.echartsInit()
  },
  methods: {
    echartsInit () {
      // 在生命周期中挂载
      this.applyChart = echarts.init(document.getElementById('echarts1'))
      this.applyChart.setOption({
        title: {
          text: '近两年新品种数量对比',
          left: 'center'
        },
        color: colors,
        tooltip: {
          trigger: 'none',
          axisPointer: {
            type: 'cross'
          }
        },
        legend: {},
        grid: {
          top: 70,
          bottom: 50
        },
        xAxis: [
          {
            type: 'category',
            axisTick: {
              alignWithLabel: true
            },
            axisLine: {
              onZero: false,
              lineStyle: {
                color: colors[1]
              }
            },
            axisPointer: {
              label: {
                formatter: function (params) {
                  return (
                    'Precipitation  ' +
                    params.value +
                    (params.seriesData.length ? '：' + params.seriesData[0].data : '')
                  )
                }
              }
            },
            // prettier-ignore
            data: []
          },
          {
            type: 'category',
            axisTick: {
              alignWithLabel: true
            },
            axisLine: {
              onZero: false,
              lineStyle: {
                color: colors[0]
              }
            },
            axisPointer: {
              label: {
                formatter: function (params) {
                  return (
                    'Precipitation  ' +
                    params.value +
                    (params.seriesData.length ? '：' + params.seriesData[0].data : '')
                  )
                }
              }
            },
            // prettier-ignore
            data: []
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: 'Precipitation(2022)',
            type: 'line',
            xAxisIndex: 1,
            smooth: true,
            emphasis: {
              focus: 'series'
            },
            data: []
          },
          {
            name: 'Precipitation(2023)',
            type: 'line',
            smooth: true,
            emphasis: {
              focus: 'series'
            },
            data: []
          }
        ]
      })
    },
    async init () {
      this.$nextTick(() => {
        this.$http({
          url: this.$http.adornUrl(`/seed-platform/tseedcharacteristics/getThisYear`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            data.data.forEach(ele => {
              this.thisYear.push(ele.yearMonth)
              this.thisYearData.push(ele.countData)
            })
          }
        })
      })
      this.$nextTick(() => {
        this.$http({
          url: this.$http.adornUrl(`/seed-platform/tseedcharacteristics/getLastYear`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            data.data.forEach(ele => {
              this.lastYear.push(ele.yearMonth)
              this.lastYearData.push(ele.countData)
            })
            this.applyChart.setOption({
              xAxis: [{
                data: this.thisYear
              }, {
                data: this.lastYear
              }],
              series: [{
                name: 'Precipitation(2022)',
                data: this.lastYearData,
                xAxisIndex: 1
              },
                { name: 'Precipitation(2023)',
                  data: this.thisYearData
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
