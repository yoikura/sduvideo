<script>
import UserInfoFloat from '@/components/UserInfoFloat.vue'
export default {
  name: 'HeadPicture',
  components: {
    UserInfoFloat
  },
  props: {
    userInfo: {
      type: Object
    },
    size: {
      type: Number | String,
      default: 48
    }
  }
}
</script>
<template>
  <div class="img-div"
       :style="{height:size+'px',width:size+'px'}">
    <el-avatar :src="userInfo.avatar"
               :size="parseInt(size)"></el-avatar>
    <div class="info">
      <user-info-float :size="size" :user="userInfo"></user-info-float>
    </div>

  </div>
</template>
<style scoped>
@import "../assets/css/common.css";
.img-div {
  position: relative;
}
.info {
  border-radius: 5px;
  display: none;
}
.img-div:hover > .info {
  display: block;
}
</style>
