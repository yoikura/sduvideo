<script>
import EventCard from '@/components/EventCard.vue'
import {getFollowedVideo, getUserInfo} from '../api/api'
export default {
  name: 'EventComponent',
  components: {
    EventCard
  },
  data: function () {
    return {
      user: {

      },
      events:[],
    }
  },
  mounted () {
    window.addEventListener('scroll', this.fixUserInfo)
  },
  destroyed () {
    window.removeEventListener('scroll', this.fixUserInfo)
  },
  async created () {
    this.user = this.$store.state._user.Info
    let res = await getFollowedVideo({uid:this.$store.state._user.Info.uid})
    let obj1 = res.data.data
    // this.events = res.data.data
    for (const item of obj1) {
      let res1 = await getUserInfo({uid:item.uid})
      item.authorInfo = res1.data.data
    }
    this.events = obj1
  },
  methods: {
    fixUserInfo () {
      const that = this
      let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      let userInfo = document.getElementById('userInfo')
      if (userInfo) {
        that.scrollTop = scrollTop
        if (that.scrollTop > 70) {
          userInfo.style.top = '0px'
          userInfo.style.position = 'fixed'
        } else {
          userInfo.style.position = 'relative'
        }
      }
    }
  }
}
</script>
<template>
  <div class="event-component">
    <div class="left">
      <event-card v-for="(event,idx) in events"
                  :key="idx"
                  :event="event"
                  :user="event.authorInfo">

      </event-card>
    </div>
    <div class="right">

      <div class="normal-card"
           id="userInfo">
        <div class="banner">
        </div>
        <div>
          <div>
            <el-avatar :src="user.avatar"
                       class="head-picture"
                       :size="48"></el-avatar>
          </div>
          <div class="user-info-right">
            <div class="user-info-nick-name">{{user.nickName}}<el-tag type="danger"
                      style="margin-left:5px;"
                      :size="'mini'">LV4</el-tag>
            </div>
            <div class="user-info-number">
              <span>关注：{{user.following}}</span>
              <span style="margin-left:20px">粉丝：{{user.followed}}</span>
<!--              <span style="margin-left:20px">作品：{{user.works}}</span>-->
            </div>
            <div class="user-info-manifesto">
              {{user.signature}}
            </div>

          </div>
        </div>
      </div>

    </div>
  </div>
</template>
<style scoped>
.event-component {
  display: flex;
}
.left {
  width: 800px;
}
.right {
  width: 440px;
  margin-left: 40px;
}
.normal-card {
  width: 100%;
  border-radius: 5px;
  background: #fff;
  margin-bottom: 20px;
  position: relative;
  width: 440px;
  padding-bottom: 20px;
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
.user-info-right {
  margin-left: 90px;
}
.user-info-nick-name {
  text-align: left;
  font-size: 16px;
  color: #222;
  padding: 5px;
}
.user-info-number {
  text-align: left;
  font-size: 12px;
  color: #222;
  padding: 5px;
}
.user-info-manifesto {
  text-align: left;
  font-size: 12px;
  color: #99a2aa;
  padding: 5px;
}
</style>
