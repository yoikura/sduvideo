<script>
import NavHead from '@/components/NavHead.vue'
import Card from '@/components/Card.vue'
import BackTop from '@/components/BackTop.vue'
import {getVideoByType, search} from '../api/api'

export default {
  name: 'Classify',
  components: {
    NavHead,
    Card,
    BackTop
  },
  created () {
    console.log(this.$route.query.tid)
    this.getVideoByType()
  },
  data: function () {
    return {
      user: null,
      oneLine: 4,
      classifyItems: [
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' },
        // { name: '辉夜大小姐想让我告白 ~天才们的恋爱头脑战~', img: require('../assets/img/card.png'), type: '恋爱，搞笑，校园，日常' }
      ],
      loaded: 12,
      loading: false,
      searchContent: ''
    }
  },
  computed: {
    noMore () {
      return this.loaded >= 42
    },
    disabled () {
      return this.loading || this.noMore
    }
  },
  methods: {
    async getVideoByType(){
      let tid = this.$route.query.tid;
      //tid === 0 搜索视频
      if(tid === '0'){
        let content = this.$route.query.searchContent;
        this.searchContent = content
        let res = await search({searchContent: content})
        this.classifyItems = res.data.data
      }else{
        let params = {
          tid: tid
        }
        let res = await getVideoByType(params)
        this.classifyItems = res.data.data
      }
    },
    // 滚动条在Y轴上的滚动距离
    getScrollTop: function () {
      var scrollTop = 0
      var bodyScrollTop = 0
      var documentScrollTop = 0
      if (document.body) {
        bodyScrollTop = document.body.scrollTop
      }
      if (document.documentElement) {
        documentScrollTop = document.documentElement.scrollTop
      }
      scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop
      return scrollTop
    },
    // 文档的总高度
    getScrollHeight: function () {
      var scrollHeight = 0
      var bodyScrollHeight = 0
      var documentScrollHeight = 0
      if (document.body) {
        bodyScrollHeight = document.body.scrollHeight
      }
      if (document.documentElement) {
        documentScrollHeight = document.documentElement.scrollHeight
      }
      scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight
      return scrollHeight
    },
    // 浏览器视口的高度
    getWindowHeight: function () {
      var windowHeight = 0
      if (document.compatMode === 'CSS1Compat') {
        windowHeight = document.documentElement.clientHeight
      } else {
        windowHeight = document.body.clientHeight
      }
      return windowHeight
    },
    scroll: function () {
      window.onscroll = () => {
        let bottomOfWindow = this.getScrollTop() + this.getWindowHeight() === this.getScrollHeight()

        if (bottomOfWindow && !this.disabled) {
          this.loading = true
          setTimeout(() => {
            // 加载数据
            for (let i = 0; i < 4; i++) {
              this.classifyItems.push({ name: '抱歉，我要毁灭一下这个地球', img: require('../assets/img/card2.png'), type: '' })
            }
            this.loaded += this.oneLine
            this.loading = false
          }, 1000)
        }
      }
    }
  },
  mounted () {
    // this.scroll()
  }
}
</script>

<template>
  <el-container id="container">
    <!-- header -->
    <el-header style="height:auto">
      <nav-head :user="user" :search="searchContent"></nav-head>
    </el-header>
    <!-- main -->
    <el-main>
      <el-row class="classify-content common-content-row">
        <el-col>
          <div class="grid-content">
            <p></p>
          </div>
        </el-col>
        <el-col>
          <div class="grid-content common-content">
            <div style="height:auto;overflow-y:hidden;">
              <ul>
                <li v-for="idx in Math.ceil(loaded/oneLine)"
                    :key="idx">
                  <el-row class="item-row">
                    <el-col :span="24/oneLine"
                            v-for="i in oneLine"
                            :key="i">
                      <card :card="classifyItems[(idx-1)*oneLine+i-1]" :searchContent="searchContent"></card>
                    </el-col>
                  </el-row>
                </li>
                <p v-if="loading">加载中...</p>
                <p v-if="noMore">别再拉了，裤子要掉了(〒︿〒)</p>
              </ul>
            </div>
          </div>
        </el-col>
        <el-col>
          <div class="common-right-content">
            <back-top></back-top>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<style scoped>
@import "../assets/css/common.css";
.el-header {
  padding: 0;
  height: auto;
}
.el-main {
  padding-left: 0px;
  padding-right: 0px;
}
.classify-content {
  display: flex;
}
.grid-content {
  height: 100%;
}
.classify-title {
  display: flex;
  margin-bottom: 20px;
}
.type-span {
  margin-right: 14px;
  color: rgba(0, 0, 0, 0.34);
}
.tag-span {
  margin: 0 4px;
  padding: 0 10px;
  color: rgba(0, 0, 0, 0.87);
}
ul {
  padding: 0px;
}
.item-row {
  margin-bottom: 20px;
}
</style>
