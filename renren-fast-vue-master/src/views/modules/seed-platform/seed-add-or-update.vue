<template>
  <el-dialog
    :title="!dataForm.seedId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="seedName">
      <el-input v-model="dataForm.seedName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="description">
      <el-input v-model="dataForm.description" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="origin">
      <el-input v-model="dataForm.origin" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="traceCode">
      <el-input v-model="dataForm.traceCode" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createdAt">
      <el-input v-model="dataForm.createdAt" placeholder=""></el-input>
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
          seedId: 0,
          seedName: '',
          description: '',
          origin: '',
          traceCode: '',
          createdAt: ''
        },
        dataRule: {
          seedName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          origin: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          traceCode: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createdAt: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.seedId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.seedId) {
            this.$http({
              url: this.$http.adornUrl(`/seed-platform/seed/info/${this.dataForm.seedId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.seedName = data.seed.seedName
                this.dataForm.description = data.seed.description
                this.dataForm.origin = data.seed.origin
                this.dataForm.traceCode = data.seed.traceCode
                this.dataForm.createdAt = data.seed.createdAt
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
              url: this.$http.adornUrl(`/seed-platform/seed/${!this.dataForm.seedId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'seedId': this.dataForm.seedId || undefined,
                'seedName': this.dataForm.seedName,
                'description': this.dataForm.description,
                'origin': this.dataForm.origin,
                'traceCode': this.dataForm.traceCode,
                'createdAt': this.dataForm.createdAt
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
