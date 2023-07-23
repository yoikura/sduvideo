<script>
// import FooterBar from '@/components/FooterBar.vue'
import PuzzleVerification from 'vue-puzzle-verification'
import {addUser, login, loginByEmail, sendMsg} from '../api/api'
import {
  mapMutations,
  mapState,
} from 'vuex'

export default {
  name: 'Login',
  components: {
    // FooterBar,
    PuzzleVerification
  },
  data: function () {
    return {
      isVerificationShow: false,
      puzzleImgList: [
        require('../assets/img/red.png')
      ],
      tel:'',
      code:'',
      timer: null,
      iscode: true,
      time: 59,
      nickName:'',
      email:'',
      password:'',
      loginByEmail:false,
      emailLogin:'',
      passwordLogin:''
    }
  },
  methods: {
    onSignIn: function () {
      let container = document.getElementById('dowebok')
      container.classList.remove('right-panel-active')
    },
    onSignUp: function () {
      let container = document.getElementById('dowebok')
      container.classList.add('right-panel-active')
    },
    handleSuccess () {
      this.isVerificationShow = false

    },
    async getCode() {
      let regsTel = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (!regsTel.test(this.tel)) {
        this.$message.warning('请输入正确格式的手机号！');
        return
      }
      let res = await sendMsg({tel: this.tel})
      // if(res.data.return_code ==='success'){
      //   this.code = res.data.data
      // }
      this.iscode = false
      this.timer = setInterval(() => {
        this.time--
        if (this.time == -1) {
          this.iscode = true
          this.time = 59
          clearInterval(this.timer)
        }
      }, 1000)
    },
    async login () {
      //邮箱登录
      if(this.loginByEmail){
        let params = {
          email:this.emailLogin,
          password:this.passwordLogin
        }
        let res = await loginByEmail(params)
        if(res.data.return_code === 'success'){
          this.isVerificationShow = true
          this.$store.commit("_user/addUser",res.data.data)
          this.$store.commit("_user/changeLogin",true)
          if(this.isVerificationShow === true){
            await this.$router.push({name: 'Home'})
          }
        }
        else{
          this.$message.error('邮箱或密码错误');
        }
      }else {
        let regsTel = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
        if (!regsTel.test(this.tel)) {
          this.$message.warning('请输入正确格式的手机号！');
          return
        }
        let regsCode = /^[0-9]{6,6}$/gim;
        if (!regsCode.test(this.code)) {
          this.$message.warning('请输入6位数字验证码！');
          return
        }
        let params = {
          tel: this.tel,
          code: this.code
        }
        let res = await login(params)
        if(res.data.return_code === 'success'){
          this.isVerificationShow = true
          console.log("asdasdasdasdasdasda")
          this.$store.commit("_user/addUser",res.data.data)
          this.$store.commit("_user/changeLogin",true)
          if(this.isVerificationShow === true){
            await this.$router.push({name: 'Home'})
          }
        }
        else{
          this.$message.error('手机号或验证码错误');
        }
      }
    },
    async register(){
      let regNickName = /^[A-Za-z0-9]+$/
      if(!regNickName.test(this.nickName)){
        this.$message.warning('用户名应由字母或数字组成');
        return
      }
      if(this.nickName.length<5){
        this.$message.warning('用户名长度应不小于5');
        return
      }
      let regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if (!regEmail.test(this.email)) {
        this.$message.warning('请输入正确格式的邮箱！');
        return
      }
      if(this.password.length<6){
        this.$message.warning('密码长度应不小于5');
        return
      }
      let params = {
        nickName: this.nickName,
        email: this.email,
        password: this.password
      }
      let res = await addUser(params)
      if(res.data.return_code === 'success'){
        this.$message.success('注册成功');
        this.nickName=''
        this.email=''
        this.password=''
      }
    },
    changeLogin(){
      this.loginByEmail = !this.loginByEmail
    }
  }
}
</script>
<template>
  <el-container style="height:100%"
                class="container-class">
    <el-header></el-header>
    <el-main>
      <el-row>
        <el-col :span="2">
          <p></p>
        </el-col>
        <el-col :span="20">
          <div class="puzzle-box">
            <PuzzleVerification v-model="isVerificationShow"
                                :puzzleImgList="puzzleImgList"
                                :height="150"
                                :width="300"
                                :blockSize="30"
                                :deviation="4"
                                blockType="puzzle"
                                :onSuccess="handleSuccess" />
          </div>
          <div class="dowebok"
               id="dowebok">
            <div class="form-container sign-up-container">
              <form action="#">
                <h1>注册</h1>
                <input type="text"
                       placeholder="昵称" v-model="nickName">
                <input type="email"
                       placeholder="电子邮箱" v-model="email">
                <input type="password"
                       placeholder="密码" v-model="password">
                <button @click="register()">注册</button>
              </form>
            </div>
            <div class="form-container sign-in-container">
              <form action="#">
                <h1>登录</h1>
                <div v-if="loginByEmail">
                  <input type=""
                         placeholder="邮箱" v-model="emailLogin">
                  <input type="password"
                         placeholder="密码" v-model="passwordLogin">
                </div>
                <div v-else>
                  <input type=""
                         placeholder="手机号" v-model="tel">
                  <input type=""
                         placeholder="验证码" v-model="code">
                  <div class="code" @click="getCode()">
                    <div v-if="iscode">获取验证码</div>
                    <div style="color: #a7a7a7;" v-else>{{time}}s后重新获取</div>
                  </div>
                </div>
                  <button @click="login">登录</button>
                <div class="email-login" @click="changeLogin()">
                  <div v-if="loginByEmail">手机登录</div>
                  <div v-else>邮箱登录</div>
                </div>
              </form>
            </div>
            <div class="overlay-container">
              <div class="overlay">
                <div class="overlay-panel overlay-left">
                  <h1>已有帐号？</h1>
                  <p>请使用您的帐号进行登录</p>
                  <button class="ghost"
                          id="signIn"
                          @click="onSignIn">登录</button>
                </div>
                <div class="overlay-panel overlay-right">
                  <h1>没有帐号？</h1>
                  <p>立即注册加入我们，和我们一起开始旅程吧</p>
                  <button class="ghost"
                          id="signUp"
                          @click="onSignUp">注册</button>
                </div>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="2">
          <p></p>
        </el-col>

      </el-row>
    </el-main>
  </el-container>

</template>

<style scoped>
* {
  box-sizing: border-box;
}

.code{
  position: absolute;
  right: 60px;
  top: 253px;
  /** 文本1 */
  font-size: 12px;
  font-weight: 400;
  letter-spacing: 0;
  line-height: 0;
  height: 25px;
  color: rgba(0, 183, 214, 1);
  padding-top: 13px;
}

.code:hover{
  cursor: pointer;
}

.email-login{
  position: absolute;
  right: 60px;
  top: 353px;
  /** 文本1 */
  font-size: 12px;
  font-weight: 400;
  letter-spacing: 0;
  line-height: 0;
  height: 25px;
  padding-top: 13px;
}

.email-login:hover{
  cursor: pointer;
}

body {
  font-family: "Montserrat", sans-serif;
  /* background: #f6f5f7; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: -20px 0 50px;
}
.puzzle-box {
  position: absolute;
  top: 0px;
  right: 560px;
  z-index: 999;
  box-shadow: 0 0 5px 1px rgba(158, 179, 193, 0.5);
  border-radius: 20px;
  /* bottom: 0;
       left: 0; */
}
.container-class::after {
  background-image: url("https://dogefs.s3.ladydaily.com/~/source/wallhaven/full/nm/wallhaven-nmdk28.jpg?w=2560&h=1440&fmt=webp");
  background-size: cover;
  opacity: 0.5;
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;

}

h1 {
  font-weight: bold;
  margin: 0;
}

p {
  font-size: 14px;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 20px 0 30px;
}

span {
  font-size: 12px;
}

a {
  color: #333;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

.dowebok {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow: hidden;
  width: 768px;
  max-width: 100%;
  min-height: 480px;
  float: right;
}

.form-container form {
  background: #fff;
  display: flex;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #ddd;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}

.social-container a:hover {
  background-color: #eee;
}

.form-container input {
  background: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
  outline: none;
}

button {
  border-radius: 20px;
  border: 1px solid #409eff;
  background: #409eff;
  color: #fff;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
  cursor: pointer;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background: transparent;
  border-color: #fff;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.sign-up-container {
  left: 0;
  width: 50%;
  z-index: 1;
  opacity: 0;
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.overlay {
  background: #409eff;
  background: linear-gradient(to right, #409eff, #ff0000) no-repeat 0 0 / cover;
  color: #fff;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateY(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-panel {
  position: absolute;
  top: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0 40px;
  height: 100%;
  width: 50%;
  text-align: center;
  transform: translateY(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-right {
  right: 0;
  transform: translateY(0);
}

.overlay-left {
  transform: translateY(-20%);
}

/* Move signin to right */
.dowebok.right-panel-active .sign-in-container {
  transform: translateY(100%);
}

/* Move overlay to left */
.dowebok.right-panel-active .overlay-container {
  transform: translateX(-100%);
}

/* Bring signup over signin */
.dowebok.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
}

/* Move overlay back to right */
.dowebok.right-panel-active .overlay {
  transform: translateX(50%);
}

/* Bring back the text to center */
.dowebok.right-panel-active .overlay-left {
  transform: translateY(0);
}

/* Same effect for right */
.dowebok.right-panel-active .overlay-right {
  transform: translateY(20%);
}
</style>
