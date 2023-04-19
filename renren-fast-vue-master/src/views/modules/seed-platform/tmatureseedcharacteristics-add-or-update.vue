<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="种子大小" prop="seedSize">
      <el-input v-model="dataForm.seedSize" placeholder="种子大小"></el-input>
    </el-form-item>
    <el-form-item label="种子颜色" prop="seedColor">
      <el-input v-model="dataForm.seedColor" placeholder="种子颜色"></el-input>
    </el-form-item>
    <el-form-item label="种子硬度" prop="seedHard">
      <el-input v-model="dataForm.seedHard" placeholder="种子硬度"></el-input>
    </el-form-item>
    <el-form-item label="种子含水量" prop="seedWaterContent">
      <el-input v-model="dataForm.seedWaterContent" placeholder="种子含水量"></el-input>
    </el-form-item>
    <el-form-item label="种子产量" prop="seedProduction">
      <el-input v-model="dataForm.seedProduction" placeholder="种子产量"></el-input>
    </el-form-item>
    <el-form-item label="种子油脂含量" prop="seedFatContent">
      <el-input v-model="dataForm.seedFatContent" placeholder="种子油脂含量"></el-input>
    </el-form-item>
    <el-form-item label="种子的耐储存性" prop="seedStorageResistence">
      <el-input v-model="dataForm.seedStorageResistence" placeholder="种子的耐储存性"></el-input>
    </el-form-item>
    <el-form-item label="种子蛋白质含量" prop="seedProteinContent">
      <el-input v-model="dataForm.seedProteinContent" placeholder="种子蛋白质含量"></el-input>
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
          seedHard: '',
          seedWaterContent: '',
          seedProduction: '',
          seedFatContent: '',
          seedStorageResistence: '',
          seedProteinContent: ''
        },
        dataRule: {
          seedSize: [
            { required: true, message: '种子大小不能为空', trigger: 'blur' }
          ],
          seedColor: [
            { required: true, message: '种子颜色不能为空', trigger: 'blur' }
          ],
          seedHard: [
            { required: true, message: '种子硬度不能为空', trigger: 'blur' }
          ],
          seedWaterContent: [
            { required: true, message: '种子含水量不能为空', trigger: 'blur' }
          ],
          seedProduction: [
            { required: true, message: '种子产量不能为空', trigger: 'blur' }
          ],
          seedFatContent: [
            { required: true, message: '种子油脂含量不能为空', trigger: 'blur' }
          ],
          seedStorageResistence: [
            { required: true, message: '种子的耐储存性不能为空', trigger: 'blur' }
          ],
          seedProteinContent: [
            { required: true, message: '种子蛋白质含量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/seed-platform/tmatureseedcharacteristics/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.seedSize = data.tMatureSeedCharacteristics.seedSize
                this.dataForm.seedColor = data.tMatureSeedCharacteristics.seedColor
                this.dataForm.seedHard = data.tMatureSeedCharacteristics.seedHard
                this.dataForm.seedWaterContent = data.tMatureSeedCharacteristics.seedWaterContent
                this.dataForm.seedProduction = data.tMatureSeedCharacteristics.seedProduction
                this.dataForm.seedFatContent = data.tMatureSeedCharacteristics.seedFatContent
                this.dataForm.seedStorageResistence = data.tMatureSeedCharacteristics.seedStorageResistence
                this.dataForm.seedProteinContent = data.tMatureSeedCharacteristics.seedProteinContent
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
              url: this.$http.adornUrl(`/seed-platform/tmatureseedcharacteristics/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'seedSize': this.dataForm.seedSize,
                'seedColor': this.dataForm.seedColor,
                'seedHard': this.dataForm.seedHard,
                'seedWaterContent': this.dataForm.seedWaterContent,
                'seedProduction': this.dataForm.seedProduction,
                'seedFatContent': this.dataForm.seedFatContent,
                'seedStorageResistence': this.dataForm.seedStorageResistence,
                'seedProteinContent': this.dataForm.seedProteinContent
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
