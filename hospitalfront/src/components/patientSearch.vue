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
      <el-form :inline="true" :model="patient" :rules="rules" ref="patient" class="demo-form-inline" label-width="120px">
        <el-col span="8" label-position="left">
          <el-form-item label="住院号：" prop="inpatientNo" >
            <el-input v-model="patient.inpatientNo" :disabled="true" ></el-input>
          </el-form-item>

          <el-form-item label="患者姓名：" prop="patientName">
            <el-input v-model="patient.patientName"  ></el-input>
          </el-form-item>
          <el-form-item label="性别：" prop="sexCode">
            <el-select v-model="patient.sexCode"  style="width:202px">
              <el-option
                v-for="item in options4"
                :key="item.index"
                :label="item.label"
                :value="item.index">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年龄：" prop="age">
            <el-input v-model="patient.age"  ></el-input>
          </el-form-item>
          <el-form-item label="出生地：" prop="birthArea">
            <el-input v-model="patient.birthArea"  ></el-input>
          </el-form-item>
          <el-form-item label="职业：" prop="occupation">
            <el-select v-model="patient.occupation"style="width:202px">
              <el-option
                v-for="item in options2"
                :key="item.index"
                :label="item.label"
                :value="item.index">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="家庭电话：" prop="homeTel">
            <el-input v-model="patient.homeTel" ></el-input>
          </el-form-item>
          <el-form-item label="联系人电话：" prop="linkmanTel">
            <el-input v-model="patient.linkmanTel"  ></el-input>
          </el-form-item>
          <el-form-item label="科室编号："prop="deptNo">
            <el-input v-model="patient.deptNo"></el-input>
          </el-form-item>
          <el-form-item label="入院来源：" prop="inSource">
            <el-select v-model="patient.inSource" style="width:202px">
              <el-option label="门诊" value="1"></el-option>
              <el-option label="急诊" value="2"></el-option>
              <el-option label="转科" value="3"></el-option>
              <el-option label="转院" value="4"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col span="8">
          <el-form-item label="病历号：" prop="cardNo">
            <el-input v-model="patient.cardNo" :disabled="true" ></el-input>
          </el-form-item>
          <el-form-item label="患者状态" prop="patientState">
            <el-select v-model="patient.patientState"  style="width:202px">
              <el-option
                v-for="item in options5"
                :key="item.index"
                :label="item.label"
                :value="item.index">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="民族：" prop="nation">
            <el-input v-model="patient.nation" ></el-input>
          </el-form-item>
          <el-form-item label="国籍：" prop="country">
            <el-input v-model="patient.country"  ></el-input>
          </el-form-item>
          <el-form-item label="婚姻状况：" prop="mariCode">
            <el-select v-model="patient.mariCode"  style="width:202px">
              <el-option
                v-for="item in options1"
                :key="item.index"
                :label="item.label"
                :value="item.index">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位电话：" prop="workTel">
            <el-input v-model="patient.workTel"  ></el-input>
          </el-form-item>
          <el-form-item label="联系人姓名：" prop="linkmanName">
            <el-input v-model="patient.linkmanName"  ></el-input>
          </el-form-item>
          <el-form-item label="与患者关系：" prop="linkmanRelation">
            <el-select v-model="patient.linkmanRelation"  style="width:202px">
              <el-option
                v-for="item in options3"
                :key="item.index"
                :label="item.label"
                :value="item.index">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="病床编号：" prop="bedNo">
            <el-input v-model="patient.bedNo" ></el-input>
          </el-form-item>
          <el-form-item label="收住医师：" prop="houseDocNo">
            <el-input v-model="patient.houseDocNo" ></el-input>
          </el-form-item>
        </el-col>
        <el-col span="8">
          <el-form-item label="身份证号：" prop="idCard">
            <el-input v-model="patient.idCard" ></el-input>
          </el-form-item>
          <el-form-item label="出生年月：" required prop="birthday">
            <el-form-item >
              <el-date-picker  placeholder="选择日期" v-model="patient.birthday" style="width:202px"></el-date-picker>
            </el-form-item>
          </el-form-item>
          <el-form-item label="籍贯：" prop="dist">
            <el-input v-model="patient.dist" ></el-input>
          </el-form-item>
          <el-form-item label="工作单位：" prop="workName">
            <el-input v-model="patient.workName" ></el-input>
          </el-form-item>
          <el-form-item label="家庭住址：" prop="home">
            <el-input v-model="patient.home"  ></el-input>
          </el-form-item>
          <el-form-item label="联系人地址：" prop="linkmanAdd">
            <el-input v-model="patient.linkmanAdd" ></el-input>
          </el-form-item>
          <el-form-item label="合同单位名称：" prop="pactName">
            <el-input v-model="patient.pactName" ></el-input>
          </el-form-item>
          <el-form-item label="入院途径：" prop="inPath">
            <el-select v-model="patient.inPath" style="width:202px">
              <el-option label="本市" value="1"></el-option>
              <el-option label="外市" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="门诊诊断：" prop="diagnose">
            <el-input v-model="patient.diagnose"></el-input>
          </el-form-item>
        </el-col>
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
        _this.patient.inSource=json.inSource;
            _this.patient.inPath=json.inPath;
            _this.patient.houseDocNo=json.houseDocNo;
            _this.patient.prepayCost=json.prepayCost;
            _this.patient.diagnose=json.diagnose;
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
          pactName:'',

          inpatientNo:'',
          inSource:'',
          inPath:'',
          houseDocNo:'',


          prepayCost:'',


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
