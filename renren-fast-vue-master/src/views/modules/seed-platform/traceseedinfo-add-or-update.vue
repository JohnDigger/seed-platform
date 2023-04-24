<template>
  <el-dialog
    :title="!dataForm.seedId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="种子名称" prop="seedName">
      <el-input v-model="dataForm.seedName" placeholder="种子名称"></el-input>
    </el-form-item>
    <el-form-item label="品类" prop="seedCategory">
      <el-input v-model="dataForm.seedCategory" placeholder="品类"></el-input>
    </el-form-item>
    <el-form-item label="产地" prop="seedSource">
      <el-input v-model="dataForm.seedSource" placeholder="产地"></el-input>
    </el-form-item>
    <el-form-item label="生产商" prop="seedProducer">
      <el-input v-model="dataForm.seedProducer" placeholder="生产商"></el-input>
    </el-form-item>
    <el-form-item label="地址" prop="seedArea">
      <el-input v-model="dataForm.seedArea" placeholder="地址"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop="telephone">
      <el-input v-model="dataForm.telephone" placeholder="联系电话"></el-input>
    </el-form-item>
    <el-form-item label="审定编号" prop="approvalNumber">
      <el-input v-model="dataForm.approvalNumber" placeholder="审定编号"></el-input>
    </el-form-item>
    <el-form-item label="生产日期" prop="productionDate">
<!--      <el-input v-model="dataForm.productionDate" placeholder="生产日期"></el-input>-->
      <el-date-picker
        v-model="dataForm.productionDate"
        type="datetime"
        placeholder="生产日期">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="创建日期" prop="createDate">
<!--      <el-input v-model="dataForm.createDate" placeholder="创建日期"></el-input>-->
      <el-date-picker
        v-model="dataForm.createDate"
        type="datetime"
        placeholder="创建日期">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="防伪码" prop="seedTraceCode">
      <el-input v-model="dataForm.seedTraceCode" placeholder="防伪码"></el-input>
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
          seedCategory: '',
          seedSource: '',
          seedProducer: '',
          seedArea: '',
          telephone: '',
          approvalNumber: '',
          productionDate: '',
          createDate: '',
          seedTraceCode: ''
        },
        dataRule: {
          seedName: [
            { required: true, message: '种子名称不能为空', trigger: 'blur' }
          ],
          seedCategory: [
            { required: true, message: '品类不能为空', trigger: 'blur' }
          ],
          seedSource: [
            { required: true, message: '产地不能为空', trigger: 'blur' }
          ],
          seedProducer: [
            { required: true, message: '生产商不能为空', trigger: 'blur' }
          ],
          seedArea: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          telephone: [
            { required: true, message: '联系电话不能为空', trigger: 'blur' }
          ],
          approvalNumber: [
            { required: true, message: '审定编号不能为空', trigger: 'blur' }
          ],
          productionDate: [
            { required: true, message: '生产日期不能为空', trigger: 'blur' }
          ],
          createDate: [
            { required: true, message: '创建日期不能为空', trigger: 'blur' }
          ],
          // seedTraceCode: [
          //   { required: true, message: '防伪码不能为空', trigger: 'blur' }
          // ]
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
              url: this.$http.adornUrl(`/seed-platform/traceseedinfo/info/${this.dataForm.seedId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.seedName = data.traceSeedInfo.seedName
                this.dataForm.seedCategory = data.traceSeedInfo.seedCategory
                this.dataForm.seedSource = data.traceSeedInfo.seedSource
                this.dataForm.seedProducer = data.traceSeedInfo.seedProducer
                this.dataForm.seedArea = data.traceSeedInfo.seedArea
                this.dataForm.telephone = data.traceSeedInfo.telephone
                this.dataForm.approvalNumber = data.traceSeedInfo.approvalNumber
                this.dataForm.productionDate = data.traceSeedInfo.productionDate
                this.dataForm.createDate = data.traceSeedInfo.createDate
                this.dataForm.seedTraceCode = data.traceSeedInfo.seedTraceCode
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
              url: this.$http.adornUrl(`/seed-platform/traceseedinfo/${!this.dataForm.seedId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'seedId': this.dataForm.seedId || undefined,
                'seedName': this.dataForm.seedName,
                'seedCategory': this.dataForm.seedCategory,
                'seedSource': this.dataForm.seedSource,
                'seedProducer': this.dataForm.seedProducer,
                'seedArea': this.dataForm.seedArea,
                'telephone': this.dataForm.telephone,
                'approvalNumber': this.dataForm.approvalNumber,
                'productionDate': this.dataForm.productionDate,
                'createDate': this.dataForm.createDate,
                'seedTraceCode': this.dataForm.seedTraceCode
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
