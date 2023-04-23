<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="种子名称" prop="seedName">
        <el-input v-model="dataForm.seedName" placeholder="种子名称"></el-input>
      </el-form-item>
    <el-form-item label="种子大小" prop="seedSize">
      <el-input v-model="dataForm.seedSize" placeholder="种子大小"></el-input>
    </el-form-item>
    <el-form-item label="种子颜色" prop="seedColor">
      <el-input v-model="dataForm.seedColor" placeholder="种子颜色"></el-input>
    </el-form-item>
    <el-form-item label="种子形状" prop="seedShape">
      <el-input v-model="dataForm.seedShape" placeholder="种子形状"></el-input>
    </el-form-item>
    <el-form-item label="种子重量" prop="seedWeight">
      <el-input v-model="dataForm.seedWeight" placeholder="种子重量"></el-input>
    </el-form-item>
    <el-form-item label="种子营养成分" prop="seedNutrient">
      <el-input v-model="dataForm.seedNutrient" placeholder="种子营养成分"></el-input>
    </el-form-item>
    <el-form-item label="种子表面特征" prop="seedSurface">
      <el-input v-model="dataForm.seedSurface" placeholder="种子表面特征"></el-input>
    </el-form-item>
    <el-form-item label="种子硬度" prop="seedHard">
      <el-input v-model="dataForm.seedHard" placeholder="种子硬度"></el-input>
    </el-form-item>
    <el-form-item label="种子含水量" prop="seedWaterContent">
      <el-input v-model="dataForm.seedWaterContent" placeholder="种子含水量"></el-input>
    </el-form-item>
    <el-form-item label="种子发芽率" prop="seedGerminationRate">
      <el-input v-model="dataForm.seedGerminationRate" placeholder="种子发芽率"></el-input>
    </el-form-item>
    <el-form-item label="种子生长速度" prop="seedGrowthRate">
      <el-input v-model="dataForm.seedGrowthRate" placeholder="种子生长速度"></el-input>
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
          seedSize: '',
          seedColor: '',
          seedShape: '',
          seedWeight: '',
          seedNutrient: '',
          seedSurface: '',
          seedHard: '',
          seedWaterContent: '',
          seedGerminationRate: '',
          seedGrowthRate: ''
        },
        dataRule: {
          seedSize: [
            { required: true, message: '种子大小不能为空', trigger: 'blur' }
          ],
          seedColor: [
            { required: true, message: '种子颜色不能为空', trigger: 'blur' }
          ],
          seedShape: [
            { required: true, message: '种子形状不能为空', trigger: 'blur' }
          ],
          seedWeight: [
            { required: true, message: '种子重量不能为空', trigger: 'blur' }
          ],
          seedNutrient: [
            { required: true, message: '种子营养成分不能为空', trigger: 'blur' }
          ],
          seedSurface: [
            { required: true, message: '种子表面特征不能为空', trigger: 'blur' }
          ],
          seedHard: [
            { required: true, message: '种子硬度不能为空', trigger: 'blur' }
          ],
          seedWaterContent: [
            { required: true, message: '种子含水量不能为空', trigger: 'blur' }
          ],
          seedGerminationRate: [
            { required: true, message: '种子发芽率不能为空', trigger: 'blur' }
          ],
          seedGrowthRate: [
            { required: true, message: '种子生长速度不能为空', trigger: 'blur' }
          ],
          seedName: [
            { required: true, message: '种子名称不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建日期不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/seed-platform/tseedcharacteristics/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.seedSize = data.tSeedCharacteristics.seedSize
                this.dataForm.seedColor = data.tSeedCharacteristics.seedColor
                this.dataForm.seedShape = data.tSeedCharacteristics.seedShape
                this.dataForm.seedWeight = data.tSeedCharacteristics.seedWeight
                this.dataForm.seedNutrient = data.tSeedCharacteristics.seedNutrient
                this.dataForm.seedSurface = data.tSeedCharacteristics.seedSurface
                this.dataForm.seedHard = data.tSeedCharacteristics.seedHard
                this.dataForm.seedWaterContent = data.tSeedCharacteristics.seedWaterContent
                this.dataForm.seedGerminationRate = data.tSeedCharacteristics.seedGerminationRate
                this.dataForm.seedGrowthRate = data.tSeedCharacteristics.seedGrowthRate
                this.dataForm.seedName = data.tSeedCharacteristics.seedName
                this.dataForm.createTime = data.tSeedCharacteristics.createTime
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
              url: this.$http.adornUrl(`/seed-platform/tseedcharacteristics/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'seedSize': this.dataForm.seedSize,
                'seedColor': this.dataForm.seedColor,
                'seedShape': this.dataForm.seedShape,
                'seedWeight': this.dataForm.seedWeight,
                'seedNutrient': this.dataForm.seedNutrient,
                'seedSurface': this.dataForm.seedSurface,
                'seedHard': this.dataForm.seedHard,
                'seedWaterContent': this.dataForm.seedWaterContent,
                'seedGerminationRate': this.dataForm.seedGerminationRate,
                'seedGrowthRate': this.dataForm.seedGrowthRate,
                'seedName': this.dataForm.seedName,
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
