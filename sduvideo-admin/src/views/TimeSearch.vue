<template>
  <div>
   
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
   
    
      <el-table-column prop="tid" label="ID" width="50px"></el-table-column>
      <el-table-column prop="typeName" label="类型名" width="100px"></el-table-column>
      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
         
          <el-button type="primary"  @click="handleEdit(scope.row)">修改类型</el-button>

          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.tid)"
          >

          <el-button type="danger" slot="reference" >删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    
    </el-table>
  

    <el-dialog title="类型信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="类型名">
          <el-input v-model="form.typeName" autocomplete="off"></el-input>
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>



    <el-dialog title="类型信息" :visible.sync="add" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="类型名">
          <el-input v-model="addform.typeName" autocomplete="off"></el-input>
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="add = false">取 消</el-button>
        <el-button type="primary" @click="addType">确 定</el-button>
      </div>
    </el-dialog>

   
  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name: "TimeSearch",
  data() {
    return {
      serverIp: serverIp,
      add:false,
      tableData: [
        {
          "tid":1,
          "typeName":"yinyue"
        },
        {
          "tid":2,
          "typeName":"123"
        }

      ],
      form:{},
      addform:{},
      dialogFormVisible: false,
      multipleSelection: [],
    
   
    }
  },
  created() {
    this.load()
  },
  methods: {
  
    load() {
      this.$sduResquest.post("/video/getVideoType", ).then(res => {

        this.tableData = res.data
      })
    },
   

    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },


    addType(){
      this.$sduResquest.post("/adminInfo/insertVideoType" ,{
       
       typeName:this.addform.typeName
     }).then(res => {
       if (res.return_code==='success') {
         this.$message.success("添加成功")
         this.dialogFormVisible = false
         this.add = false
         this.load()
       
       } else {
         this.$message.error("添加失败")
       }
     })
    },

    save(){

      // if(form.videoType in)
      this.$sduResquest.post("/adminInfo/updateVideoType" ,{
       tid:this.form.tid,
        typeName:this.form.typeName
      }).then(res => {
        if (res.return_code==='success') {
          this.$message.success("修改成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("修改失败")
        }
      })
    },

    del(tid) {
      this.$sduResquest.post("/adminInfo/deleteVideoType" ,{
        tid
      }).then(res => {
        if (res.return_code==='success') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },

    handleAdd() {
      this.add = true
      this.addform = {}
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
   
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
 

 
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>
