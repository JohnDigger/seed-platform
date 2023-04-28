<!--<template>-->
<!--  <div ref="heatmap" style="width: 100%; height: 400px;"></div>-->
<!--</template>-->

<!--<script>-->
<!--import echarts from 'echarts'-->

<!--export default {-->
<!--  name:'area_themal_maps',-->
<!--  data() {-->
<!--    return {-->
<!--      heatmapData: [] // 存储热力图数据的数组-->
<!--    }-->
<!--  },-->
<!--  mounted() {-->
<!--    // 初始化echarts实例-->
<!--    const chart = echarts.init(this.$refs.heatmap)-->

<!--    // 设置地图类型为中国地图-->
<!--    echarts.registerMap('china', require('./china.json'))-->

<!--    // 设置geo组件-->
<!--    chart.setOption({-->
<!--      geo: {-->
<!--        map: 'china',-->
<!--        roam: true,-->
<!--        label: {-->
<!--          show: true,-->
<!--          fontSize: 12,-->
<!--          color: '#ffffff'-->
<!--        },-->
<!--        itemStyle: {-->
<!--          areaColor: '#323c48',-->
<!--          borderColor: '#111'-->
<!--        }-->
<!--      },-->
<!--      visualMap: {-->
<!--        min: 0,-->
<!--        max: 100,-->
<!--        calculable: true,-->
<!--        inRange: {-->
<!--          color: ['#50a3ba', '#eac736', '#d94e5d']-->
<!--        }-->
<!--      },-->
<!--      series: [{-->
<!--        type: 'heatmap',-->
<!--        coordinateSystem: 'geo',-->
<!--        data: this.heatmapData,-->
<!--        pointSize: 10,-->
<!--        blurSize: 20-->
<!--      }]-->
<!--    })-->

<!--    // 加载热力图数据-->
<!--    this.loadHeatmapData().then(data => {-->
<!--      this.heatmapData = data-->
<!--      chart.setOption({-->
<!--        series: [{-->
<!--          data: this.heatmapData-->
<!--        }]-->
<!--      })-->
<!--    })-->
<!--  },-->
<!--  methods: {-->
<!--    loadHeatmapData() {-->
<!--      // 加载热力图数据的方法，返回一个Promise对象-->
<!--      return new Promise((resolve, reject) => {-->
<!--        // 在这里获取并解析地址数据，将经纬度保存到this.heatmapData数组中-->
<!--        // ...-->
<!--        resolve(this.heatmapData)-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->
