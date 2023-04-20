<template>
  <el-dialog
    :title="!dataForm.seedId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="种子名称" prop="seedName">
      <el-input v-model="dataForm.seedName" placeholder="种子名称"></el-input>
    </el-form-item>
    <el-form-item label="种子描述" prop="description">
      <el-input v-model="dataForm.description" placeholder="种子描述"></el-input>
    </el-form-item>
    <el-form-item label="种子来源" prop="origin">
      <el-input v-model="dataForm.origin" placeholder="种子来源"></el-input>
    </el-form-item>
    <el-form-item label="溯源二维码" prop="traceCode">
      <el-input v-model="dataForm.traceCode" placeholder="溯源二维码"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createdAt">
<!--      <el-input v-model="dataForm.createdAt" placeholder="创建时间"></el-input>-->
      <el-date-picker
        v-model="dataForm.createdAt"
        type="date"
        placeholder="创建时间">
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
          seedId: 0,
          seedName: '',
          description: '',
          origin: '',
          traceCode: '',
          createdAt: ''
        },
        dataRule: {
          seedName: [
            { required: true, message: '种子名称不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '种子描述不能为空', trigger: 'blur' }
          ],
          origin: [
            { required: true, message: '种子来源不能为空', trigger: 'blur' }
          ],
          traceCode: [
            { required: true, message: '溯源二维码不能为空', trigger: 'blur' }
          ],
          createdAt: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
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
