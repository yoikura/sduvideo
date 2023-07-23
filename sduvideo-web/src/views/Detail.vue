<template>
<!--  <LogReg v-if="$store.state.loginView"></LogReg>-->
  <el-container class="video-container">
    <el-header style="height:auto">
      <nav-head :user="user"></nav-head>
    </el-header>
    <el-main>
      <el-row style="margin-left: 300px">
        <el-col class="left-container">
          <div class="viewbox-report">
            <h1 class="video-title">{{ detailList.name }}</h1>
            <div class="video-data">
              <Icon type="ios-arrow-dropright" size="16"/><span class="item">{{ detailList.playNum }}</span>
              <Icon type="ios-barcode-outline" size="16"/><span class="item">{{Math.floor(detailList.duration/60) }}:{{ detailList.duration%60 }}</span>
              <Icon type="ios-time-outline" size="16"/><span class="item">{{ detailList.author }}</span>
              <Icon type="ios-alert-outline" size="16" color="red"/><span class="item">未经作者授权，禁止转载</span>
            </div>
          </div>
          <video muted="muted" autoplay="autoplay" :poster=detailList.img width="668" height="376" controls="controls" :src=detailList.address></video>
          <div class="arc_toolbar_report">
            <div class="toolbar-left">
              <div class="toolbar-span blue" :class="[isLike?'choose':'none-choose']" @click="changeLike"><Icon type="md-thumbs-up" size="30"/>{{ detailList.likeNum }}</div>
              <!--          <div class="toolbar-span blue"><Icon type="logo-yen" size="25"/>{{ detailList.coinsChange }}</div>-->
              <div class="toolbar-span blue" :class="[isStar?'choose':'none-choose']" @click="changeFavorite"><Icon type="md-star" size="33"/>{{ detailList. favoriteNum}}</div>
              <div class="toolbar-span blue" @click="copy()"><Icon type="ios-share-alt" size="33"/></div>
            </div>
            <div class="toolbar-right">
<!--              <div class="toolbar-span blue"><Icon type="ios-alert-outline" size="20"/>&nbsp;稿件投诉</div>-->
<!--              <div class="toolbar-span blue"><Icon type="ios-bookmarks-outline" size="20"/>&nbsp;记笔记</div>-->
            </div>
          </div>
          <div class="introduction-container">
            <div v-if="!showClose" class="desc-info-text" ref="icontainer">{{detailList.description}}</div>
            <div v-else>
              <div class="desc-info-text" ref="icontainer" :class="[isClose?'introduction-close':'introduction-open']">{{detailList.description}}</div>
              <span class="blue" style="cursor: pointer;" @click="changeClose">{{ closeMsg }}</span>
            </div>

<!--             <span @click="valueClose">test</span> -->
          </div>
          <!--      <div class="b-img">-->
          <!--        <img width="668" src="https://images.weserv.nl/?url=//i0.hdslb.com/bfs/sycp/creative_img/202303/d8f5f24cc1bff5ed317cbae55361d23f.jpg@!web-video.webp" alt="">-->
          <!--      </div>-->
          <div class="conmment-container">
            <div class="reply-header">
              <span class="nav-title-text">评论</span>
              <span class="total-reply">{{ commentList.length }}</span>
            </div>
            <div class="reply-wrap">
              <div class="main-reply-box">
                <!--            <div class="reply-box-avatar">-->
                <!--              <div v-if="!$store.state.isLogin" class="bili-avatar"  style="width: 40px;height: 40px;"></div>-->
                <!--              <div v-else class="bili-user-avatar">-->
                <!--                <img :src=$store.state.constant.base_url+$store.state.userInfo.avatar style="width: 40px;height: 40px;" alt="">-->
                <!--              </div>-->
                <!--            </div>-->
                <div class="reply-box-wrap">
                  <textarea class="reply-box-textarea" placeholder="发一条友善的评论" v-model="comment"></textarea>
                  <div class="no-login-mask" v-if="!this.user.uid">
                    <span class="no-login-span">请先</span>
                        <span class="no-login-btn" @click="goLogin">登录</span>
                    <span class="no-login-span">后发表评论 (・ω・)</span>
                  </div>
                </div>
                <Button type="primary" :class="[this.user.uid?'reply-box-send':'no-login-reply-box-send']" @click="submitComment">发布</Button>
              </div>
              <div class="reply-list">
                <div class="root-reply-item" v-for="item in commentList" :key="item.id">
                  <div class="root-reply-container">
                    <div class="root-reply-avatar">
                      <div class="bili-avatar">
                        <img @click="" style="width: 40px;height: 40px;" :src=item.avatar alt="">
                      </div>
                    </div>
                    <div class="content-wrap">
                      <div class="user-info">
                        <div class="user-name" @click="">{{ item.nickName }}</div>
                      </div>
                      <div class="root-reply">{{ item.content }}</div>
                      <div class="reply-info">{{item.createTime}}</div>
                    </div>
                  </div>
                  <div class="bottom-line"></div>
                </div>
                <div class="reply-end">没有更多评论</div>
              </div>
            </div>
          </div>
        </el-col>
          <el-col class="right-container">
            <div class="up-info">
              <div class="u-face">
                <img class="bili-avatar" style="width: 48px;height: 48px;" :src=detailList.avatar>
              </div>
              <div class="up-info-right">
                <div class="name">
                  <span @click="">{{ detailList.author }}</span>
<!--                  <div class="up-info-right-message blue"><Icon type="md-mail" size="15"/>发消息</div>-->
                </div>
                <div class="desc">{{ detailList.signature }}</div>
                <div class="btn-panel" v-if="!this.user.uid||this.user.uid!==this.detailList.uid">
                  <Button class="charge" @click="toMessage()">私信</Button>
                  <Button type="primary" class="not-follow" @click="attend()" v-if=isAttend>取消关注</Button>
                  <Button type="primary" class="follow" @click="attend()" v-else>+关注 {{ detailList.followed }}</Button>
                </div>
              </div>
            </div>
              <div class="rec-list">
                <div class="video-page-card" v-for="item in recommendList">
                  <div class="pic-box" @click=""><img width="141" height="80" :src=item.img alt="" @click="goDetail(item.id)"></div>
                  <div class="info">
                    <div class="title" @click=""><a href="javascript:void(0);">{{ item.name }}</a></div>
                    <div class="desc blue" style="cursor: pointer;" @click=""><Icon type="ios-contact" size="14"/>{{ item.author }}</div>
                    <div class="desc" style="cursor: default;">
                      <Icon type="ios-arrow-dropright" size="14"/> {{ item.playNum }}
                      <Icon type="ios-barcode-outline" size="14" style="margin-left: 30px"/> {{ item.likeNum }}
                    </div>
                  </div>
                </div>
              </div>
          </el-col>
      </el-row>
    </el-main>
    <!-- 左侧 -->

  </el-container>
</template>

<script>
// import common from '../common.js'
// import LogReg from './LogReg.vue'

import {
  attention, comment, deleteAttention, getAttention,
  getVideoById,
  getVideoCommentById, play, playVideo,
  recommend, selectAttend,
  selectFavorite,
  selectLike,
  setFavoriteVideo,
  setLikeVideo
} from '../api/api'
import NavHead from '../components/NavHead'
import QRCode from 'qrcodejs2'

export default{
  name:'Detail',
  components:{
    // LogReg
    NavHead,
  },
  data(){
    return{
      detailList:{},
      commentList:[],
      recommendList:[],
      isLike:false,
      isCoin:false,
      isStar:false,
      isClose:true,
      isAttend:false,
      comment:'',
      closeMsg:'展开更多',
      showClose:false,
      user:{
        uid:null
      }
    }
  },
  watch: { $route (to, from) { this.$router.go(0) } },
  methods: {
    goLogin: function () {
      this.$router.push('/login')
    },
    openQCode(){
      let qrcode = new QRCode("qrcode", {
        width: 200, // 二维码宽度，单位像素
        height: 200, // 二维码高度，单位像素
        text: this.detailList.address // 生成二维码的链接
      });
    },
    copy() {
      let input = document.createElement("input"); // 创建input对象
      input.value = window.location.href; // 设置复制内容
      document.body.appendChild(input); // 添加临时实例
      input.select(); // 选择实例内容
      document.execCommand("Copy"); // 执行复制
      document.body.removeChild(input); // 删除临时实例
      this.$message.success('链接已复制到粘贴板');
    },
    async toMessage(){
      let isCancel = false
      let isGoLogin = false
      if(!this.user.uid){
        await this.$alert('请先登录后再进行操作!', '', {
          confirmButtonText: '确定',
          callback: action => {
            if(action === 'confirm'){
              this.goLogin()
              isGoLogin = true
            }else{
              isCancel = true
            }
          }
        });
      }
      if(isCancel === true){
        return
      }
      if(isGoLogin === true){
        return
      }
      this.$router.push({path:'message', query:{uid: this.detailList.uid}})
    },
    async attend(){
      let isCancel = false
      if(!this.user.uid){
        await this.$alert('请先登录后再进行操作!', '', {
          confirmButtonText: '确定',
          callback: action => {
            if(action === 'confirm'){
              this.goLogin()
            }else{
              isCancel = true
            }
          }
        });
      }
      if(isCancel === true){
        return
      }
      let params = {
        followedUid:this.detailList.uid,
        followingUid:this.user.uid
      }
      if(this.isAttend){
        deleteAttention(params)
        this.isAttend = !this.isAttend
        this.$message.success('取消关注！');
        this.detailList.followed--
      }else{
        attention(params)
        this.isAttend = !this.isAttend
        this.$message.success('已关注！');
        this.detailList.followed++
      }
    },
    async initInfo(){
      let res = await selectLike({uid:this.user.uid,vid:this.$route.query.vid})
      this.isLike = res.data.isLiked === 'true';
      let res1 = await selectFavorite({uid:this.user.uid,vid:this.$route.query.vid})
      this.isStar = res1.data.isFavorite === 'true';
      let res2 = await selectAttend({followedUid:this.detailList.uid,followingUid:this.user.uid})
      this.isAttend = res2.data.isAttend === 'true'
    },
    goDetail(vid){
      console.log(vid)
      this.$router.push({path:'/detail', query:{vid: vid, date:new Date().getTime()}})
    },
    async getVideoInfo(){
      let res = await getVideoById({vid:this.$route.query.vid})
      this.detailList = res.data.data
    },
    async getVideoComments(){
      let res = await getVideoCommentById({vid:this.$route.query.vid})
      this.commentList = res.data.data
    },
    async recommend(){
      let res = await recommend()
      this.recommendList = res.data.data
    },
    async getComment(){
      // let res
      // // 获取评论数
      // res=await this.$api.get('getCommentByvideo/'+this.$route.params.id)
      // this.detailList.commentsChange=common.numChange(res.data.result.count)
      // console.log(res.data.result.count);
      // //获取评论
      // this.commentList=res.data.result.rows
      // for(let ele of this.commentList){
      //   ele.created_at=this.$moment(ele.created_at).format('YYYY-MM-DD HH:MM')
      //   res=await this.$api.get('getUsers/'+ele.user_id)
      //   ele.user_name=res.data.nickname
      //   ele.avatar=res.data.avatar
      // }
    },

    async getRecommend(){
      // let res
      // res=await this.$api.get('getVideo',{limit:10})
      // this.recommendList=res.data
      // //删除自己本身
      // let num=this.recommendList.findIndex(item=>item.id==this.$route.params.id)
      // this.recommendList.splice(num,1)
    },

    async changeLike(){
      let isCancel = false
      if(!this.user.uid){
        await this.$alert('请先登录后再进行操作!', '', {
          confirmButtonText: '确定',
          callback: action => {
            if(action === 'confirm'){
              this.goLogin()
            }else{
              isCancel = true
            }
          }
        });
      }
      if(isCancel === true){
        return
      }
      let res = await setLikeVideo({uid: this.user.uid,vid: this.detailList.id,isLike:this.isLike})
      //用户未登录 todo
      if(res.data.return_code === 'success'){
        this.isLike=!this.isLike
        if(this.isLike){
          this.detailList.likeNum++
        }else{
          this.detailList.likeNum--
        }
      }
    },

    async changeFavorite(){
      let isCancel = false
      if(!this.user.uid){
        await this.$alert('请先登录后再进行操作!', '', {
          confirmButtonText: '确定',
          callback: action => {
            if(action === 'confirm'){
              this.goLogin()
            }else{
              isCancel = true
            }
          }
        });
      }
      if(isCancel === true){
        return
      }
      let res = await setFavoriteVideo({uid: this.user.uid,vid: this.detailList.id,isFavorite:this.isStar})
      //用户未登录 todo
      if(res.data.return_code === 'success'){
        this.isStar=!this.isStar
        if(this.isStar){
          this.detailList.favoriteNum++
        }else{
          this.detailList.favoriteNum--
        }
      }
    },

    async submitComment(){
      let params = {
        uid: this.user.uid,
        vid: this.detailList.id,
        content: this.comment,
      }
      let res = await comment(params)
      if(res.data.return_code === 'success'){
        await this.getVideoComments()
        await this.getVideoInfo()
        this.comment = ''
      }else{
        this.$message.error("服务器错误，请稍后重试")
      }
      // if(this.$store.state.isLogin){
      //   if(!this.comment){
      //     alert('内容不能为空')
      //   }else{
      //     this.$api.post('users/addComment',{video_id:this.$route.params.id,user_id:this.$store.state.userInfo.id,talk:this.comment})
      //     this.comment=''
      //     this.getComment()
      //   }
      // }else{
      //   this.$store.commit('changeLoginView',true)
      // }
    },
    changeClose(){
      this.isClose=!this.isClose
      if(this.isClose){
        this.closeMsg='展开更多'
      }else{
        this.closeMsg='收起'
      }
    },
    valueClose(){
      // let height=parseFloat(window.getComputedStyle(this.$refs.icontainer).height)
      let height=this.$refs.icontainer.scrollHeight
      // console.log(height);
      if(height>92){
        this.showClose=true
      }else{
        this.showClose=false
      }
    },
    play(){
      if(!this.user.uid){
        return
      }
      let params = {
        uid: this.user.uid,
        vid: this.$route.query.vid
      }
      playVideo(params)
    }
  },
  created(){
    // this.$store.commit('changeLogin')
    // this.$store.commit('getUser')
    if(this.$store.state._user.Info!==null){
      this.user = this.$store.state._user.Info
    }
    console.log(this.$route.query.vid)
    this.getVideoInfo()
    this.getVideoComments()
    this.recommend()
    this.initInfo()
    this.play()
  },
  activated(){
    // this.$store.commit('changeLogin')
    // this.$store.commit('getUser')
    console.log(this.$route.query.vid)
    this.getVideoInfo()
    this.getVideoComments()
    this.recommend()
  },
  updated(){
    this.valueClose()
  }
}

</script>

<style>
.video-container{
  max-width: 2540px;
  min-width: 1080px;
  margin: 0 auto;
  padding: 0 10px;
  /*display: flex;*/
  /*justify-content: center;*/
  /*box-sizing: content-box;*/
  /*position: relative;*/
}

.left-container{
  width: 668px;
}

.right-container{
  width: 350px;
  /*flex: none;*/
  margin-left: 30px;
  /*position: relative;*/
}

/* --------------------------------左侧------------------------------- */

.viewbox-report{
  height: 86px;
  padding-top: 20px;
  box-sizing: border-box;
}

.video-title{
  line-height: 28px;
  font-size: 20px;
  color: black;
  font-weight: 500;
  margin-bottom: 6px;
  /*overflow: hidden;*/
  white-space: nowrap;
  text-overflow: ellipsis;
}

.video-data{
  font-size: 13px;
  color: #9499A0;
  display: flex;
  align-items: center;
  height: 24px;
  line-height: 24px;
  position: relative;
  overflow: hidden;
  /*display: flex;*/
  /*align-items: center;*/
  /*overflow: hidden;*/
  box-sizing: border-box;

}

.item{
  flex-shrink: 0;
  margin-right: 12px;
  display: inline-flex;
  align-items: center;
  overflow: hidden;
  margin-left: 3px;
}

.arc_toolbar_report{
  box-sizing: content-box;
  padding-top: 16px;
  padding-bottom: 12px;
  height: 28px;
  line-height: 28px;
  border-bottom: 1px solid #E3E5E7;
}

.toolbar-left{
  float: left;
  position: relative;
  user-select: none;
  font-size: 13px;
  color: #61666D;
  font-weight: 500;
  display: flex;
  align-items: center;
  height: 100%;
}

.toolbar-span{
  width: 92px;
  margin-right: 8px;
  cursor: pointer;
  white-space: nowrap;
  position: relative;
  display: inline-flex;
}

.blue{
  transition: color .2s ease;
}

.blue:hover{
  color: rgb(0,174,236);
}

.choose{
  color: rgb(0,174,236);
}

.none-choose{
  color: rgb(97,102,109);
}



.toolbar-right{
  position: relative;
  float: right;
  display: flex;
  align-items: center;
  color: #61666D;
  height: 100%;
  font-size: 13px;
}

.desc-info-text {
  white-space: pre-line;
  word-break: break-all;
  font-size: 15px;
  color: #18191C;
  letter-spacing: 0;
  line-height: 24px;
  padding-top: 20px;
  padding-bottom: 20px;
}

.introduction-container{
  border-bottom: 1px solid #E3E5E7;
}

.introduction-close{
  height: 92px;
  overflow: hidden;
}

.introduction-open{
  height: none;
}

.b-img{
  padding-top: 20px;
  padding-bottom: 20px;
}

.reply-header{
  height: 30px;
  line-height: 30px;
  margin-bottom: 10px;
}

.nav-title-text{
  font-weight: 500;
  font-size: 20px;
  color: black;
}

.total-reply{
  font-size: 13px;
  margin-left: 5px;
}

.main-reply-box{
  display: flex;
  height: 50px;
}

.reply-box-avatar{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80px;
  height: 50px;
}

.bili-avatar{
  display: block;
  position: relative;
  background-image: url(data:image/gif;base64,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);
  background-size: cover;
  border-radius: 50%;
}

.bili-user-avatar{
  display: block;
  position: relative;
  background-size: cover;
  /* background-color: transparent; */
  border-radius: 50%;
  overflow: hidden;
  height: 40px;
  width: 40px;
}

.reply-box-wrap{
  position: relative;
  flex: 1;
}

.reply-box-textarea{
  width: 100%;
  height: 100%;
  padding: 5px 10px;
  border: 1px solid var(--Ga1);
  border-radius: 6px;
  background-color: rgb(241,242,243);
  font-family: inherit;
  line-height: 38px;
  color: black;
  resize: none;
  outline: none;
}

.reply-box-send{
  display: flex;
  margin-left: 10px;
  height: 100% !important;
  background-color: rgb(127,214,245) !important;
  border: 0 !important;
  display: flex;
  align-items: center;
  justify-content: center;
}

.reply-box-send:hover{
  background-color:rgb(0,174,236) !important;
}

.no-login-reply-box-send{
  display: flex;
  margin-left: 10px;
  height: 100% !important;
  background-color: rgb(241,242,243) !important;
  color: rgb(148,153,160) !important;
  border: 0 !important;
  cursor: default !important;
  display: flex;
  align-items: center;
  justify-content: center;
}

.no-login-mask{
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  width: 100%;
  height: 100%;
  border-radius: 6px;
  font-size: 12px;
  background-color: rgb(241,242,243);
  cursor: pointer;
}

.no-login-span{
  padding-left: 5px;
  padding-right: 5px;
}

/* .ivu-icon{
    line-height: 28px !important;
} */

.no-login-btn{
  padding: 4px 9px;
  border-radius: 4px;
  color:white;
  background: rgb(0,174,236);
}

.no-login-btn:hover{
  background-color: rgb(64,197,241);
}

.reply-list{
  margin-top: 14px;
  padding-bottom: 100px;
}

.reply-end{
  margin-top: 20px;
  font-size: 13px;
  color: rgb(148,153,160);
  text-align: center;
  cursor: default;
}

/* --------------------------------右侧------------------------------- */
.up-info{
  padding-top: 20px;
  min-height: 58px;
  width: 350px;
  margin: 0 0 18px 0;
  display: flex;
  align-items: center;
}

.u-face{
  align-self: flex-start;
  width: 48px;
  height: 48px;
  position: relative;
  flex-shrink: 0;
}

.up-info-right{
  margin-left: 12px;
  flex: 1;
  overflow: auto;
}

.name{
  color: #FB7299;
  height: 22px;
  line-height: 22px;
  font-size: 15px;
  display: flex;
  align-items: center;
  font-weight: 500;
  cursor: pointer;
}

.up-info-right-message{
  margin-left: 10px;
  font-size: 13px;
  color: rgb(97,102,109);
}


.desc{
  margin: 3px 0 10px 0;
  font-size: 13px;
  line-height: 16px;
  height: 16px;
  color: #9499A0;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.btn-pannel{
  clear: both;
  margin-top: 5px;
  white-space: nowrap;
  width: 290px;
}

.charge{
  margin-right: 12px;
  width: 109px;
  color: #FF6699 !important;
  border: 1px solid #FF6699 !important;
  position: relative;
}

.charge:hover{
  background-color: rgb(255,236,241) !important;
}

.follow{
  width: 150px;
  height: 30px;
  background: rgb(0,174,236) !important;
  align-items: center;
  justify-content: center;
}

.follow:hover{
  background-color: rgb(0,184,246) !important;
}

.not-follow{
  width: 150px;
  height: 30px;
  background: gray !important;
  align-items: center;
  justify-content: center;
}

.not-follow:hover{
  background-color: grey !important;
}

.rec-list{
  margin-top: 18px;
  width: 350px;
}

.video-page-card{
  display: flex;
  margin-bottom: 12px;
}

.pic-box{
  position: relative;
  width: 141px;
  height: 80px;
  border-radius: 6px;
  overflow: hidden;
  cursor: pointer;
}

.info{
  flex:1;
  padding-left: 10px;
}

.info .title{
  width: 199px;
  height: 38px;
  text-overflow: ellipsis;
  display:-webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient:vertical;
  overflow: hidden;
}

.info .title a{
  font-weight: 500 !important;
}

.info .desc{
  margin-bottom: 0;
}

.toolbar-right .ivu-icon{
  line-height: 30px !important;
}

.root-reply-container{
  padding: 22px 0 0 80px;
}

.root-reply-item{
  position: relative;
}

.root-reply-avatar{
  display: flex;
  justify-content: center;
  position: absolute;
  left: 0px;
  width: 80px;
  cursor: pointer;
}

.root-reply-avatar .bili-avatar{
  width: 40px;
  height: 40px;
  overflow: hidden;
}

.content-wrap{
  position: relative;
}

.user-info{
  display: flex;
  align-items: center;
  margin-bottom: 4px;
  font-size: 13px;
}

.user-name{
  font-weight: 500;
  margin-right: 5px;
  color: rgb(251,114,153);
  cursor: pointer;
}

.root-reply{
  font-size: 15px;
  line-height: 24px;
  padding: 2px 0;
  color: black;
}

.reply-info{
  margin-top: 2px;
  font-size: 13px;
  line-height: 24px;
  color:rgb(148,153,160)
}

.bottom-line{
  margin-left: 80px;
  border-bottom: 1px solid #ccc;
  margin-top: 14px;
}

video{
  width: 100%;
  object-fit: cover;
}
</style>
