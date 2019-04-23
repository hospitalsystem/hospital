<template>
  <el-container>
    <el-header class="cate_mana_header">
      <el-input
        placeholder="请输入住院号进行出院登记"
        v-model="inpatient.inpatientNo" style="width: 200px;">
      </el-input>
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="getLeavePatientInfo">查询</el-button>
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
          <el-input readonly="true" v-model="inpatient.inpatientNo" placeholder="病历号"></el-input>
        </el-form-item>
        <el-form-item label="入院日期:">
          <el-input readonly="true" v-model="inpatient.inDate" placeholder="入院日期"></el-input>
        </el-form-item>
        <el-form-item label="病床号:">
          <el-input readonly="true" v-model="inpatient.bedNo" placeholder="病床号"></el-input>
        </el-form-item>
        <el-form-item label="性别:">
          <el-input readonly="true" v-model="patient.sex_code" placeholder="性别"></el-input>
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
        <el-form-item label="收取金额:" class="charge">
          <el-input v-model="charge" placeholder="收取金额"></el-input>
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
        <el-form-item class="button">
          <el-button type="primary" @click="onSubmit">出院</el-button>
        </el-form-item>
      </el-form>
    </el-main>

  </el-container>

</template>

<script>
  import moment from "moment-js"
  import ElFormItem from "../../node_modules/element-ui/packages/form/src/form-item.vue";
  import ElInput from "../../node_modules/element-ui/packages/input/src/input.vue";

  export default {
    mounted:{

    },
    components: {
      ElInput,
      ElFormItem},
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
          outDate:moment().format("YYYY-MM-dd hh:mm:ss").toString(),
          outState:''
        },
        patient:{
          patient_name: '',
          sex_code: '',
          idcard_no: '',
          nation: '',
          birthday: '',
          age: '',
          country: '',
          dist: '',
          birth_area: '',
          mari_code: '',
          work_name: '',
          occupation: '',
          work_tel: '',
          home: '',
          home_tel: '',
          linkman_name: '',
          linkman_relation: '',
          linkman_add: '',
          linkman_tel: '',
          pact_name: '',
          patient_state: ''
        },
        cost:{
          happenNo:'',
          inpatientNo:'',
          costName:'',
          costAmount:''
        },
        prepay:{
          happenNo:'',
          patientName:'',
          patientCost:'',
          recipeNo:'',
          perpayState:'',
          issettlement:'',
          staffNo:'',
          operDate:'',
          prepayMethod:'',
          inpatientNo:''
        },
        bed:{
          bedNo:'',
          bedState:''
        },
        costAll:'',
        prepayAll:'',
        charge:'',//实收金额
        options:[{
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

        ]
        /*
        我们的逻辑就是最开始只显示一个<input>标签和一个button进行点击查询，
        然后如果是查询失败的话，则显示alert，然后返回该界面，重新进行查询
        如果是查询成功了，就在界面上显示出来以下不可修改的信息：
        inpatient中的inpatientNo,inDate,patientName,deptNo,bedNo,outDate（获取当前时间）
        patient中的sexCode
        AllCost：cost中的costAmount总和
        AllPrepay: prepay中的prepayCost总和
        balance：余额：如果余额小于0，需要提示补缴，如果大于0，则返还预交金

        我们还需要显示如下信息，是使用select来进行选择的：
        inpatient中的outState

        在提交了表单后，我们需要对inpatient中的bedno对应在bed中相应的bedState进行更改，
        还需要把inpatient中的incardno对应的patient中相应的patientState进行修改

         最后我们需要显示当前出院病人的patient、inpatient、prepay、cost的所有信息
         */

        /*
        第一步 根据inpatientNo查询inpatient的实体类，
        成功返回以后，根据idcardNo进行查询patient，根据inpatientno查询cost的集合，根据inpatientno查询prepay的集合

        第二步 在input的输入框里面显示上面需要的信息，只是有的不可以修改。

        第三步 点击提交以后就乱七八糟再发一次

        第四步 返回一个页面
         */


      }
    },
    methods:{
      getLeavePatientInfo(){
        var _this = this;
        postRequest('/getInpatient', {inPatientNo: this.inpatient.inpatientNo}).then(resp=> {
          if (resp.status == 200) {
            console.log("resp:"+JSON.stringify(resp));
            console.log("resp.data:"+JSON.stringify(resp.data));
            console.log("resp.data.json:"+JSON.stringify(resp.data.json));
            console.log("resp.data.data"+JSON.stringify(resp.data.data));
            var data = resp.data;
            var json = data.json;

            _this.inpatient.patientName = json.inpatient.patientName;
            _this.inpatient.inpatientNo = json.inpatient.inpatientNo;
            _this.inpatient.patientName = json.inpatient.patientName;
            _this.inpatient.idCard = json.inpatient.idCard;
            _this.inpatient.inDate = json.inpatient.inDate;
            if(json.inpatient.inSource === 1){
              _this.inpatient.inSource = '门诊';
            }else if(json.inpatient.inSource === 2){
              _this.inpatient.inSource = '急诊';
            }else if(json.inpatient.inSource === 3){
              _this.inpatient.inSource = '转科';
            }else if(json.inpatient.inSource === 4){
              _this.inpatient.inSource = '转院';
            }
            if(json.inpatient.inPath === 1){
              _this.inpatient.inPath = '本市';
            }else if(json.inpatient.inPath === 2){
              _this.inpatient.inPath = '外市';
            }
            _this.inpatient.status = json.inpatient.status;
            _this.inpatient.deptNo = json.inpatient.deptNo;
            _this.inpatient.bedNo = json.inpatient.bedNo;
            _this.inpatient.houseDocNo = json.inpatient.houseDocNo;
            _this.inpatient.chargeDocNo = json.inpatient.chargeDocNo;
            _this.inpatient.chiefDocNo = json.inpatient.chiefDocNo;
            _this.inpatient.dutyNurseNo = json.inpatient.dutyNurseNo;

            //出院时间
            //_this.inpatient.outDate= json.inpatient.outDate;
            _this.inpatient.outDate = Date().format("yyyy-MM-dd HH:mm:ss")

//            if(json.inpatient.outState === 1){
//              _this.inpatient.outState = '治愈';
//            }else if(json.inpatient.outState === 2){
//              _this.inpatient.outState = '好转';
//            }else if(json.inpatient.outState === 3){
//              _this.inpatient.outState = '死亡';
//            }else if(json.inpatient.outState === 4){
//              _this.inpatient.outState = '治残';
//            }else if(json.inpatient.outState === 5){
//              _this.inpatient.outState = '未治愈';
//            }else if(json.inpatient.outState === 6){
//              _this.inpatient.outState = '正常分娩';
//            }else if(json.inpatient.outState === 7){
//              _this.inpatient.outState = '其他';
//            }
            _this.inpatient.outState = json.inpatient.outState;

            _this.patient.patient_name = json.patient.patient_name;
            if(json.patient.sex_code === 1){
              _this.patient.sex_code = '男';
            }else if(json.patient.sex_code === 2){
              _this.patient.sex_code = '女';
            }
            _this.patient.idcard_no = json.patient.idcard_no
            _this.patient.nation = json.patient.nation;
            _this.patient.birthday = json.patient.birthday;
            _this.patient.age = json.patient.age;
            _this.patient.country = json.patient.country;
            _this.patient.dist = json.patient.dist;
            _this.patient.birth_area = json.patient.birth_area;
            if(json.patient.mari_code === 1){
              _this.patient.mari_code = '未婚';
            }else if(json.patient.mari_code === 2){
              _this.patient.mari_code = '已婚';
            }else if(json.patient.mari_code === 3){
              _this.patient.mari_code = '丧偶';
            }else if(json.patient.mari_code === 4){
              _this.patient.mari_code = '离婚';
            }
            _this.patient.work_name = json.patient.work_name;
            if(json.patient.occupation === 11){
              _this.patient.occupation = '国家公务员';
            }else if(json.patient.occupation === 13){
              _this.patient.occupation = '专业技术人员';
            }else if(json.patient.occupation === 17){
              _this.patient.occupation = '职员';
            }else if(json.patient.occupation === 21){
              _this.patient.occupation = '企业管理人员';
            }else if(json.patient.occupation === 24){
              _this.patient.occupation = '工人';
            }else if(json.patient.occupation === 27){
              _this.patient.occupation = '农民';
            }else if(json.patient.occupation === 31){
              _this.patient.occupation = '学生';
            }else if(json.patient.occupation === 37){
              _this.patient.occupation = '现役军人';
            }else if(json.patient.occupation === 51){
              _this.patient.occupation = '自由职业者';
            }else if(json.patient.occupation === 54){
              _this.patient.occupation = '个人经营者';
            }else if(json.patient.occupation === 70){
              _this.patient.occupation = '无业人员';
            }else if(json.patient.occupation === 80){
              _this.patient.occupation = '退（离）休人员';
            }else if(json.patient.occupation === 90){
              _this.patient.occupation = '其他';
            }

            _this.patient.work_tel = json.patient.work_tel;
            _this.patient.home = json.patient.home;
            _this.patient.home_tel = json.patient.home_tel;
            _this.patient.linkman_name = json.patient.linkman_name;
            if(json.patient.linkman_relation === 1){
              _this.patient.linkman_relation = '配偶';
            }else if(json.patient.linkman_relation === 2){
              _this.patient.linkman_relation = '子';
            }else if(json.patient.linkman_relation === 3){
              _this.patient.linkman_relation = '女';
            }else if(json.patient.linkman_relation === 4){
              _this.patient.linkman_relation = '孙子、孙女或外孙子、外孙女';
            }else if(json.patient.linkman_relation === 5){
              _this.patient.linkman_relation = '父母';
            }else if(json.patient.linkman_relation === 6){
              _this.patient.linkman_relation = '祖父母或外祖父母';
            }else if(json.patient.linkman_relation === 7){
              _this.patient.linkman_relation = '兄弟姐妹';
            }else if(json.patient.linkman_relation === 8){
              _this.patient.linkman_relation = '其他';
            }
            _this.patient.linkman_add = json.patient.linkman_add;
            _this.patient.linkman_tel = json.patient.linkman_tel;
            _this.patient.pact_name = json.patient.pact_name;
            if(json.patient.patient_state === 0){
              _this.patient.patient_state = '住院';
            }else if(json.patient.patient_state === 1){
              _this.patient.patient_state = '出院';
            }

            _this.cost.happenNo = json.cost.happenNo;
            _this.cost.inpatientNo = json.cost.inpatientNo;
            _this.cost.costName = json.cost.costName;
            _this.cost.costAmount = json.cost.costAmount;

            _this.prepay.happenNo = json.prepay.happenNo;
            _this.prepay.patientName = json.prepay.patientName;
            _this.prepay.patientCost = json.prepay.patientCost;
            _this.prepay.recipeNo = json.prepay.recipeNo;
            if(json.prepay.perpayState === 1){
              _this.prepay.perpayState = '收取';
            }else if(json.prepay.perpayState === 2){
              _this.prepay.perpayState = '返还';
            }else if(json.prepay.perpayState === 3){
              _this.prepay.perpayState = '补打';
            }
            if(json.prepay.issettlement === 0){
              _this.prepay.issettlement = '未结算';
            }else if(json.prepay.issettlement === 1){
              _this.prepay.issettlement = '结算';
            }
            _this.prepay.staffNo = json.prepay.staffNo;
            _this.prepay.operDate = json.prepay.operDate;
            if(json.prepay.prepayMethod === 1){
              _this.prepay.prepayMethod = '现金支付';
            }else if(json.prepay.prepayMethod === 2){
              _this.prepay.prepayMethod = '银行卡支付';
            }else if(json.prepay.prepayMethod === 3){
              _this.prepay.prepayMethod = '支票支付';
            }
            _this.prepay.inpatientNo = json.prepay.inpatientNo;


            _this.bed.bedNo = json.bed.bedNo;
            if(json.bed.bedState === 0){
              _this.bed.bedState = '未占用';
            }else if(json.bed.bedState === 1){
              _this.bed.bedState = '已占用';
            }


            _this.tableData=resp.data.data;
          }
        }, resp=> {
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          }
        });
      },


    },
    computed:{
      //remain 余额
      remain: function () {
        return this.prepayAll - this.costAll;
      },
      change:function () {
        return this.remain()+this.charge;
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
