<script>
// @ is an alias to /src
import NavHead from '@/components/NavHead.vue'
// import FooterBar from '@/components/FooterBar.vue'
import PersonFirstPage from '@/components/PersonFirstPage.vue'
import PersonMyInfo from '@/components/PersonMyInfo.vue'
import PersonHeadPicture from '@/components/PersonHeadPicture.vue'
import PersonSecure from '@/components/PersonSecure.vue'
import PersonBlackList from '@/components/PersonBlackList.vue'
import PersonMySmoney from '@/components/PersonMySmoney.vue'
import PersonMyRecord from '@/components/PersonMyRecord.vue'
import PersonFans from '@/components/PersonFans.vue'
export default {
  name: 'home',
  components: {
    NavHead,
    // FooterBar,
    PersonFirstPage,
    PersonMyInfo,
    PersonHeadPicture,
    PersonSecure,
    PersonBlackList,
    PersonMySmoney,
    PersonMyRecord,
    PersonFans
  },
  data: function () {
    return {
      pageName: '个人中心',
      visible: false,
      user: null,
      isCollapse: true,
      pages: [
        // { title: '个人主页', components: 'PersonMyInfo', iconClass: 'el-icon-s-home' },
        { title: '个人信息', components: 'PersonMyInfo', iconClass: 'el-icon-s-home' },
        { title: '我的关注', components: 'PersonBlackList', iconClass: 'el-icon-s-custom' },
        { title: '我的粉丝', components: 'PersonFans', iconClass: 'el-icon-user-solid' },
        // { title: '收藏', components: 'PersonBlackList', iconClass: 'el-icon-star-off' },
        { title: '操作记录', components: 'PersonMyRecord', iconClass: 'el-icon-view' },
        { title: '修改密码', components: 'PersonSecure', iconClass: 'el-icon-setting' }

      ],
      currentTabComponent: 'PersonMyInfo',
      subTitle: ''
    }
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    changePage (index) {
      this.currentTabComponent = this.pages[index].components
    }

  }
}
</script>

<template>
  <el-container class="home">
    <!-- header -->
    <el-header style="height:auto">
      <nav-head :user="user"></nav-head>
    </el-header>
    <!-- main -->
    <el-main>
      <el-row class="common-content-row">
        <el-col>
          <div class="grid-content">
            <p></p>
          </div>
        </el-col>
        <el-col>
          <div class="grid-content person-center common-content">
            <el-container class="person-center-container">
              <div class="menu">
                <el-menu default-active="1-11-1"
                         class="el-menu-vertical-demo"
                         @open="handleOpen"
                         @close="handleClose">
                  <div class="main-title">{{pageName}}</div>
                  <el-menu-item index="0"
                                @click="changePage(0)">
                    <div class="left-item">
                      <i :class="pages[0].iconClass"></i>
                      <span slot="title">{{pages[0].title}}</span>
                    </div>
                  </el-menu-item>
                  <el-menu-item index="1"
                                @click="changePage(1)">
                    <div class="left-item">
                      <i :class="pages[1].iconClass"></i>
                      <span slot="title">{{pages[1].title}}</span>
                    </div>
                  </el-menu-item>
                  <el-menu-item index="2"
                                @click="changePage(2)">
                    <div class="left-item">
                      <i :class="pages[2].iconClass"></i>
                      <span slot="title">{{pages[2].title}}</span>
                    </div>
                  </el-menu-item>
<!--                  <el-menu-item index="3"-->
<!--                                @click="changePage(3)">-->
<!--                    <div class="left-item">-->
<!--                      <i :class="pages[3].iconClass"></i>-->
<!--                      <span slot="title">{{pages[3].title}}</span>-->
<!--                    </div>-->
<!--                  </el-menu-item>-->
                  <el-menu-item index="4"
                                @click="changePage(4)">
                    <div class="left-item">
                      <i :class="pages[4].iconClass"></i>
                      <span slot="title">{{pages[4].title}}</span>
                    </div>
                  </el-menu-item>

                </el-menu>
              </div>
              <el-main>
                <div class="sub-title">{{subTitle}}</div>
                <div>
                  <keep-alive>
                    <component v-bind:is="currentTabComponent"></component>
                  </keep-alive>
                </div>
              </el-main>
            </el-container>
          </div>
        </el-col>
        <el-col>
          <div class="grid-content">
            <p></p>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>

</template>

<style scoped>
@import "../assets/css/common.css";
.el-main {
  padding: 20px;
}
.el-header {
  padding: 0;
  height: auto;
}
.menu {
  width: 200px;
  display: flex;
  flex-direction: column;
  align-items: baseline;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  height: 100%;
}
.left-item {
  width: 100%;
  display: flex;
  justify-content: end;
  align-items: center;
}
.person-center-container {
  box-shadow: 0 0 5px 1px rgba(158, 179, 193, 0.5);
  height: 100%;
}
.person-center {
  height: auto;
  min-height: 750px;
}
.main-title {
  font-size: 20px;
  padding-left: 20px;
  /* padding-top: 20px; */
  height: 56px;
  width: 200px;
  text-align: left;
  border-bottom: 1px #e6e6e6 solid;
  line-height: 56px;
}
.sub-title {
  font-size: 14px;
  width: 100%;
  height: 36px;
  color: #409eff;
  border-bottom: 1px #e6e6e6 solid;
  line-height: 16px;
}
</style>
