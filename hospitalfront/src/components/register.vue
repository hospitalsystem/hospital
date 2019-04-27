//register组件

<template>

  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="姓名" prop="staffName">

      <el-input v-model="ruleForm.staffName"></el-input>

    </el-form-item>

    <el-form-item label="账号" prop="staffSpell">

      <el-input v-model="ruleForm.staffSpell"></el-input>

    </el-form-item>

    <el-form-item label="密码" prop="staffPassword">

      <el-input type="password" v-model="ruleForm.staffPassword" auto-complete="off"></el-input>

    </el-form-item>

    <el-form-item label="确认密码" prop="checkPass">

      <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input>

    </el-form-item>

    <el-form-item>

      <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>

      <el-button @click="resetForm('ruleForm')">重置</el-button>

    </el-form-item>

  </el-form>

</template>

<script>

  import axios from '../axios'
  export default {
    data() {

      var validatePass = (rule, value, callback) => {

        if (value === '') {

          callback(new Error('请输入密码'));

        } else {

          if (this.ruleForm.checkPass !== '') {

            this.$refs.ruleForm.validateField('checkPass');

          }

          callback();

        }

      };

      var validatePass2 = (rule, value, callback) => {

        if (value === '') {

          callback(new Error('请再次输入密码'));

        } else if (value !== this.ruleForm.staffPassword) {

          callback(new Error('两次输入密码不一致!'));

        } else {

          callback();

        }

      };

      return {

        activeName: 'second',

        ruleForm: {

          staffName: '',

          staffPassword: '',

          staffSpell:''
        },

        rules: {

          staffName: [

            { required: true, message: '请输入姓名', trigger: 'blur' },

            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }

          ],
          staffSpell:[

            { required: true, message: '请输入账号', trigger: 'blur' },

            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
          ],

          staffPassword: [

            { required: true, validator: validatePass, trigger: 'blur' }

          ],

          checkPass: [

            { required: true, validator: validatePass2, trigger: 'blur' }

          ],

        }

      };

    },

    methods: {

      submitForm(formName) {

        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log("this.ruleForm:"+this.ruleForm);
            axios.userRegister(this.ruleForm)

              .then(({data}) => {

                if (data.success) {

                  this.$message({

                    type: 'success',

                    message: '注册成功'

                  });

                } else {

                  this.$message({

                    type: 'info',

                    message: '用户名已经存在'

                  });

                }

              })

          } else {

            console.log('error submit!!');

            return false;

          }

        });

      },

      resetForm(formName) {

        this.$refs[formName].resetFields();

      }

    }

  }

</script>
