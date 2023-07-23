<script>
import {getAttention,deleteAttention} from '../api/api'

export default {
  name: 'PersonBlackList',
  data: function () {
    return {
      blackList: [
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') },
        // { nickName: 'nickName', createTime: '2019-11-10 13:18:53', headPicture: require('../assets/img/head_picture.jpg') }
      ],
      currentPage: 1,
      pageSize: 10,
      userInfo: ''
    }
  },
  created () {
    this.userInfo = this.$store.state._user.Info
    this.getAttention()
  },
  activated(){
    this.getAttention()
  },
  methods: {
    currentChange (value) {
      console.log(value)
      this.currentPage = value
    },
    async getAttention(){
      let params = {
        uid: this.userInfo.uid
      }
      let res = await getAttention(params)
      this.blackList = res.data.data
    },
    async deleteAttention(item){
      let params = {
        followingUid: this.userInfo.uid,
        followedUid: item.uid
      }
      let res = await deleteAttention(params)
      if(res.data.return_code === 'success'){
        this.$message.success("已取消关注")
        this.getAttention()
      }
    }
  }
}
</script>
<template>
  <div>
    <el-row class="item-line"
            v-for="item in blackList"
            :key="item.uid">
      <el-col :span="3">
        <el-avatar :src="item.avatar"
                   :size='60'></el-avatar>
      </el-col>
      <el-col :span="17">
        <div class="nickname">{{item.userName}}</div>
<!--        <div class="createtime">添加时间：{{blackList[idx-1+(currentPage-1)*pageSize].createTime}}</div>-->
      </el-col>
      <el-col :span="4">
        <el-button type="primary"
                   class="remove" @click="deleteAttention(item)">取消关注</el-button>
      </el-col>
    </el-row>
    <el-row class="common-pagination">
      <el-pagination background
                     layout="prev, pager, next, jumper"
                     :page-size="pageSize"
                     @current-change="currentChange"
                     :total="blackList.length">
      </el-pagination>
    </el-row>
  </div>
</template>

<style scoped>
@import "../assets/css/common.css";
.item-line {
  padding-top: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #e5e9ef;
}

.nickname {
  font-size: 16px;
  text-align: left;
  padding-top: 10px;
  padding-bottom: 15px;
}
.createtime {
  color: #6d757a;
  text-align: left;
  font-size: 12px;
}
.remove {
  margin-top: 15px;
}
</style>
