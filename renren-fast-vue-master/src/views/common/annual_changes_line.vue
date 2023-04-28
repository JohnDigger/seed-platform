<template>
  <div>
    <div ref="chart" class="container" ></div>
  </div>
</template>

<script>
import echarts from 'echarts';

export default {
  name:"annual_changes_line",
  // data() {
  //   return {
  //     traceCountByNY: [
  //       {"year":2013,"count":2},
  //       {"year":2014,"count":5},
  //       {"year":2015,"count":6},
  //       {"year":2016,"count":3},
  //       {"year":2017,"count":5},
  //       {"year":2018,"count":2},
  //       {"year":2019,"count":2},
  //       {"year":2020,"count":3},
  //       {"year":2021,"count":5},
  //       {"year":2022,"count":4},
  //       {"year":2023,"count":1},
  //     ],
  //   };
  // },
  mounted() {
    this.initLine()
  },
  methods:{
    initLine(){
      const traceCountByNY = this.traceCountByNY;
      // 初始化 ECharts 实例
      const chart = echarts.init(this.$refs.chart);

      // 配置 ECharts 参数
      const option = {
        color: ['#3E8EF7'],
        title: {
          text: '近10年的溯源数',
        },
        tooltip: {
          trigger: 'axis',
        },
        xAxis: {
          type: 'category',
          data: this.traceCountByNY.map(item => item.year),
        },
        yAxis: {
          type: 'value',


        },
        series: [{
          data: this.traceCountByNY.map(item => item.count),
          type: 'line',
        }],
      };
      // 使用配置项显示图表
      chart.setOption(option);
    }

  },
  props: {
    traceCountByNY: {
      type: Array,
      required: true,
    },
  },
  watch:{
    traceCountByNY(){
      this.initLine()
    }
  }
};
</script>

<style scoped>
.container{
  width: 100%;
  height: 400px;

}
</style>
