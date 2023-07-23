<script>
import {addMessage, getAllMessage, getUserInfo, selectToUser} from '../api/api'

export default {
  name: 'MyMessage',
  props: ['uid'],
  data: function () {
    return {
      activeId: 0,
      timer:null,
      userInfo:{

      },
      list1:[
        {
          fromUid:1,
          fromUname:"yoikura",
          toUid:9,
          toUname:"bhrt",
          content:"哈喽哈楼",
          createTime:"2023-07-08 06:30:54"
        },
      ],
      userList:[
        {
          uid:9,
          nickName:'zzr',
          avatar:''
        },
        {
          uid:2,
          nickName:'zzr',
          avatar:''
        }
      ],
      sel1:0,
      text1:'',
      messageList: [
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        }, {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        },
        {
          fromUser: {
            nickName: '哔哩哔哩创作中心',
            headPicture: require('../assets/img/head_picture2.jpg')
          },
          messList: [
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 },
            { content: '【拍摄周刊第二期】海贼王上映啦！来试试专属特效吧！投稿就有海贼头像框哦w(ﾟДﾟ)w传送门：网页链接', type: 1 },
            { content: '好的', type: 0 }
          ]
        }

      ]
    }
  },
  methods: {
    handleDate (date) {
      return this.$moment(date, 'YYYYMMDDHHmmss').format('YYYY-MM-DD HH:mm')
    },
    async fnAdd(){
      if(this.text1 === ''){
        return;
      }
      // 列表追加数据push()
      let obj = {
        fromUid:this.userInfo.uid,
        toUid:this.userList[this.activeId].uid,
        content:this.text1,
        createTime: this.handleDate(new Date())
      }
      let params = {
        fromUid:this.userInfo.uid,
        toUid:this.userList[this.activeId].uid,
        content:this.text1,
      }
      let res = await addMessage(params)
      if(res.data.return_code === 'success'){
        this.list1.push(obj);
        // 每次输入内容后,清空输入栏数据
        this.text1='';
      }else {
        this.$message.error(res.data.tips)
      }
    },
    async getAllMessage(){
      let params = {
        fromUid: this.userInfo.uid,
        toUid: this.userList[this.activeId].uid
      }
      let res = await getAllMessage(params)
      this.list1 = res.data.data
    },
    changeActiveId(idx){
      this.activeId = idx
      this.getAllMessage()
    },
    async getToUser(){
      let res = await selectToUser({uid:this.userInfo.uid})
      this.userList = res.data.data
    }
  },
  async created () {
    this.userInfo = this.$store.state._user.Info
    await this.getToUser()
    if (typeof (this.uid) !== 'undefined' && this.uid !== '') {
      let res = await getUserInfo({uid: this.uid})
      this.userList.push(res.data.data)
      this.activeId = this.userList.length-1
    }
    await this.getAllMessage()
    this.timer = setInterval(() => {
      setTimeout(this.getAllMessage(), 0)
    }, 1000)
  },
  beforeDestroy(){
    clearInterval(this.timer);
    this.timer = null;
  }
}
</script>
<template>
  <div class="my-message">
    <el-container class="height:100%;">
      <!-- left -->
      <el-aside width="250px"
                class="left">
        <div class="left-title">最近消息</div>
        <div class="recent-mess-div">
          <div v-for="(user,idx) in userList"
               :class="activeId===idx?'recent-mess-active':'recent-mess'"
               :key="idx"
                @click="changeActiveId(idx)">
            <div class="head-picture">
              <el-avatar :src="user.avatar"
                         :size="42"></el-avatar>
            </div>
            <div class="nick-name">
              {{user.nickName}}
            </div>
          </div>
        </div>
      </el-aside>
      <!-- main -->
      <el-main style="padding-top:0px;padding-bottom:0px;">
        <div class="nick-name-title">与{{userList[activeId].nickName}}的聊天窗口</div>

          <div class="communication-record">
            <div class="talk_con">
              <div class="talk_show" id="words">
                <div :class="[(i.fromUid===userInfo.uid)?'btalk':'atalk']" v-for="i in list1"><span>{{i.content}}</span></div>
              </div>
              <div class="talk_input">
                <el-input type="text" class="talk_word" id="talkwords" v-model="text1"></el-input>
                <!-- 绑定单击监听,把value传到vue的list1中 -->
<!--                <input type="button" value="发送" class="talk_sub" id="talksub" @click="fnAdd">-->
                <el-button type="info" class="talk_sub" plain @click="fnAdd">发送</el-button>
              </div>
            </div>
          </div>

        <div class="communication">

        </div>
      </el-main>
    </el-container>
  </div>
</template>
<style scoped>
@import "../assets/css/common.css";
.my-message {
  display: flex;
  background-color: #fff;
  border-radius: 4px;
  flex-direction: column;
  margin-top: 10px;
  height: 700px;
  overflow-y: auto;
}
.left {
  border-right: solid 1px #eee;
  height: 100%;
  overflow: hidden;
}
.left-title {
  padding: 5px;
  padding-left: 10px;
  display: flex;
  color: #666666;
  font-size: 14px;
  border-bottom: solid 1px #eee;
}
.recent-mess-div {
  height: 600px;
  overflow-y: auto;
}
.recent-mess {
  display: flex;
  align-items: center;
  height: 80px;
  cursor: pointer;
}
.recent-mess:hover {
  background: #e4e5e6;
}
.recent-mess-active {
  display: flex;
  align-items: center;
  background: #e4e5e6;
  height: 80px;
  cursor: pointer;
}
.head-picture {
  padding: 10px;
}
.nick-name {
  color: #222;
  font-size: 16px;
}
.nick-name-title {
  padding: 5px;
  font-size: 16px;
  color: #222;
  border-bottom: solid 1px #eee;
  text-align: center;
}
.communication-record {
}
/*.communication {*/
/*  background: #fff;*/
/*  height: 165px;*/
/*  !* box-shadow: 0 0 5px 1px rgba(158, 179, 193, 0.5); *!*/
/*}*/
/* 设置滚动条的样式 */
::-webkit-scrollbar {
  width: 8px;
}
/* 滚动槽 */
::-webkit-scrollbar-track {
  /* -webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.3); */
  border-radius: 10px;
}
/* 滚动条滑块 */
::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background: rgba(251, 114, 153, 0.5);
  /* -webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.5); */
}

.talk_con {
  width: 100%;
  height: 600px;
  background-color: lightblue;
  opacity: 0.8;
}

.talk_show {
  width: 100%;
  height: 600px;
  background: #f2f3f5;
  margin: 10px auto 0;
  overflow: auto;
}

.talk_input {
  width: 580px;
  margin: 10px auto 0;
}

.whotalk {
  width: 80px;
  height: 30px;
  float: left;
  outline: none;
}

.talk_word {
  width: 450px;
  padding: 0px;
  float: left;
  margin-left: 10px;
  outline: none;
  text-indent: 10px;
}

.talk_sub {
  float: left;
  margin-left: 30px;
}

.atalk {
  margin: 10px;
}

.atalk span {
  display: inline-block;
  background: #ef8201;
  border-radius: 10px;
  color: #fff;
  padding: 5px 10px;
}

.btalk {
  margin: 10px;
  text-align: right;
}

.btalk span {
  display: inline-block;
  background: #0181cc;
  border-radius: 10px;
  color: #fff;
  padding: 5px 10px;
}

</style>
