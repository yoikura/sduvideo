<script>
import {getUserInfo,updateUserInfo} from '../api/api'

export default {
  name: 'PersonMyInfo',
  data: function () {
    return {
      userInfo: {
        // uid: '1',
        // nickName: 'yoikura',
        // signature: '我是个性签名',
        // gender: 0,
        // birthday: null,
        // tel: '',
        // email: '',
        // address: { province: '110000', city: '110000', area: '110101', town: null }
      }
    }
  },
  created () {
    this.userInfo = this.$store.state._user.Info
    this.getUserInfo()
  },
  activated(){
    this.getUserInfo()
  },
  methods: {
    onSubmit () {
      console.log('submit form')
    },
    async getUserInfo(){
      let res = await getUserInfo({uid: this.userInfo.uid})
      this.userInfo = res.data.data
      this.userInfo.address = {
        province: res.data.data.province,
        city: res.data.data.city,
        area: res.data.data.area,
        town: null
      }
    },
    async updateUserInfo(){
      let params = {
        uid: this.userInfo.uid,
        nickName: this.userInfo.nickName,
        signature: this.userInfo.signature,
        gender: this.userInfo.gender,
        birthday: this.userInfo.birthday,
        tel: this.userInfo.tel,
        email: this.userInfo.email,
        province: this.userInfo.address.province,
        city: this.userInfo.address.city,
        area: this.userInfo.address.area,
      }
      let res = await updateUserInfo(params)
      if(res.data.return_code === 'success'){
        this.$message.success("修改成功")
      }else{
        this.$message.success(res.data.tips)
      }
    },
  }
}
</script>

<template>
  <div>
    <el-row class="item-line">
      <el-form ref="form"
               :model="userInfo"
               label-width="100px">
        <el-form-item label="昵称：">
          <el-input v-model="userInfo.nickName"
                    class="normal input-class"></el-input>
        </el-form-item>
        <el-form-item label="个人简介：">
          <el-input type="textarea"
                    v-model="userInfo.signature"
                    class="normal textarea"></el-input>
        </el-form-item>
        <el-form-item label="性别："
                      class="normal">
          <el-radio-group v-model="userInfo.gender">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期：">
          <el-date-picker type="date"
                          placeholder="选择日期"
                          v-model="userInfo.birthday"
                          class="normal input-class"></el-date-picker>
        </el-form-item>
        <el-form-item prop="email"
                      label="邮箱："
    >
          <el-input v-model="userInfo.email" disabled = "true"
                    class="normal input-class"></el-input>
        </el-form-item>
        <el-form-item label="手机号码：">
          <el-input type="text"
                    disabled = "true"
                    v-model="userInfo.tel"
                    maxlength="13"
                    show-word-limit
                    class="normal input-class">
          </el-input>
        </el-form-item>
        <el-form-item label="地址：">
          <v-region v-model="userInfo.address"
                    class="normal"></v-region>
        </el-form-item>
        <div style="border-bottom: 1px solid #e5e9ef;margin-bottom:20px"></div>
        <el-button type="primary"
                   @click="updateUserInfo">保存</el-button>
      </el-form>
    </el-row>
  </div>
</template>

<style scoped>
.item-line {
  padding-top: 20px;
  padding-bottom: 20px;
}

.normal {
  display: flex;
}
.tag {
  width: 115px;
}
.input-class {
  width: 200px;
}
.textarea {
  width: 400px;
  height: 150px;
}
</style>
