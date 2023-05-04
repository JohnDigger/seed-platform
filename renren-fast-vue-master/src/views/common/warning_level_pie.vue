<template>
  <div>
    <div ref="chart" class="pie"></div>
  </div>
</template>

<script>
import echarts from 'echarts';
import 'echarts/lib/chart/pie';
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/legend';
export default {

name: "warning_level_pie",
  // data(){
  // return{
  //   chartData: []
  // }
  // },
  mounted() {
    const chartData = this.chartData;

    // 从数据中提取预警等级和数量
    const data = chartData.map(item => ({
      name: item.warning_level,
      value: item.count,
    }));

    // 初始化图表
    const chart = echarts.init(this.$refs.chart);

    // 设置图表选项
    chart.setOption({
      color:["yellow","red","orange","blue"],
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)',
      },
      legend: {
        orient: 'vertical',
        left: 10,
        data: chartData.map(item => item.warning_level),
      },
      series: [
        {
          name: '预警等级',
          type: 'pie',
          radius: ['50%', '70%'],
          avoidLabelOverlap: false,
          label: {
            show: false,
            position: 'center',
          },
          emphasis: {
            label: {
              show: true,
              fontSize: '30',
              fontWeight: 'bold',
            },
          },
          labelLine: {
            show: false,
          },
          data,
        },
      ],
    });
  },
  props: {
    chartData: {
      type: Array,
      required: true,
    },
  },
}
</script>

<style scoped>

.pie{
  width: 100%;
  height: 400px;

}
</style>
