<script>
export default {
  name: 'Carousel',
  data: function () {
    return {
    }
  },
  props: ['carousels'],
  methods:{
    goDetail(vid){
      console.log(vid)
      this.$router.push({name:'Detail', query:{vid: vid}})
    }
  }

}
</script>
<template>
  <el-carousel :interval="4000"
               type="card"
               height="480px">
    <el-carousel-item v-for="(item,index) in carousels"
                      :key="index">
      <img :src="item.img"
           height="480px"
           width="100%"
           class="card-img" @click="goDetail(item.id)"/>
<!--      <div :style="{background:`url(${item.img})`}" class="card-img">-->
        <h1 class="card-title">{{item.name}}</h1>
<!--      </div>-->
    </el-carousel-item>
  </el-carousel>

</template>
<style scoped>
.el-carousel {
  background-image: linear-gradient(
    to right,
    rgba(0, 0, 0, 1),
    rgba(0, 0, 0, 0.34),
    rgba(0, 0, 0, 1)
  );
  /* background-color: rgba(0, 0, 0, 0.8); */
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.card-img{
  height: 480px;
  width: 100%;
  position: relative;
}

.card-img:hover{
  opacity: 0.8;
}

.card-title{
  position: absolute;
  bottom: 0;
  color: floralwhite;
  backdrop-filter: blur(10px) saturate(150%) drop-shadow(4px 4px 10px);
  width: 100%;
  margin: 0 0 0 0;
}
</style>
