<template>
  <el-form :model="loginForm" :rules="fieldRules" ref="loginForm" label-position="left" label-width="0px"
           class="demo-ruleForm login-container">
    <span class="tool-bar">
      <!-- 主题切换 -->
      <theme-picker style="float:right;" class="theme-picker" :default="themeColor"
                    @onThemeChange="onThemeChange"></theme-picker>
    </span>
    <h2 class="title" style="padding-left:22px;">系统登录</h2>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-col :span="12">
        <el-form-item prop="captcha">
          <el-input type="test" v-model="loginForm.captcha" auto-complete="off" placeholder="验证码, 单击图片刷新"
                    style="width: 100%;">
          </el-input>
        </el-form-item>
      </el-col>
      <el-col class="line" :span="1">&nbsp;</el-col>
      <el-col :span="11">
        <el-form-item>
          <img style="width: 100%;" class="pointer" :src="loginForm.src" @click="refreshCaptcha">
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:48%;" @click.native.prevent="reset">重 置</el-button>
      <el-button type="primary" style="width:48%;" @click.native.prevent="login" :loading="loading">登 录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import {mapState} from 'vuex'
import Cookies from "js-cookie"
import ThemePicker from "@/components/ThemePicker"

export default {
  name: 'Login',
  components: {
    ThemePicker
  },
  data() {
    return {
      loading: false,
      loginForm: {
        username: 'wgy',
        password: '327052',
        captcha: '',
        src: '',
        checkCaptcha: ''
      },
      fieldRules: {
        account: [
          {required: true, message: '请输入账号', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      },
      checked: true
    }
  },
  methods: {
    login() {
      if (this.loginForm.captcha === this.loginForm.checkCaptcha) {
        this.loading = true
        let userInfo = {
          username: this.loginForm.username,
          password: this.loginForm.password,
        }
        this.$api.login.login(userInfo).then((res) => {
          if (res.message != null) {
            this.$message({
              message: res.message,
              type: 'error'
            })
          } else {
            Cookies.set('token', res.data.token) // 放置token到Cookie
            sessionStorage.setItem('user', res.data.name) // 保存用户到本地会话
            // this.$message({
            //   message:JSON.stringify(res.data.name),
            //   type:"info"
            // })
            this.$store.commit('menuRouteLoaded', false) // 要求重新加载导航菜单
            this.$router.push('/')  // 登录成功，跳转到主页
          }
          this.loading = false
        }).catch((res) => {
          this.$message({
            message: res.message,
            type: 'error'
          })
          this.loading = false
        });
      }else if(!this.loginForm.captcha){
        this.$message({
          message:"请输入验证码",
          type:"warning"
        })
      }else{
        this.$message({
          message:"验证码错误",
          type:"warning"
        })
        this.refreshCaptcha()
      }
    },
    refreshCaptcha: function () {
      //this.loginForm.src = this.global.baseUrl + "/captcha?t=" + new Date().getTime();
      this.$api.login.captcha().then((res) => {
        if (res.data != null) {
          this.loginForm.src = res.data.imageBase64
          this.loginForm.checkCaptcha = res.data.verifyCode
        }
      })
    },
    reset() {
      this.$refs.loginForm.resetFields()
    },
    // 切换主题
    onThemeChange: function (themeColor) {
      this.$store.commit('setThemeColor', themeColor)
    }
  },
  mounted() {
    this.refreshCaptcha()
  },
  computed: {
    ...mapState({
      themeColor: state => state.app.themeColor
    })
  }
}
</script>

<style lang="scss" scoped>
.login-container {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 100px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;

  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #505458;
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }
}
</style>
