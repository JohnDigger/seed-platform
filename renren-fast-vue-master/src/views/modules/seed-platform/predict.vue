<template>
  <div>
    <el-row :gutter="0">
      <el-col :span="12">
        <div class="left-side">
          <el-row>
            <el-col :span="24">
              <div>
                <div><h1 class="title left-up">种子指标</h1></div>
                <div>
                  <el-row>
                    <el-col :span="12">
                      <div><h2 class="left-up">选择新品</h2></div>
                      <el-select v-model="value" placeholder="请选择种子" class="left-up" @change="handleChange">
                        <el-option
                          v-for="(item,index) in options"
                          :key="index"
                          :label="item.label"
                          :value="item.value"
                        >
                        </el-option>
                      </el-select>
                    </el-col>
                    <el-col :span="12">
                      <div>
                        <h2 class="left-up">
                          点击确认获取结果
                        </h2>
                      </div>
                      <el-button type="primary" plain class="left-up" style="margin-top: 10px" @click="predict()">计算结果</el-button>
                    </el-col>
                  </el-row>
                </div>
              </div>

              <div>
                <el-row>
                  <el-col :span="12">
                    <div><h2 class="left-up">种子生长周期:{{ date_duration }}/月</h2></div>
                    <el-slider v-model="date_duration" class="left-up" :min="1"
                               :max="24"
                               :step="1"></el-slider>
                  </el-col>
                  <el-col :span="12">
                    <div><h2 class="left-up">种子成熟硬度</h2></div>
                    <el-radio-group v-model="seedHard" class="left-up">
                      <el-radio label="软"></el-radio>
                      <el-radio label="硬"></el-radio>
                    </el-radio-group>
                  </el-col>
                </el-row>
              </div>
              <div>
                <el-row>
                  <el-col :span="12">
                    <div><h2 class="left-up">种子生长气温</h2></div>
                    <el-input v-model="seed_temp" class="left-input" placeholder="摄氏度"></el-input>
                  </el-col>
                  <el-col :span="12">
                    <div><h2 class="left-up">成熟种子含水量</h2></div>
                    <el-input v-model="seed_water" class="left-input" placeholder="百分比"></el-input>
                  </el-col>
                </el-row>
              </div>

              <div>
                <el-row>
                  <el-col :span="12">
                    <div><h2 class="left-up">种子生长湿度</h2></div>
                    <el-input v-model="seed_humidity" class="left-input" placeholder="摄氏度"></el-input>
                  </el-col>
                  <el-col :span="12">
                    <div><h2 class="left-up">成熟种子大小</h2></div>
                    <el-input v-model="mature_size" class="left-input" placeholder="摄氏度"></el-input>
                    <!--              <el-radio-group v-model="mature_size" class="left-up">-->
                    <!--                <el-radio label="大"></el-radio>-->
                    <!--                <el-radio label="中"></el-radio>-->
                    <!--                <el-radio label="小"></el-radio>-->
                    <!--              </el-radio-group>-->
                  </el-col>
                </el-row>
              </div>

              <div>
                <el-row>
                  <el-col :span="12">
                    <div><h2 class="left-up">种子生长光照</h2></div>
                    <el-input v-model="seed_light" class="left-input" placeholder="摄氏度"></el-input>
                  </el-col>
                  <el-col :span="12">
                    <div><h2 class="left-up">成熟种子产量</h2></div>
                    <el-input v-model="mature_production" class="left-input" placeholder="摄氏度"></el-input>
                  </el-col>
                </el-row>
              </div>

              <div>
                <el-row>
                  <el-col :span="12">
                    <div><h2 class="left-up">种子生长土壤ph值</h2></div>
                    <el-input v-model="seed_ph" class="left-input" placeholder="摄氏度"></el-input>
                  </el-col>
                  <el-col :span="12">
                    <div><h2 class="left-up">成熟种子油脂含量</h2></div>
                    <el-input v-model="seed_oil" class="left-input" placeholder="摄氏度"></el-input>
                  </el-col>
                </el-row>
              </div>
              <div style="margin-bottom: 20px">
                <el-row>
                  <el-col :span="12">
                    <div><h2 class="left-up">种子生土壤有机质含量</h2></div>
                    <el-input v-model="organic_content" class="left-input" placeholder="摄氏度"></el-input>
                  </el-col>
                  <el-col :span="12">
                    <div><h2 class="left-up">成熟种子耐储性</h2></div>
                    <el-radio-group v-model="resistence" class="left-up">
                      <el-radio label="高"></el-radio>
                      <el-radio label="中"></el-radio>
                      <el-radio label="低"></el-radio>
                    </el-radio-group>
                  </el-col>
                </el-row>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="right-side">
          <div
            style="display: flex;justify-content: center;align-items: center;flex-direction: column;height: 100vh;color: #8a979e">
            <div><h1>新品良品率为：{{successRate*100}}%</h1></div>
            <div><h1>新品失败风险为：{{ (1-successRate)*100}}%</h1></div>
            <div><p>温馨提示:本工具仅用于初步风险评估，最终结果可能会收到多种因素影响</p></div>
          </div>

        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'predict',
  data() {
    return {
      date_duration: 0,
      soilQuality: '',
      seedHard: '',
      seedSize: 0.0,
      input: '',
      value: '',
      options: null,
      seed_temp: 0.0,
      seed_water: 0.0,
      seed_humidity: 0.0,
      mature_size: 0.0,
      seed_light: 0.0,
      mature_production: 0.0,
      seed_ph: 0,
      seed_oil: 0.0,
      organic_content: 0,
      resistence: '',
      successRate: 0
    }
  },
  methods: {
    init() {
      this.$nextTick(() => {
        this.$http({
          url: this.$http.adornUrl(`/seed-platform/tseedcharacteristics/getAllSeedName`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
            if (data && data.code === 0) {
              let obj = {}
              let arr = []
              data.data.forEach(ele => {
                arr.push({
                    value: ele.seedName,
                    label: ele.seedName
                  }
                )
              })
              this.options = arr
              if (arr.length > 0) {
                this.value = arr[0].value; // 设置默认选中项
                this.data_render(this.value)
              }
            }
          }
        )

      })
    },
    data_render(params) {
      this.$http({
        url: this.$http.adornUrl(`/seed-platform/tseedcharacteristics/selectSeedByName`),
        method: 'get',
        params: this.$http.adornParams({seedName: params})
      }).then(({data}) => {
        this.seed_temp = data.data.tseedGrowEnvironmentEntity.growTemp
        this.seed_water = data.data.tmatureSeedCharacteristicsEntity.seedWaterContent
        this.seed_humidity = data.data.tseedGrowEnvironmentEntity.growHumidity
        this.mature_size = data.data.tmatureSeedCharacteristicsEntity.seedSize
        this.seed_light = data.data.tseedGrowEnvironmentEntity.growLight
        this.mature_production = data.data.tmatureSeedCharacteristicsEntity.seedProduction
        this.seed_ph = data.data.tseedGrowEnvironmentEntity.growSoilPh
        this.seed_oil = data.data.tmatureSeedCharacteristicsEntity.seedFatContent
        this.organic_content = data.data.tseedGrowEnvironmentEntity.growSoilOrganicContent
        this.resistence = data.data.tmatureSeedCharacteristicsEntity.seedStorageResistence
        this.seedHard = data.data.tmatureSeedCharacteristicsEntity.seedHard
        this.date_duration = Number(data.data.tplantCharacteristicsEntity.growCycle)
      })
    },
    handleChange(value) {
      this.data_render(value)
    },
    predict(){
      this.$http({
        url: this.$http.adornUrl(`/seed-platform/tseedcharacteristics/calculateSeed`),
        method: 'get',
        params: this.$http.adornParams({seed_temp:this.seed_temp,seed_water:this.seed_water,seed_humidity:this.seed_humidity,mature_size:this.mature_size,seed_light:this.seed_light,
          mature_production:this.mature_production,seed_ph:this.seed_ph,seed_oil:this.seed_oil,organic_content:this.organic_content,resistence:this.resistence,seedHard:this.seedHard,
          date_duration:this.date_duration})
      }).then(({data}) => {
        this.successRate = data
      })
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>
.left-side {
  background-color: #d3d3d3; /* 灰色 */
  width: 100%;
}

.right-side {
  background-color: #ffffff; /* 白色 */
  height: 100vh;
  width: 100%;
}

.left {
  width: 100%;
}

.right {
  width: 100%;
}

.title {
  font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
  font-weight: 700;
  font-style: normal;
  color: #3E8EF7;
}

.left-up {
  margin-left: 20px;
  /*margin-top: 20px;*/
  padding-top: 10px;
}

.left-input {
  margin-left: 20px;
  max-width: 90%;
}
</style>
