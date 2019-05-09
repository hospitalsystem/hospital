<template>
  <el-container>
    <div class="formDIV">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="住院号" prop="inpatientNo">
          <el-input v-model="ruleForm.inpatientNo" class="elInput"></el-input>
        </el-form-item>
        <el-form-item label="收据号" prop="recipeNo">
          <el-input v-model="ruleForm.recipeNo" class="elInput"></el-input>
        </el-form-item>
        <el-form-item label="预交金额" prop="prepayCost">
          <el-input v-model="ruleForm.prepayCost" class="elInput"></el-input>
        </el-form-item>
        <el-form-item label="患者姓名" prop="patientName">
          <el-input v-model="ruleForm.patientName" class="elInput"></el-input>
        </el-form-item>
        <el-form-item label="是否结算" prop="issettlement">
          <el-select v-model="ruleForm.issettlement" placeholder="请选择是否结算" class="elInput">
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="结算方式" prop="prepayMethod">
          <el-select v-model="ruleForm.prepayMethod" placeholder="请选择支付方式" class="elInput">
            <el-option label="现金支付" value="1"></el-option>
            <el-option label="银行卡支付" value="2"></el-option>
            <el-option label="支票支付" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预交金状态" prop="prepayState">
          <el-select v-model="ruleForm.prepayState" placeholder="请选择预交金状态" class="elInput">
            <el-option label="收取" value="1"></el-option>
            <el-option label="返还" value="2"></el-option>
            <el-option label="补打" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作时间" required>
          <el-col :span="11">
            <el-form-item prop="date1">
              <el-date-picker  placeholder="选择日期" v-model="ruleForm.date1" style="width: 70%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="date2">
              <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 70%;"></el-time-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-container>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  import {uploadFileRequest} from '../utils/api'
  // Local Registration
  import moment from 'moment'

  import {isNotNullORBlank} from '../utils/utils'

  export default {
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var _this = this;
            var operDate1=_this.ruleForm.date1;
            console.log("operDate1:"+operDate1);
            operDate1=Date.parse(operDate1);
            console.log("时间戳:"+operDate1);
            operDate1=moment(operDate1).format('YYYY-MM-DD HH:mm:ss');
            console.log("operDate1:"+operDate1);
            var data={
              inpatientNo:_this.ruleForm.inpatientNo,
              recipeNo:_this.ruleForm.recipeNo,
              staffNo:"1",
              prepayCost:_this.ruleForm.prepayCost,
              patientName:_this.ruleForm.patientName,
              issettlement:_this.ruleForm.issettlement,
              prepayMethod:_this.ruleForm.prepayMethod,
              prepayState:_this.ruleForm.prepayState,
              operDate:operDate1
            };
            //console.log("data:"+JSON.stringify(data));
            //data=JSON.parse(JSON.stringify(data));
            data=JSON.stringify(data);
            console.log("data:"+data);
            postRequest("/prepayCollect",data).then(resp=> {
              if (resp.status == 200 && resp.data.status == 'success') {
                console.log("resp:"+JSON.stringify(resp));
                alert("提交成功");
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
    },
    data() {
      return {
          ruleForm: {
            inpatientNo: '',
            recipeNo: '',
            prepayCost:'',
            issettlement: '',
            prepayMethod:'',
            patientName:'',
            prepayState:'',
            date1: '',
            date2: ''
          },
          rules: {
            inpatientNo: [
              { required: true, message: '请输入住院号', trigger: 'blur' },
              //{ min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            recipeNo: [
              { required: true, message: '请输入收据号', trigger: 'blur' }
            ],
            patientName: [
              { required: true, message: '请输入患者姓名', trigger: 'blur' }
            ],
            date1: [
              { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
            ],
            date2: [
              { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
            ],
            prepayCost: [
              { required: true, message: '请输入预交金额', trigger: 'blur' }
            ],
            prepayMethod: [
              { required: true, message: '请选择支付方式', trigger: 'change' }
            ],
            issettlement: [
              { required: true, message: '请选择是否结算', trigger: 'change' }
            ],
            prepayState: [
              { required: true, message: '请选择预交金状态', trigger: 'change' }
            ]
          }
        }
      }
  }
</script>
<style>
  .formDIV{
    width:800px;
    height: 600px;
    margin-top: 50px;
    float: left;
    margin-left: 0;
    margin-right: 0;
  }
  .elInput{
    display: block;
    width: 35%;
    margin-left: 220px;
  }
</style>
