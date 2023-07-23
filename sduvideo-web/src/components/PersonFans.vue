<script>
import {getAttentionTo} from '../api/api'

export default {
  name: 'PersonFans',
  data: function () {
    return {
      fansList: [
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
    this.getAttentionTo()
  },
  activated(){
    this.getAttentionTo()
  },
  methods: {
    currentChange (value) {
      console.log(value)
      this.currentPage = value
    },
    async getAttentionTo(){
      let params = {
        uid: this.userInfo.uid
      }
      let res = await getAttentionTo(params)
      this.fansList = res.data.data
    }
  }
}
</script>
<template>
  <div>
    <el-row class="item-line"
            v-for="item in fansList"
            :key="item.uid">
      <el-col :span="3">
        <el-avatar :src="item.avatar"
                   :size='60'></el-avatar>
      </el-col>
      <el-col :span="17">
        <div class="nickname">{{item.userName}}</div>
      </el-col>
<!--      <el-col :span="4">-->
<!--        <el-button type="primary"-->
<!--                   class="remove" @click="deleteAttention(item)">取消关注</el-button>-->
<!--      </el-col>-->
    </el-row>
    <el-row class="common-pagination">
      <el-pagination background
                     layout="prev, pager, next, jumper"
                     :page-size="pageSize"
                     @current-change="currentChange"
                     :total="fansList.length">
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
