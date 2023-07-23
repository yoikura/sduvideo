<script>
import {selectAttend} from '../api/api'
import user from '../store/user'

export default {
  name: 'UserInfoFloat',
  props: {
    user: {
      type: Object
    },
    size: {
      type: Number | String,
      default: 48
    }
  },
  data: function () {
    return {
      userInfo: {

      },
      isAttend:false
    }
  },
  async created () {
    this.userInfo = this.$store.state._user
  }
}
</script>
<template>
  <!-- 浮动框 -->
  <div class="info-bottom"
       :style="{left:parseInt(size)/2+'px',top:parseInt(size)+'px'}">
    <div class="banner">
    </div>
    <div>
      <div>
        <el-avatar :src="user.avatar"
                   class="head-picture"
                   :size="48"></el-avatar>
      </div>
      <div class="right">
        <div class="nick-name">{{user.nickName}}<el-tag type="danger"
                  style="margin-left:5px;"
                  :size="'mini'">LV4</el-tag>
        </div>
        <div class="number">
          <span>关注：{{user.following}}</span>
          <span style="margin-left:20px">粉丝：{{user.followed}}</span>
        </div>
        <div class="manifesto">
          {{user.signature}}
        </div>
<!--        <div class="button-div">-->
<!--          <el-button>+ 关注</el-button>-->
<!--          <el-button>发消息</el-button>-->
<!--        </div>-->
      </div>
    </div>
  </div>
</template>
<style scoped>
.info-bottom {
  height: 300px;
  width: 400px;
  border-radius: 5px;
  cursor: pointer;
  background: #fff;
  position: absolute;
  box-shadow: 0 0 0 1px #cee;
  border-radius: 0 0 4px 4px;
  z-index: 99;
}
.img-div:hover > .info-bottom {
  display: block;
}
.banner {
  background: url("~@/assets/img/banner_bg.png") no-repeat;
  width: 100%;
  height: 120px;
  background-position: center;
}
.head-picture {
  border: 2px solid #fff;
  width: 52px;
  height: 52px;
  position: absolute;
  top: 94px;
  left: 20px;
}
.right {
  margin-left: 90px;
}
.nick-name {
  text-align: left;
  font-size: 16px;
  color: #222;
  padding: 5px;
}
.number {
  text-align: left;
  font-size: 12px;
  color: #222;
  padding: 5px;
}
.manifesto {
  text-align: left;
  font-size: 12px;
  color: #99a2aa;
  padding: 5px;
}
.button-div {
  margin-top: 20px;
  display: flex;
}
</style>
