<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="备案种子品种" prop="filingSeedName">
      <el-input v-model="dataForm.filingSeedName" placeholder="备案种子品种"></el-input>
    </el-form-item>
    <el-form-item label="备案种子观察点(产地)" prop="filingSeedOrigin">
      <el-input v-model="dataForm.filingSeedOrigin" placeholder="备案种子观察点(产地)"></el-input>
    </el-form-item>
    <el-form-item label="备案种子生产商" prop="filingSeedProducer">
      <el-input v-model="dataForm.filingSeedProducer" placeholder="备案种子生产商"></el-input>
    </el-form-item>
    <el-form-item label="备案种子经销商" prop="filingSeedSeller">
      <el-input v-model="dataForm.filingSeedSeller" placeholder="备案种子经销商"></el-input>
    </el-form-item>
    <el-form-item label="备案种子采集时间" prop="filingSeedTime">
<!--      <el-input v-model="dataForm.filingSeedTime" placeholder="备案种子采集日期"></el-input>-->
      <el-date-picker
        v-model="dataForm.filingSeedTime"
        type="date"
        placeholder="选择日期时间"
        value-format="yyyy-MM-dd">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="备案种子价格" prop="filingSeedPrice">
      <el-input v-model="dataForm.filingSeedPrice" placeholder="备案种子价格"></el-input>
    </el-form-item>
    <el-form-item label="备案种子重量" prop="filingSeedWeight">
      <el-input v-model="dataForm.filingSeedWeight" placeholder="备案种子重量(100g/包)"></el-input>
    </el-form-item>
      <el-form-item label="商品图片" prop="timags">
        <div>
          <el-input v-if="dataForm.id" v-for="(img,index) in dataForm.timags  " :key="index"
                    v-text="'图'+(index+1)+':'+img"
                    disabled
                    clearable/>
        </div>
        <div>
          <el-input v-if="!dataForm.id" v-for="(imgs,item) in dataForm.timags  " :key="item"
                    v-text="'图'+(item+1)+':'+imgs"
                    disabled
                    clearable/>
        </div>

        <uploadImage @upload-image="handleImageUrlUpdate"></uploadImage>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import uploadImage from "./uploadImage";
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          filingSeedName: '',
          filingSeedOrigin: '',
          filingSeedProducer: '',
          filingSeedSeller: '',
          filingSeedTime: '',
          filingSeedPrice: '',
          filingSeedWeight: '',
          timags: []
        },
        dataRule: {
          filingSeedName: [
            { required: true, message: '备案种子品种不能为空', trigger: 'blur' }
          ],
          filingSeedOrigin: [
            { required: true, message: '备案种子观察点(产地)不能为空', trigger: 'blur' }
          ],
          filingSeedProducer: [
            { required: true, message: '备案种子生产商不能为空', trigger: 'blur' }
          ],
          filingSeedSeller: [
            { required: true, message: '备案种子经销商不能为空', trigger: 'blur' }
          ],
          filingSeedTime: [
            { required: true, message: '备案种子采集时间不能为空', trigger: 'blur' }
          ],
          filingSeedPrice: [
            { required: true, message: '备案种子价格不能为空', trigger: 'blur' }
          ],
          filingSeedWeight: [
            { required: true, message: '备案种子重量不能为空', trigger: 'blur' }
          ],
          timags: [
            {required: true, message: '商品图片不能为空', trigger: 'blur'}
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
              url: this.$http.adornUrl(`/seed-platform/tfilingseed/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.filingSeedName = data.tFilingSeed.filingSeedName
                this.dataForm.filingSeedOrigin = data.tFilingSeed.filingSeedOrigin
                this.dataForm.filingSeedProducer = data.tFilingSeed.filingSeedProducer
                this.dataForm.filingSeedSeller = data.tFilingSeed.filingSeedSeller
                this.dataForm.filingSeedTime = data.tFilingSeed.filingSeedTime
                this.dataForm.filingSeedPrice = data.tFilingSeed.filingSeedPrice
                this.dataForm.filingSeedWeight = data.tFilingSeed.filingSeedWeight
                data.tFilingseed.timags.forEach(ele => {
                  this.dataForm.timags.push(ele.imageUrl)
                })
              }
            })
          }
        })
      },
      handleImageUrlUpdate (imageUrls) {
        let arr = []
        imageUrls.forEach(ele => {
          arr.push(ele)
        })
        this.dataForm.timags = arr
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/seed-platform/tfilingseed/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'filingSeedName': this.dataForm.filingSeedName,
                'filingSeedOrigin': this.dataForm.filingSeedOrigin,
                'filingSeedProducer': this.dataForm.filingSeedProducer,
                'filingSeedSeller': this.dataForm.filingSeedSeller,
                'filingSeedTime': this.dataForm.filingSeedTime,
                'filingSeedPrice': this.dataForm.filingSeedPrice,
                'filingSeedWeight': this.dataForm.filingSeedWeight,
                'imageList': this.dataForm.timags
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
    },
    components:{
      uploadImage
    }
  }
</script>
