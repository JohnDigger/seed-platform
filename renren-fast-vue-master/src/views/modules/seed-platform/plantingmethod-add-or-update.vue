<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="method">
      <el-input v-model="dataForm.method" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="seedType">
      <el-input v-model="dataForm.seedType" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="proportion">
      <el-input v-model="dataForm.proportion" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="yield">
      <el-input v-model="dataForm.yield" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="income">
      <el-input v-model="dataForm.income" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="region">
      <el-input v-model="dataForm.region" placeholder=""></el-input>
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
          method: '',
          seedType: '',
          proportion: '',
          yield: '',
          income: '',
          region: ''
        },
        dataRule: {
          method: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          seedType: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          proportion: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          yield: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          income: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/seed-platform/plantingmethod/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.method = data.plantingMethod.method
                this.dataForm.seedType = data.plantingMethod.seedType
                this.dataForm.proportion = data.plantingMethod.proportion
                this.dataForm.yield = data.plantingMethod.yield
                this.dataForm.income = data.plantingMethod.income
                this.dataForm.region = data.plantingMethod.region
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
              url: this.$http.adornUrl(`/seed-platform/plantingmethod/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'method': this.dataForm.method,
                'seedType': this.dataForm.seedType,
                'proportion': this.dataForm.proportion,
                'yield': this.dataForm.yield,
                'income': this.dataForm.income,
                'region': this.dataForm.region
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
