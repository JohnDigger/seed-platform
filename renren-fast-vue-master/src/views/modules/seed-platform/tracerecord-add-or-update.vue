<template>
  <el-dialog
    :title="!dataForm.recordId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="种子id" prop="seedId">
      <el-input v-model="dataForm.seedId" placeholder="种子id"></el-input>
    </el-form-item>
    <el-form-item label="溯源时间" prop="traceDate">
      <el-input v-model="dataForm.traceDate" placeholder="溯源时间"></el-input>
    </el-form-item>
    <el-form-item label="溯源信息表" prop="traceInfo">
      <el-input v-model="dataForm.traceInfo" placeholder="溯源信息表"></el-input>
    </el-form-item>
    <el-form-item label="溯源描述" prop="tracePerson">
      <el-input v-model="dataForm.tracePerson" placeholder="溯源描述"></el-input>
    </el-form-item>
    <el-form-item label="溯源创建时间" prop="createdAt">
      <el-input v-model="dataForm.createdAt" placeholder="溯源创建时间"></el-input>
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
          recordId: 0,
          seedId: '',
          traceDate: '',
          traceInfo: '',
          tracePerson: '',
          createdAt: ''
        },
        dataRule: {
          seedId: [
            { required: true, message: '种子id不能为空', trigger: 'blur' }
          ],
          traceDate: [
            { required: true, message: '溯源时间不能为空', trigger: 'blur' }
          ],
          traceInfo: [
            { required: true, message: '溯源信息表不能为空', trigger: 'blur' }
          ],
          tracePerson: [
            { required: true, message: '溯源描述不能为空', trigger: 'blur' }
          ],
          createdAt: [
            { required: true, message: '溯源创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.recordId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.recordId) {
            this.$http({
              url: this.$http.adornUrl(`/seed-platform/tracerecord/info/${this.dataForm.recordId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.seedId = data.traceRecord.seedId
                this.dataForm.traceDate = data.traceRecord.traceDate
                this.dataForm.traceInfo = data.traceRecord.traceInfo
                this.dataForm.tracePerson = data.traceRecord.tracePerson
                this.dataForm.createdAt = data.traceRecord.createdAt
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
              url: this.$http.adornUrl(`/seed-platform/tracerecord/${!this.dataForm.recordId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'recordId': this.dataForm.recordId || undefined,
                'seedId': this.dataForm.seedId,
                'traceDate': this.dataForm.traceDate,
                'traceInfo': this.dataForm.traceInfo,
                'tracePerson': this.dataForm.tracePerson,
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
