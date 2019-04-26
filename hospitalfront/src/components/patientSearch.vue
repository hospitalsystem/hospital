<template>
  <el-container>
    <el-header class="cate_mana_header">
      <el-input
        placeholder="请输入病历号"
        v-model="cardNo" style="width: 200px;">
      </el-input>
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="getPatient">查询</el-button>
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="patientChange">修改</el-button>
    </el-header>
    <el-main class="cate_mana_second">
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <el-form-item label="患者姓名：">
          <el-input v-model="patient.patientName" placeholder="patient_name" ></el-input>
        </el-form-item>
        <el-form-item label="身份证号：">
          <el-input v-model="patient.idCard" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="性别：">
          <el-input v-model="patient.sexCode" placeholder="sex_code" >
          </el-input>
        </el-form-item>
        <el-form-item label="民族：">
          <el-input v-model="patient.nation" placeholder="请输入病人姓名"></el-input>
        </el-form-item>
        <el-form-item label="出生年月：">
          <el-input v-model="patient.birthday" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="年龄：">
          <el-input v-model="patient.age" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="国籍：">
          <el-input v-model="patient.country" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="籍贯：">
          <el-input v-model="patient.dist" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="出生地：">
          <el-input v-model="patient.birthArea" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="婚姻状况">
          <el-input v-model="patient.mariCode" placeholder="请选择" >
          </el-input>
        </el-form-item>
        <el-form-item label="工作单位：">
          <el-input v-model="patient.workName" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="职位：">
          <el-input v-model="patient.occupation" placeholder="请选择" >
          </el-input>
        </el-form-item>
        <el-form-item label="单位电话：">
          <el-input v-model="patient.workTel" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="家庭住址：">
          <el-input v-model="patient.home" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="家庭电话：">
          <el-input v-model="patient.homeTel" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="联系人姓名：">
          <el-input v-model="patient.linkmanName" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="与患者关系：">
          <el-input v-model="patient.linkmanRelation" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="联系人地址：">
          <el-input v-model="patient.linkmanAdd" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="联系人电话：">
          <el-input v-model="patient.linkmanTel" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="合同单位名称：">
          <el-input v-model="patient.pactName" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="入院来源：">
          <el-input v-model="inpatient.inSource" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="入院途径：">
          <el-input v-model="inpatient.inPath" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="收住医师：">
          <el-input v-model="inpatient.houseDocNo" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="门诊诊断：">
          <el-input v-model="outpatient.diagnose" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
        <el-form-item label="预收金额：">
          <el-input v-model="prepay.prepayCost" placeholder="请输入病人姓名" ></el-input>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {getRequest} from '../utils/api'

  export default {
    methods: {
      getPatient(){
        var _this = this;
        postRequest("/getPatient",{cardNo : this.cardNo}).then(resp=> {
          if (resp.status == 200) {
            console.log("resp:" + JSON.stringify(resp));
            console.log("resp.data:" + JSON.stringify(resp.data));
         //   console.log("resp.data.json:" + JSON.stringify(resp.data.json));
       var data=resp.data;
       var json = data.json;
        _this.patient.idCard=json.idCard;
        _this.patient.sexCode=json.sexCode;
        _this.patient.patientName=json.patientName;
        //_this.patient.cardNo=json.cardNo;
        _this.patient.nation=json.nation;
        _this.patient.birthday=json.birthday;
        _this.patient.age=json.age;
        _this.patient.country=json.country;
        _this.patient.dist=json.dist;
        _this.patient.birthArea=json.birthArea;
        _this.patient.mariCode=json.mariCode;
        _this.patient.workName=json.workName;
        _this.patient.occupation=json.occupation;
        _this.patient.workTel=json.workTel;
        _this.patient.home=json.home;
        _this.patient.homeTel=json.homeTel;
        _this.patient.linkmanName=json.linkmanName;
        _this.patient.linkmanRelation=json.linkmanRelation;
        _this.patient.linkmanAdd=json.linkmanAdd;
        _this.patient.linkmanTel=json.linkmanTel;
        _this.patient.pactName=json.pactName;
        _this.inpatient.inSource=json.inSource;
            _this.inpatient.inPath=json.inPath;
            _this.inpatient.houseDocNo=json.houseDocNo;
            _this.prepay.prepayCost=json.prepayCost;
            _this.outpatient.diagnose=json.diagnose;
        //console.log("data:"+JSON.stringify(data));
        //data=JSON.parse(JSON.stringify(data));
      }
    }, resp => {
    if (resp.response.status == 403) {
      _this.$message({
        type: 'error',
        message: resp.response.data
      });
    }
  });
  },
      patientChange(){

      }

  },
    data() {
      return {
        form: '',
        patient:{
          cardNo:'',
          patientName:'',
          sexCode:'',
          idCard:'',
          nation:'',
          birthday:'',
          age:'',
          country:'',
          dist:'',
          birthArea:'',
          mariCode:'',
          workName:'',
          occupation:'',
          workTel:'',
          home:'',
          homeTel:'',
          linkmanName:'',
          linkmanRelation:'',
          linkmanAdd:'',
          linkmanTel:'',
          pactName:''
        },
        inpatient:{
          inSource:'',
          inPath:'',
          houseDocNo:'',
        },
        prepay:{
          prepayCost:'',
        },
        outpatient:{
          diagnose:''
        },
        options1:[{
          index:1,
          label:'未婚',
        }, {
          index: 2,
          label: '已婚',
        },{
          index: 3,
          label:'丧偶',
        }, {
          index: 4,
          label: '离婚',
        }],

        options2:[{
          index:11,
          label:'国家公务人员',
        }, {
          index: 13,
          label: '专业技术人员',
        },{
          index: 17,
          label:'职员',
        }, {
          index: 21,
          label: '企业管理人员',
        },
          {
            index:24,
            label:'工人',
          }, {
            index: 27,
            label: '农民',
          },{
            index: 31,
            label:'学生',
          }, {
            index: 37,
            label: '现役军人',
          },
          {
            index:11,
            label:'国家公务人员',
          }, {
            index: 51,
            label: '自由职业者',
          },{
            index: 54,
            label:'个人经营者',
          }, {
            index: 70,
            label: '无业人员',
          },
          {
            index: 80,
            label:'退（离）休人员',
          }, {
            index: 90,
            label: '其他',
          }],
        options3:[{
          index: 1,
          label:'配偶',
        }, {
          index: 2,
          label: '子',
        },{
          index: 3,
          label:'女',
        }, {
          index: 4,
          label: '孙子、孙女或外孙子、外孙女',
        },
          {
            index:5,
            label:'父母',
          }, {
            index: 6,
            label: '祖父母或外祖父母',
          },{
            index: 7,
            label:'兄弟姐妹',
          }, {
            index: 8,
            label: '其他',
          }],
        options4:[{
          index: 1,
          label:'男',
        }, {
          index: 2,
          label: '女',
        }]
      }
    }
  }
</script>
<style>
  .patientAdd {
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    margin-top: 20px;
    padding-top: 10px;
  }
  .cate_mana_header {
    background-color: #ececec;
    margin-top: 20px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }
  .cate_mana_second{
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    margin-top: 20px;
    padding-top: 10px;
  }
</style>
