<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="名称"></el-input>
    </el-form-item>
    <el-form-item label="品种" prop="varieties">
      <el-input v-model="dataForm.varieties" placeholder="品种"></el-input>
    </el-form-item>
    <el-form-item label="种属" prop="speciesGenera">
      <el-input v-model="dataForm.speciesGenera" placeholder="种属"></el-input>
    </el-form-item>
    <el-form-item label="种子大小" prop="size">
      <el-input v-model="dataForm.size" placeholder="种子大小"></el-input>
    </el-form-item>
    <el-form-item label="种子颜色" prop="color">
      <el-input v-model="dataForm.color" placeholder="种子颜色"></el-input>
    </el-form-item>
    <el-form-item label="种子产地" prop="origin">
      <el-input v-model="dataForm.origin" placeholder="种子产地"></el-input>
    </el-form-item>
    <el-form-item label="种子品种" prop="variety">
      <el-input v-model="dataForm.variety" placeholder="种子品种"></el-input>
    </el-form-item>
    <el-form-item label="种植方式" prop="plantingMethod">
      <el-input v-model="dataForm.plantingMethod" placeholder="种植方式"></el-input>
    </el-form-item>
    <el-form-item label="主要种植区域" prop="plantingArea">
      <el-input v-model="dataForm.plantingArea" placeholder="主要种植区域"></el-input>
    </el-form-item>
    <el-form-item label="播种时间" prop="sowingTime">
<!--      <el-input v-model="dataForm.sowingTime" placeholder="播种时间"></el-input>-->
      <el-date-picker
        v-model="dataForm.sowingTime"
        type="date"
        placeholder="播种时间">
      </el-date-picker>
    </el-form-item>
      <el-form-item label="生长周期" prop="growthCycle">
        <el-input v-model="dataForm.growthCycle" placeholder="生长周期"></el-input>
      </el-form-item>
    <el-form-item label="环境温度（℃）" prop="environmentTemperature">
      <el-input v-model="dataForm.environmentTemperature" placeholder="环境温度（℃）"></el-input>
    </el-form-item>
    <el-form-item label="平均产量" prop="averageYield">
      <el-input v-model="dataForm.averageYield" placeholder="平均产量"></el-input>
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
          name: '',
          varieties: '',
          speciesGenera: '',
          size: '',
          color: '',
          origin: '',
          variety: '',
          plantingMethod: '',
          plantingArea: '',
          sowingTime: '',
          growthCycle: '',
          environmentTemperature: '',
          averageYield: ''
        },
        dataRule: {
          name: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          varieties: [
            { required: true, message: '品种不能为空', trigger: 'blur' }
          ],
          speciesGenera: [
            { required: true, message: '种属不能为空', trigger: 'blur' }
          ],
          size: [
            { required: true, message: '种子大小不能为空', trigger: 'blur' }
          ],
          color: [
            { required: true, message: '种子颜色不能为空', trigger: 'blur' }
          ],
          origin: [
            { required: true, message: '种子产地不能为空', trigger: 'blur' }
          ],
          variety: [
            { required: true, message: '种子品种不能为空', trigger: 'blur' }
          ],
          plantingMethod: [
            { required: true, message: '种植方式不能为空', trigger: 'blur' }
          ],
          plantingArea: [
            { required: true, message: '主要种植区域不能为空', trigger: 'blur' }
          ],
          sowingTime: [
            { required: true, message: '播种时间不能为空', trigger: 'blur' }
          ],
          growthCycle: [
            { required: true, message: '生长周期不能为空', trigger: 'blur' }
          ],
          environmentTemperature: [
            { required: true, message: '环境温度（℃）不能为空', trigger: 'blur' }
          ],
          averageYield: [
            { required: true, message: '平均产量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/seed-platform/seedprocessmanagement/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.seedProcessManagement.name
                this.dataForm.varieties = data.seedProcessManagement.varieties
                this.dataForm.speciesGenera = data.seedProcessManagement.speciesGenera
                this.dataForm.size = data.seedProcessManagement.size
                this.dataForm.color = data.seedProcessManagement.color
                this.dataForm.origin = data.seedProcessManagement.origin
                this.dataForm.variety = data.seedProcessManagement.variety
                this.dataForm.plantingMethod = data.seedProcessManagement.plantingMethod
                this.dataForm.plantingArea = data.seedProcessManagement.plantingArea
                this.dataForm.sowingTime = data.seedProcessManagement.sowingTime
                this.dataForm.growthCycle = data.seedProcessManagement.growthCycle
                this.dataForm.environmentTemperature = data.seedProcessManagement.environmentTemperature
                this.dataForm.averageYield = data.seedProcessManagement.averageYield
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
              url: this.$http.adornUrl(`/seed-platform/seedprocessmanagement/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'varieties': this.dataForm.varieties,
                'speciesGenera': this.dataForm.speciesGenera,
                'size': this.dataForm.size,
                'color': this.dataForm.color,
                'origin': this.dataForm.origin,
                'variety': this.dataForm.variety,
                'plantingMethod': this.dataForm.plantingMethod,
                'plantingArea': this.dataForm.plantingArea,
                'sowingTime': this.dataForm.sowingTime,
                'growthCycle': this.dataForm.growthCycle,
                'environmentTemperature': this.dataForm.environmentTemperature,
                'averageYield': this.dataForm.averageYield
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
