<template>
  <div style="width: auto;height: 225px" id="echarts2">
  </div>
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'echarts2',
  data () {
    return {
      charts: {}
      // option配置
    }
  },
  mounted () {
    this.echartsInit()
    this.init()
  },
  methods: {
    echartsInit () {
      // 在生命周期中挂载
      this.charts = echarts.init(document.getElementById('echarts2'))
      this.charts.setOption({
        title: {
          text: '商品各类别销售比例图',
          subtext: '占比情况',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      })
    },
    init () {
      this.$nextTick(() => {
        this.$http({
          url: this.$http.adornUrl(`/product/panel/getPercent`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.charts.setOption({
              series: [{data: data.data}]
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
