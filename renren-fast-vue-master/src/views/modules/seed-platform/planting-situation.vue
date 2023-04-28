<template>
  <div>
    <div class="dashboard">
      <div class="chart">
        <div ref="barChart" style="width: 520px; height: 300px;"></div>
      </div>
      <div class="chart">
        <div ref="lineChart" style="width: 480px; height: 300px;"></div>
      </div>
      <div class="data">
        <el-form ref="form" :model="formData">
          <el-form-item label="选择各种子的年产量(万公顷)" >
            <el-select v-model="formData.data" placeholder="请选择" @change="getDataList">
              <el-option v-for="option in dataOptions" :key="option.value" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="选择不同类型的种子" >
            <el-select v-model="formData.data1" placeholder="请选择" @change="getDataList1">
              <el-option v-for="seed in dataOptions1" :key="seed" :label="seed" :value="seed" />
            </el-select>
          </el-form-item>
          <el-form-item label="可视化说明">
            <el-input v-model="formData.description" />
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="dashboard2">
      <div class="chart">
        <div ref="pieChart" style="width: 480px; height: 380px;"></div>
      </div>
      <div class="chart" style="margin-left: 14%">
        <div ref="lineChart2" style="width: 480px; height: 310px;"></div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as echarts from 'echarts';

  export default {
    data() {
      return {
        seed_type1: [],
        dataList1: [],
        seed_type2: [],
        dataList2: [],
        seed_type3: [],
        dataList3: [],
        barChartOptions: { // 条形图
          color: ['#3E8EF7'],
          title: {
            text: '不同种子的年产量条形图',
            left: '30%',
          },
          xAxis: {
            type: 'category',
            data: this.seed_type1,
            axisLabel: {
              fontSize: 7 // 设置 x 轴标签字体大小为 12px
            }
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              fontSize: 8 // 设置 x 轴标签字体大小为 12px
            }
          },
          series: [
            {
              data: this.dataList1,
              type: 'bar',
              itemStyle: {color:'#6ca037'}
            },
          ],
        },
        lineChartOptions: { // 折线图
          color: ['#3E8EF7'],
          title: {
            text: '种子的年产量变化折线图',
            left: '25%',
          },
          xAxis: {
            type: 'category',
            data: this.seed_type2,
            axisLabel: {
              fontSize: 8 // 设置 x 轴标签字体大小为 12px
            }
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              fontSize: 8 // 设置 x 轴标签字体大小为 12px
            }
          },
          series: [
            {
              data: this.dataList2,
              type: 'line',
              itemStyle: {  // itemStyle 中包含了颜色属性
                color: 'blue'  // 将颜色修改为蓝色
              }
            },
          ],
        },
        pieChartOptions :{ // 种子种植方式饼图
          title: {
            text: '种子种植方式占比图',
            left: '30%',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: []
          },
          series: [
            {
              name: '种子产量',
              type: 'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '30',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: [],
              itemStyle: {  // itemStyle 中包含了颜色属性
                color: 'blue'  // 将颜色修改为蓝色
              }
            }
          ]
        },
        lineChartOptions2: { // 种子种植面积变化折线图
          color: ['#3E8EF7'],
          title: {
            text: '种子种植面积变化折线图',
            left: '25%',
          },
          xAxis: {
            type: 'category',
            data: this.seed_type3,
            axisLabel: {
              fontSize: 8 // 设置 x 轴标签字体大小为 12px
            }
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              fontSize: 8 // 设置 x 轴标签字体大小为 12px
            },
          },
          series: [
            {
              data: this.dataList3,
              type: 'line',
            },
          ],
        },
        dataOptions: [ // 年份信息
          {label: '2012', value: '2012',}, {label: '2013', value: '2013',}, {label: '2014', value: '2014',}, {label: '2015', value: '2015',},
          {label: '2016', value: '2016',}, {label: '2017', value: '2017',}, {label: '2018', value: '2018',}, {label: '2019', value: '2019',},
          {label: '2020', value: '2020',}, {label: '2021', value: '2021',},
        ],
        dataOptions1:[],//种子类型信息
        formData: {
          data: '2012',
          data1: '小麦',
          description: '阿坝种子平台种植情况预览可视化',
        },
      };
    },
    activated() {
      this.getDataList();
      this.getSeedType();
      this.getDataList1();
    },
    methods: {
      // 获取条形数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/seed-platform/seedyield/getYearyield'),
          method: 'get',
          params: this.$http.adornParams({
            'year': this.formData.data,
          })
        }).then(({data}) => {
          if (data && data.code === 0) { // 获取请求成功
            const datas = [];
            const seeds = [];
            for(var i = 0; i < data.data.length; i ++){
              datas.push(parseInt(data.data[i].yield));
              seeds.push(data.data[i].seed_type);
            }
            this.barChartOptions.xAxis.data = seeds;
            this.barChartOptions.series[0].data = datas;
            this.renderBarChart();
          } else {
            console.log("访问失败！")
          }
        })
      },
      getSeedType () { // 获取所有种子类型
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/seed-platform/plantingmethod/getSeedinfo'),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 0) { // 获取请求成功
            const seeds = [];
            for(var i = 0; i < data.data.length; i ++){
              seeds.push(data.data[i].name); // 年份
            }
            this.dataOptions1 = seeds;
          } else {
            console.log("访问失败！");
          }
        })
      },
      getDataList1 () { // 获取折线图数据
        this.getDataList2(); // 更新数据的同时更新另外两类数据
        this.getDataList3();
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('/seed-platform/seedyield/getSeedyield'),
          method: 'get',
          params: this.$http.adornParams({
            'seed_type': this.formData.data1,
          })
        }).then(({data}) => {
          if (data && data.code === 0) { // 获取请求成功
            const years = [];
            const yields = [];
            for(var i = 0; i < data.data.length; i ++){
              years.push(parseInt(data.data[i].year)); // 年份
              yields.push(data.data[i].yield);
            }
            this.lineChartOptions.xAxis.data = years;
            this.lineChartOptions.series[0].data = yields;
            this.renderLineChart();
          } else {
            console.log("访问失败！")
          }
        })
      },
      getDataList2 () { // 获取饼图图数据
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/seed-platform/plantingmethod/getSeedmethod'),
          method: 'get',
          params: this.$http.adornParams({
            'seed_type': this.formData.data1,
          })
        }).then(({data}) => {
          if (data && data.code === 0) { // 获取请求成功
            //console.log(data.data);
            const data1 = [];
            const data2 = [];
            for(var i = 0; i < data.data.length; i ++){
              data1.push(data.data[i].method); // 年份
              data2.push({value: data.data[i].proportion, name: data.data[i].method});
            }
            this.pieChartOptions.legend.data = data1;
            this.pieChartOptions.series[0].data = data2;
            this.renderPieChart();
          } else {
            console.log("访问失败！")
          }
        })
      },
      getDataList3(){ // 种子种植面积折线图
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/seed-platform/plantingarea/getYeararea'),
          method: 'get',
          params: this.$http.adornParams({
            'seed_type': this.formData.data1,
          })
        }).then(({data}) => {
          if (data && data.code === 0) { // 获取请求成功
            console.log(data.data);
            const years = [];
            const area = [];
            for(var i = 0; i < data.data.length; i ++){
              years.push(parseInt(data.data[i].year)); // 年份
              area.push(data.data[i].area);
            }
            this.lineChartOptions2.xAxis.data = years;
            this.lineChartOptions2.series[0].data = area;
            this.renderLineChart2();
          } else {
            console.log("访问失败！")
          }
        })
      },
      renderBarChart() { // 条形图（不同种子的年产量图）
        const chart = echarts.init(this.$refs.barChart);
        chart.setOption(this.barChartOptions);
        chart.on('click', this.handleBarChartClick);
      },
      renderLineChart() { // 折线图（种子的年产量图）
        const chart = echarts.init(this.$refs.lineChart);
        chart.setOption(this.lineChartOptions);
        chart.on('click', this.handleLineChartClick);
      },
      renderLineChart2() { // 折线图（种子的年产量图）
        const chart = echarts.init(this.$refs.lineChart2);
        chart.setOption(this.lineChartOptions2);
        chart.on('click', this.handleLineChartClick);
      },
      renderPieChart() { // 饼图（种子的种植方式占比）
        const chart = echarts.init(this.$refs.pieChart);
        chart.setOption(this.pieChartOptions);
        chart.on('click', this.handleLineChartClick);
      },
      handleBarChartClick(params) {
        this.barChartData = {
          name: params.name,
          value: params.value,
        };
      },
      handleLineChartClick(params) {
        this.lineChartData = {
          name: params.name,
          value: params.value,
        };
      },

    },
  };
</script>

<style>
  .dashboard {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .dashboard2 {
    display: flex;
    flex-wrap: wrap;
  }
  .chart {
    width: 300px;
    height: 300px;
    margin-bottom: 20px;
  }
  .data {
    width: 300px;
    height: 300px;
  }
</style>
