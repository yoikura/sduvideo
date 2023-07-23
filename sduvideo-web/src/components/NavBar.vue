<script>
import {getVideoType} from '../api/api'

export default {
  name: 'NavBar',
  data: function () {
    return {
      catalogs: [
        // '全部',
        // '类型1',
        // '类型2',
        // '类型3',
        // '类型4',
        // '类型5',
        // '类型6',
        // '类型7',
        // '类型8',
        // '类型9',
        // '类型10'
      ]
    }
  },
  created(){
    this.getVideoType()
  },
  methods: {
    goClassify: function (item) {
      this.$router.push({name:'Classify', query:{tid: item.tid}})
    },
    async getVideoType(){
      let res = await getVideoType()
      // this.catalogs = res.data.data.map((item) => {
      //   return item.typeName
      // })
      this.catalogs = res.data.data
    }
  }
}
</script>

<template>
  <el-row class="common-content-row">

    <el-col class="common-content">
      <div class="grid-content bg-purple">
        <span v-for="(item,index) in catalogs"
              :key="index"
              class="bar-item"
              @click="goClassify(item)">{{item.typeName}}</span>
      </div>
    </el-col>

  </el-row>
</template>

<style scoped>
@import "../assets/css/common.css";
.el-row {
  background-color: rgba(0, 0, 0, 1);
  height: 73px;
  display: flex;
  align-items: center;
}
.bar-item {
  font-size: 16px;
  color: #fff;
  margin: 20px;
  cursor: pointer;
}

.bar-item:hover {
  cursor: pointer;
  color: grey;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
