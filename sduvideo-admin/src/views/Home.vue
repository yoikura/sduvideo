<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" /> 播放总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            100
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-money" /> 视数数量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            54
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-bank-card" /> 用户数量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            5
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-s-shop" /> 累计上传</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            20
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>

      <el-col :span="12">
        <div id="right" style="width: 500; height: 450px"></div>
      </el-col>

    
    </el-row>

  </div>
</template>

<script>



  import * as echarts from 'echarts'
  export default {
  name: "Home",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}

    }
  },
  mounted() {  // 页面元素渲染之后再触发
   
  var  option = {
    
  title: {
    text: '类型播放统计',

    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data: [
        { value: 1048, name: '音乐' },
        { value: 735, name: '舞蹈' },
        { value: 580, name: '游戏' },
        { value: 484, name: '教育' },
        { value: 300, name: '运动' }
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};


var Roption = {
  
  title: {
    text: '主要用户投稿',
    left: 'center',
    top: 20,
    textStyle: {
      
    }
  },
  tooltip: {
    trigger: 'item'
  },
  visualMap: {
    show: false,
    min: 80,
    max: 600,
    inRange: {
  
    }
  },
  series: [
    {
      name: '数量',
      type: 'pie',
      radius: '55%',
      center: ['50%', '50%'],
      data: [
        { value: 335, name: 'yoikura' },
        { value: 310, name: 'few' },
        { value: 274, name: 'gwe' },
        { value: 235, name: 'bhrt' },
        { value: 400, name: 'test' }
      ].sort(function (a, b) {
        return a.value - b.value;
      }),
      roseType: 'radius',
     
  
      animationType: 'scale',
      animationEasing: 'elasticOut',
      animationDelay: function (idx) {
        return Math.random() * 200;
      }
    }
  ]
};

   

    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    myChart.setOption(option);
    var right = document.getElementById('right');
    var myChart = echarts.init(right);
    myChart.setOption(Roption);
    


  }
}
</script>

<style scoped>

</style>
