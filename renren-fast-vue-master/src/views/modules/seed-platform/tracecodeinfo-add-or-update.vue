<template>
  <el-dialog
    :title="!dataForm.traceCodeId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="防伪码" prop="seedTraceCode">
      <el-input v-model="dataForm.seedTraceCode" placeholder="防伪码"></el-input>
    </el-form-item>
    <el-form-item label="防伪码地址" prop="seedTraceUrl">
      <el-input v-model="dataForm.seedTraceUrl" placeholder="防伪码地址"></el-input>
    </el-form-item>
    <el-form-item label="被扫次数" prop="codeScanCount">
      <el-input v-model="dataForm.codeScanCount" placeholder="被扫次数"></el-input>
    </el-form-item>
    <el-form-item label="预警等级" prop="warningLevel">
<!--      <el-input v-model="dataForm.warningLevel" placeholder="预警等级"></el-input>-->
      <el-select v-model="dataForm.warningLevel" placeholder="预警等级">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <!--      <el-input v-model="dataForm.traceDate" placeholder=""></el-input>-->
        <el-date-picker
          v-model="dataForm.createTime"
          type="datetime"
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
        options: [{
          value: '蓝色',
          label: '蓝色'
        }, {
          value: '黄色',
          label: '黄色'
        }, {
          value: '橙色',
          label: '橙色'
        }, {
          value: '红色',
          label: '红色'
        }],

        visible: false,
        dataForm: {
          traceCodeId: 0,
          seedTraceCode: '',
          seedTraceUrl: '',
          codeScanCount: '',
          warningLevel: '',
          createTime: ''
        },
        dataRule: {
          seedTraceCode: [
            { required: true, message: '防伪码不能为空', trigger: 'blur' }
          ],
          seedTraceUrl: [
            { required: true, message: '防伪码地址不能为空', trigger: 'blur' }
          ],
          codeScanCount: [
            { required: true, message: '被扫次数不能为空', trigger: 'blur' }
          ],
          warningLevel: [
            { required: true, message: '预警等级不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.traceCodeId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.traceCodeId) {
            this.$http({
              url: this.$http.adornUrl(`/seed-platform/tracecodeinfo/info/${this.dataForm.traceCodeId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.seedTraceCode = data.traceCodeInfo.seedTraceCode
                this.dataForm.seedTraceUrl = data.traceCodeInfo.seedTraceUrl
                this.dataForm.codeScanCount = data.traceCodeInfo.codeScanCount
                this.dataForm.warningLevel = data.traceCodeInfo.warningLevel
                this.dataForm.createTime = data.traceCodeInfo.createTime
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
              url: this.$http.adornUrl(`/seed-platform/tracecodeinfo/${!this.dataForm.traceCodeId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'traceCodeId': this.dataForm.traceCodeId || undefined,
                'seedTraceCode': this.dataForm.seedTraceCode,
                'seedTraceUrl': this.dataForm.seedTraceUrl,
                'codeScanCount': this.dataForm.codeScanCount,
                'warningLevel': this.dataForm.warningLevel,
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
