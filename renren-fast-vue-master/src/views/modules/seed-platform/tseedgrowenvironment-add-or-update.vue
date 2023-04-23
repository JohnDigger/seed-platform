<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="生长气温" prop="growTemp">
      <el-input v-model="dataForm.growTemp" placeholder="生长气温"></el-input>
    </el-form-item>
    <el-form-item label="生长湿度" prop="growHumidity">
      <el-input v-model="dataForm.growHumidity" placeholder="生长湿度"></el-input>
    </el-form-item>
    <el-form-item label="生长光照" prop="growLight">
      <el-input v-model="dataForm.growLight" placeholder="生长光照"></el-input>
    </el-form-item>
    <el-form-item label="生长土壤PH值" prop="growSoilPh">
      <el-input v-model="dataForm.growSoilPh" placeholder="生长土壤PH值"></el-input>
    </el-form-item>
    <el-form-item label="土壤有机质含量" prop="growSoilOrganicContent">
      <el-input v-model="dataForm.growSoilOrganicContent" placeholder="土壤有机质含量"></el-input>
    </el-form-item>
    <el-form-item label="土壤质地" prop="growSoilQuality">
      <el-input v-model="dataForm.growSoilQuality" placeholder="土壤质地"></el-input>
    </el-form-item>
    <el-form-item label="土壤营养元素含量" prop="growSoilNutrientContent">
      <el-input v-model="dataForm.growSoilNutrientContent" placeholder="土壤营养元素含量"></el-input>
    </el-form-item>
      <el-form-item label="创建日期" prop="seedGrowthRate">
        <!--        <el-input v-model="dataForm.createTime" placeholder="创建日期"></el-input>-->
        <el-date-picker
          v-model="dataForm.createTime"
          type="datetime"
          placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          growTemp: '',
          growHumidity: '',
          growLight: '',
          growSoilPh: '',
          growSoilOrganicContent: '',
          growSoilQuality: '',
          growSoilNutrientContent: ''
        },
        dataRule: {
          growTemp: [
            { required: true, message: '生长气温不能为空', trigger: 'blur' }
          ],
          growHumidity: [
            { required: true, message: '生长湿度不能为空', trigger: 'blur' }
          ],
          growLight: [
            { required: true, message: '生长光照不能为空', trigger: 'blur' }
          ],
          growSoilPh: [
            { required: true, message: '生长土壤PH值不能为空', trigger: 'blur' }
          ],
          growSoilOrganicContent: [
            { required: true, message: '土壤有机质含量不能为空', trigger: 'blur' }
          ],
          growSoilQuality: [
            { required: true, message: '土壤质地不能为空', trigger: 'blur' }
          ],
          growSoilNutrientContent: [
            { required: true, message: '土壤营养元素含量不能为空', trigger: 'blur' }
          ],
          createTime: [
          {required: true, message: '创建日期不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/seed-platform/tseedgrowenvironment/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.growTemp = data.tSeedGrowEnvironment.growTemp
                this.dataForm.growHumidity = data.tSeedGrowEnvironment.growHumidity
                this.dataForm.growLight = data.tSeedGrowEnvironment.growLight
                this.dataForm.growSoilPh = data.tSeedGrowEnvironment.growSoilPh
                this.dataForm.growSoilOrganicContent = data.tSeedGrowEnvironment.growSoilOrganicContent
                this.dataForm.growSoilQuality = data.tSeedGrowEnvironment.growSoilQuality
                this.dataForm.growSoilNutrientContent = data.tSeedGrowEnvironment.growSoilNutrientContent
                this.dataForm.createTime = data.tPlantCharacteristics.createTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/seed-platform/tseedgrowenvironment/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'growTemp': this.dataForm.growTemp,
                'growHumidity': this.dataForm.growHumidity,
                'growLight': this.dataForm.growLight,
                'growSoilPh': this.dataForm.growSoilPh,
                'growSoilOrganicContent': this.dataForm.growSoilOrganicContent,
                'growSoilQuality': this.dataForm.growSoilQuality,
                'growSoilNutrientContent': this.dataForm.growSoilNutrientContent,
                'createTime': this.dataForm.createTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
