<template>
  <el-container>
    <el-header class="cate_mana_header">
      <el-input
        placeholder="请输入住院号进行出院登记"
        v-model="inpatient.inpatientNo" style="width: 200px;" @keyup.enter.native="leaveInpatientInfoSearch">
      </el-input>
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="leaveInpatientInfoSearch" >查询</el-button>
    </el-header>

    <el-main class="cate_mana_second">
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <el-form-item label="患者姓名:">
          <el-input readonly="true" v-model="inpatient.patientName" placeholder="患者姓名"></el-input>
        </el-form-item>
        <el-form-item label="住院科室:">
          <el-input readonly="true" v-model="inpatient.deptNo" placeholder="住院科室"></el-input>
        </el-form-item>
        <el-form-item label="病历号:">
          <el-input readonly="true" v-model="patient.cardNo" placeholder="病历号"></el-input>
        </el-form-item>
        <el-form-item label="入院日期:">
          <el-input readonly="true" v-model="inpatient.inDate" placeholder="入院日期"></el-input>
        </el-form-item>
        <el-form-item label="病床号:">
          <el-input readonly="true" v-model="inpatient.bedNo" placeholder="病床号"></el-input>
        </el-form-item>
        <el-form-item label="性别:">
          <el-input readonly="true" v-model="patient.sexCode" placeholder="性别"></el-input>
        </el-form-item>
        <el-form-item  label="预交金总额:" class="prepayAll">
          <el-input readonly="true" v-model="prepayAll" placeholder="预交金总额"></el-input>
        </el-form-item>
        <el-form-item  label="消费总额:" class="costAll">
          <el-input readonly="true" v-model="costAll" placeholder="消费总额"></el-input>
        </el-form-item>
        <el-form-item  label="费用余额:" class="remain">
          <el-input readonly="true" v-model="remain" placeholder="费用余额"></el-input>
        </el-form-item>
        <el-form-item label="收取金额:" class="charge" v-show="remain < 0">
          <el-input v-model="charge" placeholder="收取金额" ></el-input>
        </el-form-item>
        <el-form-item label="找零:" class="change" v-show="change > 0">
          <el-input readonly="true" v-model="change" placeholder="找零"></el-input>
        </el-form-item>
        <el-form-item  label="出院日期:" class="outDate">
          <el-input readonly="true" v-model="inpatient.outDate" placeholder="出院日期"></el-input>
        </el-form-item>
        <el-form-item label="出院状态:" class="outState">
          <el-select v-model="inpatient.outState" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.index"
              :label="item.label"
              :value="item.index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item class="button" >
          <el-button type="primary" @click="leavePatientRegister" v-show="bed.bedState != ''">出院</el-button>
        </el-form-item>
      </el-form>
    </el-main>

    <el-main class="cate_mana_main">
      <p>预交金详情</p>
      <el-table
        :data="prepay"
        stripe
        style="width: 100%">
        <el-table-column
          prop="inpatientNo"
          label="住院编号">
        </el-table-column>
        <el-table-column
          prop="happenNo"
          label="发生编号">
        </el-table-column>
        <el-table-column
          prop="patientName"
          label="姓名">
        </el-table-column>
        <el-table-column
          prop="prepayCost"
          label="预交金额">
        </el-table-column>
        <el-table-column
          prop="recipeNo"
          label="收据号">
        </el-table-column>
        <el-table-column
          prop="prepayState"
          label="预交金状态">
        </el-table-column>
        <el-table-column
          prop="issettlement"
          label="是否结算">
        </el-table-column>
        <el-table-column
         prop="staffNo"
         label="操作员编号">
        </el-table-column>
        <el-table-column
          prop="operDate"
          label="操作日期">
        </el-table-column>
        <el-table-column
          prop="prepayMethod"
          label="预交金支付方式">
        </el-table-column>
      </el-table>
    </el-main>

    <el-main class="cate_mana_main">
      <p>消费详情</p>
      <el-table
        :data="cost"
        stripe
        style="width: 100%">
        <el-table-column
          prop="inpatientNo"
          label="住院编号">
        </el-table-column>
        <el-table-column
          prop="happenNo"
          label="发生序号">
        </el-table-column>
        <el-table-column
          prop="costName"
          label="开销名称">
        </el-table-column>
        <el-table-column
          prop="costAmount"
          label="费用金额">
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>

</template>

<script>
  import moment from "moment"
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'

  export default {
    data(){
      return {
        inpatient: {
          inpatientNo:'',
          patientName:'',
          idCard:'',
          inDate:'',
          inSource:'',
          inPath:'',
          status:'',
          deptNo:'',
          bedNo:'',
          houseDocNo:'',
          chargeDocNo:'',
          chiefDocNo:'',
          dutyNurseNo:'',
          outDate:'',
          outState:''
        },
        patient:{
          cardNo:'',
          patientName: '',
          sexCode: '',
          idcardNo: '',
          nation: '',
          birthday: '',
          age: '',
          country: '',
          dist: '',
          birthArea: '',
          mariCode: '',
          workName: '',
          occupation: '',
          workTel: '',
          home: '',
          homeTel: '',
          linkmanName: '',
          linkmanRelation: '',
          linkmanAdd: '',
          linkmanTel: '',
          pactName: '',
          patientState: ''
        },
        bed:{
          bedNo:'',
          bedState:''
        },
        prepay:[],
        cost:[],
        costAll:'',
        prepayAll:'',
        charge:'',//实收金额
        options:[
          {
          index: 1,
          label: '治愈'
          },{
          index: 2,
          label: '好转'
          },{
          index: 3,
          label: '死亡'
          },{
          index: 4,
          label: '治残'
          },{
          index: 5,
          label: '未治愈'
          },{
          index: 6,
          label: '正常分娩'
          },{
          index: 7,
          label: '其他'
        }
        ] //outState的下拉选项
        /*
        如果是查询失败的话，则显示alert，然后返回该界面，重新进行查询

        在提交了表单后，我们需要对inpatient中的bedno对应在bed中相应的bedState进行更改，
        还需要把inpatient中的incardno对应的patient中相应的patientState进行修改
        cost进行增加
        outState进行修改

        最后我们需要显示当前出院病人的patient、inpatient、prepay、cost的所有信息
         */
      }
    },
    methods: {
      leaveInpatientInfoSearch() {
        var _this = this;
        postRequest('/leaveInpatientInfoSearch', {inPatientNo: this.inpatient.inpatientNo}).then(resp => {
          if (resp.status == 200) {
            var Data = resp.data;
            var patientJSON = Data.patientJSON;
            var bedJSON = Data.bedJSON;
            var inpatientJSON = Data.inpatientJSON;

            _this.costAll = Data.costAll;
            _this.prepayAll = Data.prepayAll;
            _this.prepay = Data.prepayJSON;
            _this.cost = Data.costJSON;

            _this.inpatient.patientName = inpatientJSON.patientName;
            _this.inpatient.inpatientNo = inpatientJSON.inpatientNo;
            _this.inpatient.patientName = inpatientJSON.patientName;
            _this.inpatient.idCard = inpatientJSON.idCard;
            _this.inpatient.inDate = inpatientJSON.inDate;
            if (inpatientJSON.inSource == 1) {
              _this.inpatient.inSource = '门诊';
            } else if (inpatientJSON.inSource == 2) {
              _this.inpatient.inSource = '急诊';
            } else if (inpatientJSON.inSource == 3) {
              _this.inpatient.inSource = '转科';
            } else if (inpatientJSON.inSource == 4) {
              _this.inpatient.inSource = '转院';
            }
            if (inpatientJSON.inPath == 1) {
              _this.inpatient.inPath = '本市';
            } else if (inpatientJSON.inPath == 2) {
              _this.inpatient.inPath = '外市';
            }
            _this.inpatient.status = inpatientJSON.status;
            _this.inpatient.deptNo = inpatientJSON.deptNo;
            _this.inpatient.bedNo = inpatientJSON.bedNo;
            _this.inpatient.houseDocNo = inpatientJSON.houseDocNo;
            _this.inpatient.chargeDocNo = inpatientJSON.chargeDocNo;
            _this.inpatient.chiefDocNo = inpatientJSON.chiefDocNo;
            _this.inpatient.dutyNurseNo = inpatientJSON.dutyNurseNo;
            //出院时间是前端直接获取的当前时间并进行显示，最后可以修改为点击“出院“按钮后再获取时间
            _this.inpatient.outDate = moment().format("YYYY-MM-dd hh:mm:ss").toString()
            if(inpatientJSON.outState == 1){
              _this.inpatient.outState = '治愈';
            }else if(inpatientJSON.outState == 2){
              _this.inpatient.outState = '好转';
            }else if(inpatientJSON.outState == 3){
              _this.inpatient.outState = '死亡';
            }else if(inpatientJSON.outState == 4){
              _this.inpatient.outState = '治残';
            }else if(inpatientJSON.outState == 5){
              _this.inpatient.outState = '未治愈';
            }else if(inpatientJSON.outState == 6){
              _this.inpatient.outState = '正常分娩';
            }else if(inpatientJSON.outState == 7){
              _this.inpatient.outState = '其他';
            }

            _this.patient.cardNo = patientJSON.cardNo;
            _this.patient.patientName = patientJSON.patientName;
            if (patientJSON.sexCode == 1) {
              _this.patient.sexCode = '男';
            } else if (patientJSON.sexCode == 2) {
              _this.patient.sexCode = '女';
            }
            _this.patient.idcardNo = patientJSON.idcardNo
            _this.patient.nation = patientJSON.nation;
            _this.patient.birthday = patientJSON.birthday;
            _this.patient.age = patientJSON.age;
            _this.patient.country = patientJSON.country;
            _this.patient.dist = patientJSON.dist;
            _this.patient.birthArea = patientJSON.birthArea;
            if (patientJSON.mariCode == 1) {
              _this.patient.mariCode = '未婚';
            } else if (patientJSON.mariCode == 2) {
              _this.patient.mariCode = '已婚';
            } else if (patientJSON.mariCode == 3) {
              _this.patient.mariCode = '丧偶';
            } else if (patientJSON.mariCode == 4) {
              _this.patient.mariCode = '离婚';
            }
            _this.patient.workName = patientJSON.workName;
            if (patientJSON.occupation == 11) {
              _this.patient.occupation = '国家公务员';
            } else if (patientJSON.occupation == 13) {
              _this.patient.occupation = '专业技术人员';
            } else if (patientJSON.occupation == 17) {
              _this.patient.occupation = '职员';
            } else if (patientJSON.occupation == 21) {
              _this.patient.occupation = '企业管理人员';
            } else if (patientJSON.occupation == 24) {
              _this.patient.occupation = '工人';
            } else if (patientJSON.occupation == 27) {
              _this.patient.occupation = '农民';
            } else if (patientJSON.occupation == 31) {
              _this.patient.occupation = '学生';
            } else if (patientJSON.occupation == 37) {
              _this.patient.occupation = '现役军人';
            } else if (patientJSON.occupation == 51) {
              _this.patient.occupation = '自由职业者';
            } else if (patientJSON.occupation == 54) {
              _this.patient.occupation = '个人经营者';
            } else if (patientJSON.occupation == 70) {
              _this.patient.occupation = '无业人员';
            } else if (patientJSON.occupation == 80) {
              _this.patient.occupation = '退（离）休人员';
            } else if (patientJSON.occupation == 90) {
              _this.patient.occupation = '其他';
            }
            _this.patient.workTel = patientJSON.workTel;
            _this.patient.home = patientJSON.home;
            _this.patient.homeTel = patientJSON.homeTel;
            _this.patient.linkmanName = patientJSON.linkmanName;
            if (patientJSON.linkmanRelation == 1) {
              _this.patient.linkmanRelation = '配偶';
            } else if (patientJSON.linkmanRelation == 2) {
              _this.patient.linkmanRelation = '子';
            } else if (patientJSON.linkmanRelation == 3) {
              _this.patient.linkmanRelation = '女';
            } else if (patientJSON.linkmanRelation == 4) {
              _this.patient.linkmanRelation = '孙子、孙女或外孙子、外孙女';
            } else if (patientJSON.linkmanRelation == 5) {
              _this.patient.linkmanRelation = '父母';
            } else if (patientJSON.linkmanRelation == 6) {
              _this.patient.linkmanRelation = '祖父母或外祖父母';
            } else if (patientJSON.linkmanRelation == 7) {
              _this.patient.linkmanRelation = '兄弟姐妹';
            } else if (patientJSON.linkmanRelation == 8) {
              _this.patient.linkmanRelation = '其他';
            }
            _this.patient.linkmanAdd = patientJSON.linkmanAdd;
            _this.patient.linkmanTel = patientJSON.linkmanTel;
            _this.patient.pactName = patientJSON.pactName;
            if (patientJSON.patientState == 0) {
              _this.patient.patientState = '住院';
            } else if (patientJSON.patientState == 1) {
              _this.patient.patientState = '出院';
            }

            _this.bed.bedNo = bedJSON.bedNo;
            if (bedJSON.bedState == 0) {
              _this.bed.bedState = '未占用';
            } else if (bedJSON.bedState == 1) {
              _this.bed.bedState = '已占用';
            }

          }
        }, resp => {
          if (resp.response.status == 403) {
            console.log("失败了 403")
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          }
        });
      },
      leavePatientRegister(){
        console.log("出院登记");

      }
    },
    computed:{
      //remain 余额
      remain: function () {
        return Number(this.prepayAll) - Number(this.costAll);
      },
      //change 找零
      change:function () {
        return Number(this.remain) + Number(this.charge);
      }
    }
  }
</script>

<style>
  .cate_mana_header {
    background-color: #ececec;
    margin-top: 20px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }

  .cate_mana_main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    margin-top: 20px;
    padding-top: 10px;
  }
  .cate_mana_second{
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    margin-top: 20px;
    padding-top: 10px;
  }
  .el-table th{
    text-align: center;
  }
  .el-form-item__label{
    width: 90px;
  }

</style>
