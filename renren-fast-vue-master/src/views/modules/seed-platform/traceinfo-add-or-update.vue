<template>
  <el-dialog
    :title="!dataForm.traceId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="溯源种子" prop="traceSeedName">
      <el-input v-model="dataForm.traceSeedName" placeholder="溯源种子"></el-input>
    </el-form-item>
    <el-form-item label="扫码日期" prop="traceDate">
<!--      <el-input v-model="dataForm.traceDate" placeholder=""></el-input>-->
      <el-date-picker
        v-model="dataForm.traceDate"
        type="datetime"
        placeholder="扫码日期">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="扫码地点" prop="traceArea">
      <el-input v-model="dataForm.traceArea" placeholder=""></el-input>
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
          traceId: 0,
          traceSeedName: '',
          traceDate: '',
          traceArea: ''
        },
        dataRule: {
          traceSeedName: [
            { required: true, message: '溯源种子不能为空', trigger: 'blur' }
          ],
          traceDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          traceArea: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.traceId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.traceId) {
            this.$http({
              url: this.$http.adornUrl(`/seed-platform/traceinfo/info/${this.dataForm.traceId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.traceSeedName = data.traceInfo.traceSeedName
                this.dataForm.traceDate = data.traceInfo.traceDate
                this.dataForm.traceArea = data.traceInfo.traceArea
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
              url: this.$http.adornUrl(`/seed-platform/traceinfo/${!this.dataForm.traceId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'traceId': this.dataForm.traceId || undefined,
                'traceSeedName': this.dataForm.traceSeedName,
                'traceDate': this.dataForm.traceDate,
                'traceArea': this.dataForm.traceArea
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
