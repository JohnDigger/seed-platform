<template>
  <div>
    <el-upload
      class="upload-img"
      ref="upload"
      action=""
      :on-preview="handlePreview"
      :multiple="true"
      :on-remove="handleRemove"
      :file-list="fileList"
      list-type="picture"
      :limit="3"
      accept="image/jpeg,image/jpg,image/png,image/JPG"
      :auto-upload="false"
      :before-upload="beforeUpload"
      :on-change="handleChange"
      :on-exceed="handleExceed"
      :on-success="handleSuccess"
    >
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png/jpeg文件</div>
    </el-upload>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  data () {
    return {
      fileList: []
    }
  },
  methods: {
    // submitUpload () {
    //   this.$refs.upload.submit()
    // },
    // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用,function(file, fileList)
    handleChange (file, fileList) {
      this.fileList = fileList
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handleSuccess (response, file, fileList) {
      this.fileList = []
    },
    beforeUpload (file) {
      const isJPG = file.type === 'image/jpg'
      const isPng = file.type === 'image/png'
      const isJpeg = file.type === 'image/jpeg'
      const isJPGup = file.type === 'image/JPG'
      if (!isJPG && !isPng && !isJpeg && !isJPGup) {
        this.$message.error('上传图片只能是 png,jpg,jpeg 格式!')
        return false
      }
      this.formData.fileName = file.name
      let size10M = file.size / 1024 / 1024 < 30
      if (!size10M) {
        this.$message.warning('上传文件大小不能超过 30MB!')
        return false
      }
    },
    handlePreview (file) {
      console.log(file)
    },
    handleExceed () {
      this.$message.warning('您只能上传三个文件')
    },
    submitUpload () {
      // 判断是否有文件再上传
      if (this.fileList.length === 0) {
        return this.$message.warning('请选取文件后再上传')
      }
      // 下面的代码将创建一个空的FormData对象:
      const formData = new FormData()
      // 你可以使用FormData.append来添加键/值对到表单里面；
      this.fileList.forEach((file) => {
        formData.append('file', file.raw)
      })
      // eslint-disable-next-line no-undef
      axios.post(window.SITE_CONFIG.baseUrl + '/seed-platform/upload/pictureUpload', formData, {
        'Content-type': 'multipart/form-data'
      }).then(res => {
        this.$emit('upload-image', res.data.fileList)
        document.querySelectorAll('.is-ready').forEach(ele => {
          ele.classList.replace('is-ready', 'is-success')
        })
        this.$message.success('上传成功')
        this.$refs.upload.clearFiles()
      }, err => {
        this.$message.success(err.msg)
      })
    }
  },
  props: {
    imageUrls: Array
  }
}
</script>

