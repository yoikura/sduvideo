<script>
export default {
  name: 'Card',
  props: ['card','searchContent'],
  methods:{
    goDetail(vid){
      console.log(vid)
      this.$router.push({name:'Detail', query:{vid: vid}})
    },
    highlight(searchContent, text) {
      const highlightStr = `<span style="color: deepskyblue">${searchContent}</span>`
      //正则表达式//中间的内容都会当作匹配字符来使用，而不是数据变量
      //如果需要根据数据变量动态的创建正则表达式，则手动new RegExp
      // RegExp正则表达式构造函数
      //参数1:匹配模式字符串，它会根据这个字符串创建正则对象
      //参数2:匹配模式，要写到字符串中
      //gi表示正则字符串全部匹配和不区分大小写
      const reg = new RegExp(searchContent, 'gi')
      return text.replace(reg, highlightStr)
    },
  }
}
</script>

<template>
  <el-card :body-style="{ padding: '0px' }"
           :title="card.name">
    <img :src="card.img"
         class="card-img"
          @click="goDetail(card.id)">
    <div style="padding-left:8px;padding-right:5px; " @click="goDetail(card.id)">
      <p class="card-name line-limit-length" v-if="typeof(searchContent) !== 'undefined'&&searchContent !== ''" v-html="highlight(searchContent,card.name)"></p>
      <p class="card-name line-limit-length" v-else>{{card.name}}</p>
      <div class="bottom clearfix">
        <p class="card-type line-limit-length">{{ card.type }}</p>
        <div class="card-type line-limit-length" style="float: right;font-size: 14px">作者：{{ card.author }}</div>
      </div>
    </div>
  </el-card>
</template>

<style scoped>
.el-card {
  height: 235px;
  width: 280px;
  cursor: pointer;
}
.card-img {
  height: 165px;
  width: 278px;
}
.card-name {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.87);
  text-align: left;
}
.card-name:hover {
  color: #00a1d6;
}
.card-type {
  font-size: 12px;
  color: rgba(0, 0, 0, 0.34);
  text-align: left;
}
.line-limit-length {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin: 0px;
}
</style>
