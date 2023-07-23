<template>
  <div >
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="vname" ></el-input>
      <!-- <el-input placeholder="请输入名称" v-model="vname"></el-input> -->
      <el-button class="ml-5" type="primary" @click="searchByName()">搜索</el-button>
      <el-button type="warning" >重置</el-button>
    </div>


    <div style="margin: 10px 0">
     

    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
             >
    
      <el-table-column prop="vid" label="ID" width="80"></el-table-column>
      <el-table-column prop="vname" label="视频名称"></el-table-column>
      <el-table-column prop="cover" label="封面" >
        <template slot-scope="scope">
          <img :src="scope.row.cover" width="150">
        </template>
      </el-table-column>
      <el-table-column prop="address" label="地址" align="center">
        <template slot-scope="scope">
          
          <el-link type="info" >
            <a :href="scope.row.address">点我播放</a>
          </el-link>

         
        </template>

      </el-table-column>
      <el-table-column prop="playNum" label="点赞数"></el-table-column>
      <el-table-column prop="likeNum" label="喜欢的次数"></el-table-column>
      
      <el-table-column label="修改">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.state" active-color="#13ce66" inactive-color="#ccc"
                     @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="checkVideo(scope.row.vid)" 
                    v-if="scope.row.examine===0">审核</el-button>
          <el-button type="info" @click="checkVideo(scope.row.vid)" 
          :disabled="true" v-else>已审核</el-button>

          <el-button type="primary" @click="handleEdit(scope.row)">修改类型</el-button>
        </template>
      </el-table-column>
    </el-table>



    <el-dialog title="视频类型" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="视频名称">
          <el-input v-model="form.vname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select clearable v-model="form.tid" placeholder="请选择角色" style="width: 100%">
            <el-option v-for="item in videoTypes" :key="item.typeName" :label="item.typeName" :value="item.tid"></el-option>
          </el-select>
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>

    <div style="padding: 10px 0">
      <el-pagination style="text-align: center;"
           background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[3,5 , 10, 20]"
          :page-size="pageSize"
          :page-count=11
          layout='total,sizes,prev, pager, next, jumper'
          :total="total">
      </el-pagination>
    </div>

   
  </div>
</template>

<script>
import Video from './front/Video.vue'

export default {
  components: { Video },
  name: "Course",
  data() {
    return {
      form: {},
      tableData: [
  
      ],
      videoTypes:[],
      form:{},
      vname:"",
      pageNum: 1,
      pageSize: 3,
      total: '',
      dialogFormVisible:false
   
    }
  },
  created() {
    this.load() 
  },
  methods: {
  
    load() {
     
      this.$sduResquest.post("/adminInfo/selectAllVideos", {
        
          page: this.pageNum,
          size: this.pageSize,
          vname:this.vname
       
      }).then(res => {

        this.tableData = res.data.pageList
        this.total = res.data.count
            console.log(this.tableData,this.total)

      })

      this.$sduResquest.post("/video/getVideoType").then(res => {
        this.videoTypes = res.data
      })

    },

    searchByName(){
        this.load();
    },

    updateType(){
      this.$sduResquest.post("/adminInfo/classifyVideo", {
        vid:this.form.vid,
        tid:this.form.tid,
     

      }).then(res => {
        if (res.return_code==='success') {
          this.$message.success("操作成功")
          this.dialogFormVisible = false
          this.load()

        }
        else{
          this.$message.success("修改失败")
        }
      })

    },


    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },

    checkVideo(vid,aid){
      console.log(vid)
      this.$sduResquest.post("/adminInfo/examineVideo", {
        aid:1,
        vid:vid
      }).then(res => {
      
        if (res.return_code==='success') {
          this.$message.success("审核成功")
          this.load()
        }
      })
    },

    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum,"fwef")
      this.pageNum = pageNum
      this.load()
    },
    download(url) {
      window.open(url)
    },
  }
}
</script>

<style scoped>
  		#el-table {
            height: calc(100vh - 280px);
            overflow-y: auto;
            scrollbar-color: transparent transparent;
      }

</style>
