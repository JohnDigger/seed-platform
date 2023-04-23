<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="植株高度" prop="plantHeight">
        <el-input v-model="dataForm.plantHeight" placeholder="植株高度"></el-input>
      </el-form-item>
      <el-form-item label="叶片形态" prop="leafShape">
        <el-input v-model="dataForm.leafShape" placeholder="叶片形态"></el-input>
      </el-form-item>
      <el-form-item label="果实大小" prop="fruitSize">
        <el-input v-model="dataForm.fruitSize" placeholder="果实大小"></el-input>
      </el-form-item>
      <el-form-item label="果实颜色" prop="fruitColor">
        <el-input v-model="dataForm.fruitColor" placeholder="果实颜色"></el-input>
      </el-form-item>
      <el-form-item label="果实产量" prop="fruitProduction">
        <el-input v-model="dataForm.fruitProduction" placeholder="果实产量"></el-input>
      </el-form-item>
      <el-form-item label="耐逆性" prop="stressTolerance">
        <el-input v-model="dataForm.stressTolerance" placeholder="耐逆性"></el-input>
      </el-form-item>
      <el-form-item label="生长周期" prop="growCycle">
        <el-input v-model="dataForm.growCycle" placeholder="生长周期"></el-input>
      </el-form-item>
      <el-form-item label="创建日期" prop="seedGrowthRate">
        <!--        <el-input v-model="dataForm.createTime" placeholder="创建日期"></el-input>-->
        <el-date-picker
          v-model="dataForm.createTime"
          type="datetime"
          placeholder="选择日期时间">
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
        id: 0,
        plantHeight: '',
        leafShape: '',
        fruitSize: '',
        fruitColor: '',
        fruitProduction: '',
        stressTolerance: '',
        growCycle: ''
      },
      dataRule: {
        plantHeight: [
          {required: true, message: '植株高度不能为空', trigger: 'blur'}
        ],
        leafShape: [
          {required: true, message: '叶片形态不能为空', trigger: 'blur'}
        ],
        fruitSize: [
          {required: true, message: '果实大小不能为空', trigger: 'blur'}
        ],
        fruitColor: [
          {required: true, message: '果实颜色不能为空', trigger: 'blur'}
        ],
        fruitProduction: [
          {required: true, message: '果实产量不能为空', trigger: 'blur'}
        ],
        stressTolerance: [
          {required: true, message: '耐逆性不能为空', trigger: 'blur'}
        ],
        growCycle: [
          {required: true, message: '生长周期不能为空', trigger: 'blur'}
        ],
        createTime: [
          {required: true, message: '创建日期不能为空', trigger: 'blur'}
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
            url: this.$http.adornUrl(`/seed-platform/tplantcharacteristics/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.plantHeight = data.tPlantCharacteristics.plantHeight
              this.dataForm.leafShape = data.tPlantCharacteristics.leafShape
              this.dataForm.fruitSize = data.tPlantCharacteristics.fruitSize
              this.dataForm.fruitColor = data.tPlantCharacteristics.fruitColor
              this.dataForm.fruitProduction = data.tPlantCharacteristics.fruitProduction
              this.dataForm.stressTolerance = data.tPlantCharacteristics.stressTolerance
              this.dataForm.growCycle = data.tPlantCharacteristics.growCycle
              this.dataForm.createTime = data.tPlantCharacteristics.createTime
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
            url: this.$http.adornUrl(`/seed-platform/tplantcharacteristics/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'plantHeight': this.dataForm.plantHeight,
              'leafShape': this.dataForm.leafShape,
              'fruitSize': this.dataForm.fruitSize,
              'fruitColor': this.dataForm.fruitColor,
              'fruitProduction': this.dataForm.fruitProduction,
              'stressTolerance': this.dataForm.stressTolerance,
              'growCycle': this.dataForm.growCycle,
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
