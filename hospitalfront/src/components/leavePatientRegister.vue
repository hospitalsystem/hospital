<template>
  <el-container>
    <!--出院成功以后会隐藏以下界面-->
    <el-header class="cate_mana_header" v-show="isShow">
      <el-input
        placeholder="请输入住院号进行出院登记"
        v-model="inpatient.inpatientNo" style="width: 200px;" @keyup.enter.native="leaveInpatientInfoSearch">
      </el-input>
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="leaveInpatientInfoSearch" >查询</el-button>
    </el-header>

    <el-main class="cate_mana_second" v-show="isShow">
      <el-form :inline="true" :model="form"  class="demo-form-inline" align="left" labelWidth="90px">
        <el-form-item label="患者姓名:">
          <el-input readonly="true" v-model="inpatient.patientName" placeholder="患者姓名"></el-input>
        </el-form-item>
        <el-form-item label="病历号:">
          <el-input readonly="true" v-model="patient.cardNo" placeholder="病历号"></el-input>
        </el-form-item>
        <el-form-item label="性别:">
          <el-input readonly="true" v-model="patient.sexCode" placeholder="性别"></el-input>
        </el-form-item>
        <el-form-item label="住院科室:">
          <el-input readonly="true" v-model="inpatient.deptNo" placeholder="住院科室"></el-input>
        </el-form-item>
        <el-form-item label="病床号:">
          <el-input readonly="true" v-model="inpatient.bedNo" placeholder="病床号"></el-input>
        </el-form-item>
        <el-form-item label="入院日期:">
          <el-input readonly="true" v-model="inpatient.inDate" placeholder="入院日期"></el-input>
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
        <el-form-item label="收据号:" class="recipeNo" v-show="remain < 0">
          <el-input v-model="forms.recipeNo" placeholder="收据号"></el-input>
        </el-form-item>
        <el-form-item label="找零:" class="change" v-show="change > 0">
          <el-input readonly="true" v-model="change" placeholder="找零"></el-input>
        </el-form-item>
        <el-form-item label="操作员:" class="staffNo" v-show="remain < 0">
          <el-input v-model="forms.staffNo" placeholder="操作员"></el-input>
        </el-form-item>
        <el-form-item label="预交金支付方式:" class="prepayMethod" v-show="remain < 0">
          <el-select v-model="forms.prepayMethod" placeholder="请选择" style="width:206.4px">
            <el-option
              v-for="item in prepayMethodOptions"
              :key="item.index"
              :label="item.label"
              :value="item.index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出院状态:" class="outState">
          <el-select v-model="inpatient.outState" placeholder="请选择" style="width:206.4px">
            <el-option
              v-for="item in outStateOptions"
              :key="item.index"
              :label="item.label"
              :value="item.index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item  label="出院日期:" class="outDate">
          <el-input readonly="true" v-model="inpatient.outDate" placeholder="出院日期"></el-input>
        </el-form-item>
        <el-form-item class="button" >
          <el-button type="primary" @click="leavePatientRegister" v-show="bed.bedState != ''">出院</el-button>
        </el-form-item>
      </el-form>
    </el-main>

    <el-main class="cate_mana_main" v-show="isShow">
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

    <el-main class="cate_mana_main" v-show="isShow">
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


    <!--出院成功以后会展示以下界面-->
    <el-header class="cate_mana_header" v-show="!isShow">
      <p>住院详情如下</p>
    </el-header>

    <el-main class="cate_mana_second" v-show="!isShow">
      <el-form :inline="true" :model="form" class="demo-form-inline" align="left" labelWidth="110px">
        <el-form-item label="住院编号:">
          <el-input readonly="true" v-model="inpatient.inpatientNo" ></el-input>
        </el-form-item>
        <el-form-item label="病人姓名:">
          <el-input readonly="true" v-model="inpatient.patientName" ></el-input>
        </el-form-item>
        <el-form-item label="身份证号：">
          <el-input readonly="true" v-model="inpatient.idCard"></el-input>
        </el-form-item>
        <el-form-item label="入院时间：">
          <el-input readonly="true" v-model="inpatient.inDate"></el-input>
        </el-form-item>
        <el-form-item label="入院来源：">
          <el-input readonly="true" v-model="inpatient.inSource"></el-input>
        </el-form-item>
        <el-form-item label="入院途径：">
          <el-input readonly="true" v-model="inpatient.inPath"></el-input>
        </el-form-item>
        <el-form-item label="入院情况：">
          <el-input readonly="true" v-model="inpatient.status"></el-input>
        </el-form-item>
        <el-form-item label="科室编号：">
          <el-input readonly="true" v-model="inpatient.deptNo"></el-input>
        </el-form-item>
        <el-form-item label="病床编号：">
          <el-input readonly="true" v-model="inpatient.bedNo"></el-input>
        </el-form-item>
        <el-form-item label="住院医师编号：">
          <el-input readonly="true" v-model="inpatient.houseDocNo"></el-input>
        </el-form-item>
        <el-form-item label="主治医师编号：">
          <el-input readonly="true" v-model="inpatient.chargeDocNo"></el-input>
        </el-form-item>
        <el-form-item label="主任医师编号：">
          <el-input readonly="true" v-model="inpatient.chiefDocNo"></el-input>
        </el-form-item>
        <el-form-item label="责任护士编号：">
          <el-input readonly="true" v-model="inpatient.dutyNurseNo"></el-input>
        </el-form-item>
        <el-form-item label="出院日期：">
          <el-input readonly="true" v-model="inpatient.outDate"></el-input>
        </el-form-item>
        <el-form-item label="出院情况：">
          <el-input readonly="true" v-model="inpatient.outState"></el-input>
        </el-form-item>
      </el-form>
    </el-main>

    <el-main class="cate_mana_second" v-show="!isShow">
      <el-form :inline="true" :model="form" class="demo-form-inline" align="left" labelWidth="110px">
        <el-form-item label="病历编号：">
          <el-input readonly="true" v-model="patient.cardNo"></el-input>
        </el-form-item>
        <el-form-item label="病人姓名：">
          <el-input readonly="true" v-model="patient.patientName"></el-input>
        </el-form-item>
        <el-form-item label="性别：">
          <el-input readonly="true" v-model="patient.sexCode"></el-input>
        </el-form-item>
        <el-form-item label="身份证号：">
          <el-input readonly="true" v-model="patient.idcard"></el-input>
        </el-form-item>
        <el-form-item label="民族：">
          <el-input readonly="true" v-model="patient.nation"></el-input>
        </el-form-item>
        <el-form-item label="出生年月：">
          <el-input readonly="true" v-model="patient.birthday"></el-input>
        </el-form-item>
        <el-form-item label="年龄：">
          <el-input readonly="true" v-model="patient.age"></el-input>
        </el-form-item>
        <el-form-item label="国籍：">
          <el-input readonly="true" v-model="patient.country"></el-input>
        </el-form-item>
        <el-form-item label="籍贯：">
          <el-input readonly="true" v-model="patient.dist"></el-input>
        </el-form-item>
        <el-form-item label="出生地：">
          <el-input readonly="true" v-model="patient.birthArea"></el-input>
        </el-form-item>
        <el-form-item label="婚姻状况：">
          <el-input readonly="true" v-model="patient.mariCode"></el-input>
        </el-form-item>
        <el-form-item label="工作单位：">
          <el-input readonly="true" v-model="patient.workName"></el-input>
        </el-form-item>
        <el-form-item label="职业：">
          <el-input readonly="true" v-model="patient.occupation"></el-input>
        </el-form-item>
        <el-form-item label="单位电话：">
          <el-input readonly="true" v-model="patient.workTel"></el-input>
        </el-form-item>
        <el-form-item label="家庭住址：">
          <el-input readonly="true" v-model="patient.home"></el-input>
        </el-form-item>
        <el-form-item label="家庭电话：">
          <el-input readonly="true" v-model="patient.homeTel"></el-input>
        </el-form-item>
        <el-form-item label="联系人姓名：">
          <el-input readonly="true" v-model="patient.linkmanName"></el-input>
        </el-form-item>
        <el-form-item label="与患者关系：">
          <el-input readonly="true" v-model="patient.linkmanRelation"></el-input>
        </el-form-item>
        <el-form-item label="联系人地址：">
          <el-input readonly="true" v-model="patient.linkmanAdd"></el-input>
        </el-form-item>
        <el-form-item label="联系人电话：">
          <el-input readonly="true" v-model="patient.linkmanTel"></el-input>
        </el-form-item>
        <el-form-item label="合同单位名称：">
          <el-input readonly="true" v-model="patient.pactName"></el-input>
        </el-form-item>
        <el-form-item label="病人状态：">
          <el-input readonly="true" v-model="this.patient.patientState"></el-input>
        </el-form-item>
      </el-form>
    </el-main>

    <el-main class="cate_mana_second" v-show="!isShow">
      <el-form :inline="true" :model="form" class="demo-form-inline"  align="left" labelWidth="110px">
        <el-form-item label="预交金总额:">
          <el-input readonly="true" v-model="prepayAll" ></el-input>
        </el-form-item>
        <el-form-item label="消费总额:">
          <el-input readonly="true" v-model="costAll"></el-input>
        </el-form-item>
      </el-form>
    </el-main>

    <el-main class="cate_mana_main" v-show="!isShow">
      <p>预交金详情</p>
      <el-table
        :data="this.prepay"
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

    <el-main class="cate_mana_main" v-show="!isShow">
      <p>消费详情</p>
      <el-table
        :data="this.cost"
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
      </el-table>/
    </el-main>
  </el-container>

</template>

<script>
  import moment from "moment"
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  import ElRow from "element-ui/packages/row/src/row";

  export default {

    components: {ElRow},
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
          idcard: '',
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
          patientState: '',
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
        prepayMethodOptions:[
          {
            index: 1,
            label: '现金支付'
          },{
            index: 2,
            label: '银行卡支付'
          },{
            index: 3,
            label: '支票支付'
          }
        ],
        outStateOptions:[
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
        ], //outState的下拉选项
        forms: {
          staffNo:'',
          recipeNo:'',
          prepayMethod:'',
        },
        isShow: true,
        rules: {
          inpatientNo: [
            { required: true, message: '请输入住院号', trigger: 'blur' },
            //{ min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],

        }
      }
    },
    methods: {
      leaveInpatientInfoSearch() {
        if(this.inpatient.inpatientNo == ''){
          alert('住院号不能为空')
          return
        }
        var _this = this;
        postRequest('/leaveInpatientInfoSearch', {inPatientNo: this.inpatient.inpatientNo}).then(resp => {
          if (resp.status == 200) {
            var Data = resp.data;
            var patientJSON = Data.patientJSON;
            var bedJSON = Data.bedJSON;
            var inpatientJSON = Data.inpatientJSON;


            if (patientJSON.patientState == 0) {
              //只有住院状态的病人才能出院
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
              _this.inpatient.outDate = moment().format("YYYY-MM-DD HH:mm:ss").toString()
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
              console.log("inpatientJSON中的outState"+inpatientJSON.outState);
              console.log("inpatient中的outState"+_this.inpatient.outState);

              _this.patient.cardNo = patientJSON.cardNo;
              _this.patient.patientName = patientJSON.patientName;
              if (patientJSON.sexCode == 1) {
                _this.patient.sexCode = '男';
              } else if (patientJSON.sexCode == 2) {
                _this.patient.sexCode = '女';
              }
              _this.patient.idcard = patientJSON.idCard
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

            } else if (patientJSON.patientState == 1) {
              alert('该病人已经出院，无法进行出院操作');
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

      updateNewCost(){
        postRequest('/updateNewCost',{inPatientNo: this.inpatient.inpatientNo}).then(resp => {
          if(resp.status == 200){
            var Data = resp.data;
            this.cost = [];
            this.cost = Data.costJSON;
          }
        });
      },

      updateNewPrepay(){
        postRequest('/updateNewPrepay',{inPatientNo: this.inpatient.inpatientNo}).then(resp => {
          if(resp.status == 200){
            var Data = resp.data;
            this.prepay = [];
            this.prepay = Data.prepayJSON;
          }
        });
      },

      leavePatientRegister(){
        var _this = this;
        if(this.change >= 0){
          postRequest('/leaveInpatientInfoSearch', {inPatientNo: _this.inpatient.inpatientNo}).then(resp => {
            if (resp.status == 200) {
              //当初写的时候，上面的那个请求有意义吗？用来干嘛的....
              var inpatientNoTemp = _this.inpatient.inpatientNo;
              console.log("inpatient:::"+_this.inpatient.inpatientNo);
              var bedNoTemp = _this.bed.bedNo;
              var bedStateTemp = 0;
              var costAmountTemp = _this.remain; //把余额作为开销，名称为返钱，打给用户，用来把全部预交金均消费掉
              var costNameTemp = '预交金返还';
              var patientStateTemp = '1';
              var outStateTemp = _this.inpatient.outState;
              if(this.inpatient.outState == '治愈'){
                outStateTemp = 1;
              }else if(this.inpatient.outState == '好转'){
                outStateTemp = 2;
              }else if(this.inpatient.outState == '死亡'){
                outStateTemp = 3;
              }else if(this.inpatient.outState == '治残'){
                outStateTemp = 4;
              }else if(this.inpatient.outState == '未治愈'){
                outStateTemp = 5;
              }else if(this.inpatient.outState == '正常分娩'){
                outStateTemp = 6;
              }else if(this.inpatient.outState == '其他'){
                outStateTemp = 7;
              }
              console.log("inpatient.outState::"+_this.inpatient.outState);
              console.log("outStateTemp:"+outStateTemp);

              var patientIdcardTemp = _this.patient.idcard;
              var remainTemp = _this.remain;
              var recipeNoTemp = _this.forms.recipeNo;
              var prepayMethodTemp = _this.forms.prepayMethod;
              var staffNoTemp = _this.forms.staffNo;
              var setOperDateTemp = moment().format("YYYY-MM-DD HH:mm:ss");


              var data = {
                staffNoTemp: staffNoTemp,
                prepayMethodTemp: prepayMethodTemp,
                inpatientNoTemp: inpatientNoTemp,
                bedNoTemp: bedNoTemp,
                bedStateTemp: bedStateTemp,
                costAmountTemp: costAmountTemp,
                costNameTemp: costNameTemp,
                patientStateTemp: patientStateTemp,
                outStateTemp: outStateTemp,
                patientIdcardTemp: patientIdcardTemp,
                remainTemp: remainTemp,
                recipeNoTemp: recipeNoTemp,
                setOperDateTemp: setOperDateTemp
              }

              //需要对后台的数据进行更新，所以要发回去
              postRequest("/leavePatientRegister", data).then(resp => {
                if (resp.status == 200) {
                  alert('出院登记成功');
                  this.isShow = false;
                  this.patient.patientState = '出院';

                  if(this.inpatient.outState == 1){
                    this.inpatient.outState = '治愈'
                  }else if(this.inpatient.outState == 2){
                    this.inpatient.outState = '好转'
                  }else if(this.inpatient.outState == 3){
                    this.inpatient.outState = '死亡'
                  }else if(this.inpatient.outState == 4){
                    this.inpatient.outState = '治残'
                  }else if(this.inpatient.outState == 5){
                    this.inpatient.outState = '未治愈'
                  }else if(this.inpatient.outState == 6){
                    this.inpatient.outState = '正常分娩'
                  }else if(this.inpatient.outState == 7){
                    this.inpatient.outState = '其他'
                  }
                  this.updateNewCost(); //把后台最新的cost取到
                  this.updateNewPrepay();//把后台最新的prepay取到
                }
              })
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
        }
        else{
          alert('必须缴纳足够金额的住院费用才允许出院');
        }
      },
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
  .demo-form-inline{
    /*display: flex;*/
    /*justify-content: space-around;*/

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
