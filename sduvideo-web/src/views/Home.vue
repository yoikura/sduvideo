<script>
import NavHead from '@/components/NavHead.vue'
import Carousel from '@/components/Carousel.vue'
import NavBar from '@/components/NavBar.vue'
import Recommond from '@/components/Recommond.vue'
import {rank, recommend} from '../api/api'
export default {
  name: 'home',
  components: {
    NavHead,
    Carousel,
    NavBar,
    Recommond
  },
  created () {
    this.getRank()
    this.getRecommend()
  },
  activated(){
    this.getRank()
    this.getRecommend()
  },
  data: function () {
    return {
      visible: false,
      user: null,
      // 由后台传入cards不需要require，后期修改
      carousels: [
        // require('../assets/img/blue.png'),
        // require('../assets/img/red.png'),
        // require('../assets/img/yellow.png'),
        // require('../assets/img/red.png')
      ],
      recommondItems: [
        // { name: '视频案例1', img: require('../assets/img/red.png'), type: '' },
        // { name: '视频案例2', img: require('../assets/img/blue.png'), type: '' },
        // { name: '视频案例3', img: require('../assets/img/red.png'), type: '' },
        // { name: '视频案例4', img: require('../assets/img/red.png'), type: '' },
        // { name: '视频案例5', img: require('../assets/img/red.png'), type: '' },
        // { name: '视频案例6', img: require('../assets/img/blue.png'), type: '' },
        // { name: '视频案例7', img: require('../assets/img/blue.png'), type: '' },
        // { name: '视频案例8', img: require('../assets/img/blue.png'), type: '' },
        // { name: '视频案例9', img: require('../assets/img/blue.png'), type: '' }

      ],
      rankinglist: [
        // { id: 1, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: false },
        // { id: 2, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/red.png'), showWord: true },
        // { id: 3, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true },
        // { id: 4, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true },
        // { id: 5, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true },
        // { id: 6, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true },
        // { id: 7, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true },
        // { id: 8, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true },
        // { id: 9, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true },
        // { id: 10, name: '视频案例', createTime: '2023-07-04', author: '作者', type: '类型', img: require('../assets/img/blue.png'), showWord: true }
      ]
    }
  },
  methods: {
    async getRank(){
      let res = await rank()
      this.rankinglist = res.data.data
      this.rankinglist[0].showWord = false
      this.carousels = []
      for(let i=0;i<5;i++){
        this.carousels.push(this.rankinglist[i])
      }
    },
    async getRecommend(){
      let res = await recommend()
      this.recommondItems = res.data.data
    },
    changeRecommend(){
      this.getRecommend()
    }
  }
}
</script>

<template>
  <el-container>
    <!-- header -->
    <el-header style="height:auto">
      <nav-head :user="user"></nav-head>
      <carousel :carousels="carousels"></carousel>
      <nav-bar style="text-align: center;"></nav-bar>
    </el-header>
    <!-- main -->
    <el-main>
      <el-row class="common-content-row">
        <el-col>
          <div>
            <p></p>
          </div>
        </el-col>
        <el-col :span="1">
          <button class="change-btn" v-on:click="changeRecommend()">换一换</button>
        </el-col>
        <el-col :span="23">
          <div class="common-content">
            <recommond :recommondItems="recommondItems"
                       :rankinglist="rankinglist"></recommond>
          </div>
        </el-col>
        <el-col>
          <div>
            <p></p>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>

</template>

<style scoped>
@import "../assets/css/common.css";
.change-btn{
  /*flex-direction: column;*/
  /*position: fixed;*/
  /*left: 120px;*/
  /*top: 650px;*/
  height: auto;
  width: 40px;
  padding: 9px;
  font-size: 12px;
  border-radius: 8px;
  border: 1px solid rgb(227,229,231);
  background-color: #fff;
  cursor: pointer;
  margin-right: 20px;
}

.change-btn:hover{
  background-color: rgb(227,229,231);
}
</style>
