<script>
export default {
  name: 'NavHead',
  // props: ['user'],
  data () {
    return {
      title: 'SilenceHTML',
      searchContent: '',
      userInfo: {
        // uid: 1
      },
    }
  },
  created () {
    this.userInfo = this.$store.state._user.Info
  },
  watch: { $route (to, from) { this.$router.go(0) } },
  methods: {
    goLogin: function () {
      this.$router.push('/login')
    },
    goLogout: function () {
      this.$store.commit("_user/removeUser")
      this.$store.commit("_user/changeLogin",false)
      this.$router.push('/login')
    },
    goHome: function () {
      this.$router.push('/')
    },
    goClassify: function () {
      this.$router.push('/classify')
    },
    goPersonCenter: function () {
      if(this.userInfo === null){
        this.goLogin()
        return
      }
      this.$router.push('/person_center')
    },
    goHistory: function () {
      if(this.userInfo === null){
        this.goLogin()
        return
      }
      this.$router.push('/history')
    },
    goEvent: function () {
      if(this.userInfo === null){
        this.goLogin()
        return
      }
      this.$router.push('/event')
    },
    goMessage: function () {
      if(this.userInfo === null){
        this.goLogin()
        return
      }
      this.$router.push('/message')
    },
    goCollect: function () {
      if(this.userInfo === null){
        this.goLogin()
        return
      }
      // 必须用name，path会使params失效
      this.$router.push({ name: 'PersonSpace', params: { module: 'PSCollect' }, query:{uid: this.userInfo.uid.toString()}})
    },
    goUpload: function () {
      if(this.userInfo === null){
        this.goLogin()
        return
      }
      this.$router.push('/upload')
    },
    goWork: function () {
      if(this.userInfo === null){
        this.goLogin()
        return
      }
      this.$router.push({ name: 'PersonSpace', params: { module: 'PSWork' }, query:{uid: this.userInfo.uid.toString()}})
    },
    goSearch :function () {
      this.$router.push({path:'/classify',query:{tid: '0',searchContent:this.searchContent,date:new Date().getTime()}})
    }
  }
}
</script>

<template>

  <el-row class="head-container">
    <el-col class="nav-head">
      <el-row>
        <el-col :span="2">
          <div class="grid-content left">
            <div class="nav-item">
              <span class="nav-span"
                    @click="goHome">首页</span>
            </div>
<!--            <div class="nav-item">-->
<!--              <span class="nav-span"-->
<!--                    @click="goClassify">分类</span>-->
<!--            </div>-->
          </div>
        </el-col>
        <el-col :span="22">
          <div class="grid-content right">
            <div class="nav-item" style="width: 500px">
<!--              <el-input placeholder="请输入搜索内容"-->
<!--                        suffix-icon="el-icon-search"-->
<!--                        v-model="searchContent"-->
<!--                        >-->
<!--              </el-input>-->
              <el-input placeholder="请输入您感兴趣的内容" v-model="searchContent" class="input-with-select">
                <el-button slot="append" icon="el-icon-search" @click="goSearch"></el-button>
              </el-input>
            </div>
            <div class="nav-item">
              <el-dropdown>
                <span class="el-dropdown-link"
                      >
                  <img :src="userInfo.avatar" v-if="userInfo" alt="" height="40px" width="40px" @click="goPersonCenter" class="avatar"/>
                  <el-avatar icon="el-icon-user-solid" v-else></el-avatar>
                </span>
                <el-dropdown-menu slot="dropdown"
                                  v-if="userInfo">
<!--                  <el-dropdown-item>{{userInfo.userName}}</el-dropdown-item>-->
<!--                  <el-dropdown-item>{{userInfo.tel}}</el-dropdown-item>-->
                  <el-dropdown-item @click.native="goPersonCenter">个人中心</el-dropdown-item>
<!--                  <el-dropdown-item>等级:{{user.level}}</el-dropdown-item>-->
<!--                  <el-dropdown-item>漫币:{{user.cmoney}}</el-dropdown-item>-->
                  <el-dropdown-item @click.native="goLogout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
                <el-dropdown-menu slot="dropdown"
                                  v-else>
                  <el-dropdown-item @click.native="goLogin">
                    登录/注册
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>

            </div>
            <div class="nav-item">
              <el-badge
                        class="item">
                <span class="nav-span"
                      @click="goMessage">消息</span>
              </el-badge>
            </div>
            <div class="nav-item">
              <el-badge
                        class="item">
                <span class="nav-span"
                      @click="goEvent">动态</span>
              </el-badge>
            </div>
            <div class="nav-item">
              <el-badge
                        class="item">
                <span class="nav-span"
                      @click="goWork">个人投稿</span>
              </el-badge>
            </div>
            <div class="nav-item">
              <span class="nav-span"
                    @click="goCollect">收藏</span>
            </div>
            <div class="nav-item"
                 @click="goHistory">
              <span class="nav-span">历史</span>
            </div>
            <div class="nav-item"
                 @click="goUpload">
              <el-button type="primary"
                         @click="goUpload">投稿<i class="el-icon-upload el-icon--right"></i></el-button>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<style scoped>
.head-container {
  display: flex;
  justify-content: center;
  box-shadow: 0 0 5px 1px rgba(158, 179, 193, 0.5);
  background: #fff;
}
.avatar:hover{
  cursor: pointer;
}
.el-row {
  height: 50px;
}
.el-col {
  border-radius: 4px;
  height: 100%;
}
.nav-head {
  width: 1280px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  height: 100%;
  display: flex;
  align-items: center;
}
.left {
  float: left;
}
.right {
  float: right;
}
.nav-img {
  width: 36px;
  height: 36px;
  border-radius: 4px;
  cursor: pointer;
}
.web-span {
  font-size: 16px;
  padding: 8px;
  color: rgba(0, 0, 0, 0.87);
  cursor: pointer;
}
.nav-span {
  font-size: 14px;
  padding: 8px;
  color: rgba(0, 0, 0, 0.87);
}
.nav-item {
  margin-left: 14px;
  margin-right: 14px;
}
.nav-span:hover {
  color: #32aaff;
  cursor: pointer;
}
.el-input {
  border-radius: 15px;
}
</style>
