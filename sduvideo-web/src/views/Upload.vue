<script>
import NavHead from '@/components/NavHead.vue'
// import FooterBar from '@/components/FooterBar.vue'
import BackTop from '@/components/BackTop.vue'
import {addVideo, getVideoType} from '../api/api'

export default {
  name: 'Event',
  components: {
    NavHead,
    // FooterBar,
    BackTop

  },
  created () {
    this.user = this.$store.state._user.Info
    this.getTypeList()
  },
  data: function () {
    return {
      user: {
        // uid: 1
      },
      handbook: '',
      htmlCode: '',
      typeList:[],
      form:{
        title: '',
        type:''
      },
      rules: {
        title: [
          { required: true, message: '请输入视频标题', trigger: 'blur' },
          { min: 5, max: 20, message: '标题长度在 5 到 20 个字符', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择视频分区', trigger: 'change' }
        ],
      },
      progressFlag:true,
      loadProgress: 0,
      fileList1:[],
      fileList2:[],
      fileData:{},
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true // 预览
      }
    }
  },
  methods: {
    writeMd (value, render) {
      // this.htmlCode = mavonEditor.getMarkdownIt().render(this.handbook)
      // this.htmlCode = render
      // this.getNavigation()
    },
    doUpload () {
      // this.$refs.workUpload.action=""
    },
    async getTypeList(){
      let res = await getVideoType()
      this.typeList = res.data.data
    },
    uploadFile1(file) {
      this.fileData.append('videoFile', file.file);  // append增加数据
    },
    uploadFile2(file) {
      this.fileData.append('imgFile', file.file);  // append增加数据
    },
    //移除
    handleRemove1(file, fileList) {
      this.fileList1 = fileList;
    },
    handleRemove2(file, fileList) {
      this.fileList2 = fileList;
    },
    // 选取文件超过数量提示
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 2 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },

    //监控上传文件列表
    handleChange1(file, fileList) {
      let existFile = fileList.slice(0, fileList.length - 1).find(f => f.name === file.name);
      if (existFile) {
        this.$message.error('当前文件已经存在!');
        fileList.pop();
      }
      this.fileList1 = fileList;
    },
    handleChange2(file, fileList) {
      let existFile = fileList.slice(0, fileList.length - 1).find(f => f.name === file.name);
      if (existFile) {
        this.$message.error('当前文件已经存在!');
        fileList.pop();
      }
      this.fileList2 = fileList;
    },
    async submitUpload() {
      if(this.form.type === '' || this.form.title === ''){
        this.$message({
          message: '请补全输入信息',
          type: 'warning'
        })
        return
      }
      const isLt100M1 = this.fileList1.every(file => file.size / 1024 / 1024 < 100);
      const isLt20M2 = this.fileList2.every(file => file.size / 1024 / 1024 < 20);
      if (!isLt100M1||!isLt20M2) {
        this.$message.error('请检查，上传文件大小不能超过指定大小!');
      } else if(this.fileList1.length === 0 || this.fileList2.length === 0){
        this.$message({
          message: '请补全上传文件',
          type: 'warning'
        })
      } else {
        this.fileData = new FormData();  // new formData对象
        this.$refs.upload1.submit();  // 提交调用uploadFile函数
        this.$refs.upload2.submit();  // 提交调用uploadFile函数
        this.fileData.append('vname', this.form.title);
        this.fileData.append('tid', this.form.type);
        this.fileData.append('description', this.handbook);
        this.fileData.append('uid', this.user.uid);
        const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        let res = await addVideo(this.fileData)
        loading.close()
        if (res.data.return_code === 'success') {
          this.$message({
            message: "上传成功",
            type: 'success'
          });
          this.fileList = [];
        } else {
          this.$message({
            message: res.data.tip,
            type: 'error'
          })
        }
      }
    },
  }
}
</script>
<template>
  <el-container class="upload-container">
    <!-- header -->
    <el-header style="height:auto">
      <nav-head :user="user"></nav-head>

    </el-header>
    <!-- main -->
    <el-main>
      <el-row class="common-content-row">
        <el-col>
          <div>
            <p></p>
          </div>
        </el-col>
        <el-col>
          <div class="common-content">
            <div class="upload-info">
              <div class="info">
              </div>
              <div class="upload-content">

                <el-upload class="upload upload-box"
                           drag
                           action="http://81.68.137.192:8888/video/addVideo"
                           accept=".mp4"
                           ref="upload1"
                           :multiple="false"
                           :auto-upload="false"
                           :file-list="fileList1"
                           :http-request="uploadFile1"
                           :on-change="handleChange1"
                           :on-remove="handleRemove1">
                  <i class="el-icon-upload"></i>
                  <div class="el-upload__text">将视频文件拖到此处，或<em>点击上传</em>（不超过100M）</div>

                </el-upload>
                <el-upload class="upload upload-box"
                           drag
                           action="http://81.68.137.192:8888/video/addVideo"
                           accept=".jpg,.gif,.png,.jpeg"
                           ref="upload2"
                           :multiple="false"
                           :auto-upload="false"
                           :file-list="fileList2"
                           :http-request="uploadFile2"
                           :on-change="handleChange2"
                           :on-remove="handleRemove2">
                  <i class="el-icon-upload"></i>
                  <div class="el-upload__text">将封面文件拖到此处，或<em>点击上传</em>（不超过20M）</div>
                </el-upload>
                <div>
                  <el-form ref="form" :model="form" label-width="80px" :rules="rules">
                    <el-form-item label="视频标题" prop="title" class="form-item">
                      <el-input v-model="form.title"></el-input>
                    </el-form-item>
                    <el-form-item label="视频分区" prop="type" class="form-item">
                      <el-select v-model="form.type" placeholder="请选择视频分区">
                        <el-option v-for="(item,index) in typeList" :label= item.typeName :value=item.tid></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item class="form-item">
<!--                      <div class="el-upload__tip"-->
<!--                           slot="tip">-->
<!--                        不超过50M-->
<!--                        <span class="upload-button">-->
<!--                          <el-button type="primary"-->
<!--                                     @click.prevent="doUpload">上传作品</el-button>-->
<!--                        </span>-->
<!--                      </div>-->
                      <el-button type="primary" @click="submitUpload">立即上传</el-button>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </div>

            <div class="description">输入视频简介，让更多人看到吧！</div>
            <!-- md -->
            <div class="md-div">
              <mavon-editor :toolbars="toolbars"
                            style="min-height: 380px;"
                            @change="writeMd"
                            v-model="handbook" />
            </div>

          </div>
        </el-col>
        <el-col>
          <div class="common-right-content">
            <back-top></back-top>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<style scoped>
@import "../assets/css/common.css";
.upload-container {
  height: 100%;
}
.upload-content {
  display: flex;
  justify-content: flex-start;
}

.upload-info {
  display: flex;
  align-items: center;
  height: 300px;
}

.md-div {
  margin-top: 20px;
}
.upload-button {
  margin: 30px;
}
.upload >>> .el-upload-dragger {
  width: 280px;
  height: 220px;
}

.upload-box{
  margin-right: 30px;
}

.description{
  font-size: 14px;
  margin-left: 10px;
}

.form-item{
  margin-bottom: 30px;
}
</style>
