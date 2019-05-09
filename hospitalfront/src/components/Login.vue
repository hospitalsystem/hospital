<template>
  <p class="login">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="登录" name="first">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </p>

</template>

<script>
  import axios from '../axios'
  export default {

    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
         } else {
          callback();
        }
      };
      return {
        activeName: 'first',
        ruleForm: {
          name: '',
          pass: '',
        },
        rules: {
          name: [
            { required: true, message: '请输入您的名称', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],

          pass: [
            { required: true, validator: validatePass, trigger: 'blur' }
          ]
        },
      };
    },

    methods: {
      //选项卡切换
      handleClick(tab, event) {
      },
      //重置表单
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      //提交表单
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log("this.ruleForm:"+JSON.stringify(this.ruleForm));
            axios.userLogin(this.ruleForm).then(({ data }) => {
              console.log("data:"+JSON.stringify(data));
                //账号不存在
                if (!data.status) {
                  this.$message({
                    type: 'info',
                    message: data.message
                  });
                  return;
                }
                //账号存在
                if (data.status) {
                  this.$message({
                    type: 'success',
                    message: '登录成功'
                  });
                  //拿到返回的token和username，并存到store
                  var token = data.token;
                  console.log("token:"+token);
                  var username = data.name;
                  this.$store.dispatch('UserLogin', token);
                  this.$store.dispatch('UserName', username);
                  //跳到目标页
                  this.$router.push('Home');
                }
              });
          }else {
            console.log('error submit!!');
            return false;
          }
        });
      },
    }
  }

</script>

<style rel="stylesheet/scss" lang="scss">

  .login {

    width: 400px;

    margin: 0 auto;

  }

  #app {

    font-family: 'Avenir', Helvetica, Arial, sans-serif;

    -webkit-font-smoothing: antialiased;

    -moz-osx-font-smoothing: grayscale;

    text-align: center;

    color: #2c3e50;

    margin-top: 60px;

  }

  .el-tabsitem {

    text-align: center;

    width: 60px;

  }

</style>
