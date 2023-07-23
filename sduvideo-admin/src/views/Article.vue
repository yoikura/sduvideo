<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <!-- <el-button type="primary" @click="handleAdd" v-if="user.role === 'ROLE_ADMIN'">新增 <i class="el-icon-circle-plus-outline"></i></el-button> -->
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>

    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="uid" label="ID" width="50px"></el-table-column>
      <el-table-column prop="userName" label="用户名" width="100px"></el-table-column>
      <el-table-column prop="nickName" label="别名" width="80"></el-table-column>

      <el-table-column prop="avatar" label="头像" width="100px">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" width="100px">
        </template>
      </el-table-column>
      <el-table-column prop="following" label="关注数" width="80">
        <template slot-scope="scope">
          <el-popover

            placement="right"
            width="400"
            trigger="click"

            >
            <div v-for='(fan ,index) in following' :key=index>
              用户ID 手机号 邮箱
              <template v-if="fan">
                <span >
                {{ fan.uid }} {{ fan.tel }}  {{ fan.email }}
              </span>
              </template>

            </div>
            <i @click="selectFollow(scope.row.uid)" type="primary"  slot="reference" style="text-align: center;">{{scope.row.following}}</i>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="followed" label="粉丝数" width="80">
        <template slot-scope="scope">
          <el-popover

            placement="right"
            width="400"
            trigger="click"
            >
            <div v-for='(fan ,index) in fans' :key=index>
              用户ID 手机号 邮箱
              <template v-if="fan">
                <span >
                {{ fan.uid }} {{ fan.tel }}  {{ fan.email }}
              </span>
              </template>

            </div>
            <i @click="selectFans(scope.row.uid)" type="primary"  slot="reference" style="text-align: center;">{{scope.row.followed}}</i>
          </el-popover>
        </template>

      </el-table-column>

      <el-table-column prop="email" label="邮箱" width="80" show-overflow-tooltip></el-table-column>

      <el-table-column prop="avatar" label="头像地址">
        <!-- <template slot-scope="scope">
          <el-button @click="view(scope.row.content)" type="primary">查看内容</el-button>
        </template> -->
      </el-table-column>

      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope" >
          <el-popover
            placement="right"
            width="400"
            trigger="click"
          >
              <ul style="margin-left: 20px">
                <li >
                  密码 {{row.password}}
                </li>
                <li >

                  城市 {{ row.city}}

                </li>
                <li >

                  省份 {{row.province}}



                </li>
                <li >

                  生日 {{row.birthday}}


                </li>
              </ul>
            <el-button  slot="reference"  @click="getDetails(scope.row)" v-if="user.role === 'ROLE_ADMIN'"style="margin-right: 6px">详情 <i class="el-icon-scan"></i></el-button>

    </el-popover>


          <el-button type="success" @click="handleEdit(scope.row)" v-if="user.role === 'ROLE_ADMIN'">编辑 <i class="el-icon-edit"></i></el-button>



          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.uid)"
          >

          <el-button type="danger" slot="reference" v-if="user.role === 'ROLE_ADMIN'">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="修改用户" :visible.sync="dialogFormVisible" width="60%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="用户ID">
          <el-input v-model="form.uid" autocomplete="off" width="20px" disabled></el-input>
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="form.tel" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>



      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="changeEnable">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="文章信息" :visible.sync="viewDialogVis" width="60%" >
      <el-card>
        <div>

        </div>
      </el-card>
    </el-dialog>

  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "Article",
  data() {
    return {
      form: {},
      tableData: [],
      name: '',
      multipleSelection: [],
      pageNum: 1,
      pageSize: 5,
      total: 0,
      dialogFormVisible: false,
      teachers: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      content: '',
      viewDialogVis: false,
      row:{},
      following:[],
      fans:[]
    }
  },
  created() {
    this.load()
  },
  methods: {
    view(content) {
      this.content = content
      this.viewDialogVis = true
    },
    // 绑定@imgAdd event
    imgAdd(pos, $file) {

    },
    load() {
      this.$sduResquest.post("/adminInfo/selectAllUsers",
         {
          page: this.pageNum,
          size: this.pageSize,
          uname: this.name,
        }
      ).then(res => {

        this.tableData = res.data.pageList
        this.total = res.data.count
            console.log(this.tableData,this.total)

      })

    },

    getDetails(row){

        this.row =row
        console.log(this.row)//此时就能拿到整行的信息
      },


      selectFollow(uid){
        this.$sduResquest.post("/userinfo/getAttention",
         {
          uid
        }
      ).then(res => {
        this.following = res.data
        console.log(res.data,"关注的谁")

      })


    },

    selectFans(uid){
        this.$sduResquest.post("/userinfo/getAttentionTo",
         {
          uid
        }
      ).then(res => {
        console.log(res.data,"我的粉丝")
        this.fans = res.data

      })


    },





    changeEnable() {
      console.log(this.form,"修改用户")
      this.$sduResquest.post("/adminInfo/updateUserById", {
        uid:this.form.uid,
        tel:this.form.tel,
        email:this.form.email

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
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    del(uid) {
      this.$sduResquest.post("/adminInfo/deleteUserById" ,{
        uid
      }).then(res => {
        if (res.return_code==='success') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val,"修改数组")
      this.multipleSelection = val
    },
    delBatch() {

      let ids = this.multipleSelection.map(v => v.uid)
      console.log(ids,"数组")
      this.$sduResquest.post("/adminInfo/deleteUserByList", {
        params:ids
      }).then(res => {
        if (res.return_code === 'success') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    save() {
      this.request.post("/article", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    reset() {
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
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

</style>
