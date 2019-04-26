<template>
  <el-container>
<el-main class="cate_mana_second">
  <el-form :inline="true" :model="form" class="demo-form-inline">
    <el-form-item label="患者姓名：">
    <el-input v-model="patient.patientName" placeholder="patient_name" ></el-input>
    </el-form-item>
    <el-form-item label="患者状态" >
      <el-select v-model="patient.patientState" placeholder="请选择患者状态" >
        <el-option
        v-for="item in options5"
        :key="item.index"
        :label="item.label"
        :value="item.index">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="身份证号：">
    <el-input v-model="patient.idCard" placeholder="请输入病人姓名" ></el-input>
    </el-form-item>
    <el-form-item label="性别：">
      <el-select v-model="patient.sexCode" placeholder="sex_code" >
        <el-option
          v-for="item in options4"
          :key="item.index"
          :label="item.label"
          :value="item.index">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="民族：">
      <el-input v-model="patient.nation" placeholder="请输入病人姓名"></el-input>
    </el-form-item>
    <el-form-item label="出生年月：" required>
      <el-form-item >
        <el-date-picker  placeholder="选择日期" v-model="patient.birthday" style="width: 70%;"></el-date-picker>
      </el-form-item>
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
      <el-select v-model="patient.mariCode" placeholder="请选择" >
          <el-option
        v-for="item in options1"
        :key="item.index"
        :label="item.label"
        :value="item.index">
      </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="工作单位：">
      <el-input v-model="patient.workName" placeholder="请输入病人姓名" ></el-input>
    </el-form-item>
    <el-form-item label="职位：">
    <el-select v-model="patient.occupation" placeholder="请选择" >
      <el-option
        v-for="item in options2"
        :key="item.index"
        :label="item.label"
        :value="item.index">
      </el-option>
    </el-select>
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
    <el-select v-model="patient.linkmanRelation" placeholder="请输入病人姓名" >
      <el-option
        v-for="item in options3"
        :key="item.index"
        :label="item.label"
        :value="item.index">
      </el-option>
    </el-select>
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
    <!--入院来源、入院途径、入院情况、收住医师、门诊诊断、出生地、预收金额、支付方式。-->
    <el-form-item label="科室编号：">
      <el-input v-model="patient.deptNo" placeholder="请输入病人姓名" ></el-input>
    </el-form-item>
    <el-form-item label="病床编号：">
      <el-input v-model="patient.bedNo" placeholder="请输入病人姓名" ></el-input>
    </el-form-item>
    <el-form-item label="入院途径：">
      <el-select v-model="patient.inPath">
        <el-option label="本市" value="1"></el-option>
        <el-option label="外市" value="2"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="入院来源：">
      <el-select v-model="patient.inSource">
        <el-option label="门诊" value="1"></el-option>
        <el-option label="急诊" value="2"></el-option>
        <el-option label="转科" value="3"></el-option>
        <el-option label="转院" value="4"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="收住医师：">
      <el-input v-model="patient.houseDocNo" placeholder="请输入病人姓名" ></el-input>
    </el-form-item>
    <el-form-item label="门诊诊断：">
      <el-input v-model="patient.diagnose" placeholder="请输入病人姓名" ></el-input>
    </el-form-item>
    <el-form-item label="预收金额：">
      <el-input v-model="patient.prepayCost" placeholder="请输入病人姓名" ></el-input>
    </el-form-item>
    <el-form-item label="支付方式：">
      <el-select v-model="patient.inSource">
        <el-option label="现金支付" value="1"></el-option>
        <el-option label="银行卡支付" value="2"></el-option>
        <el-option label="支票支付" value="3"></el-option>
      </el-select>
    </el-form-item>
    <el-button type="primary" @click="savePatient">添加病人</el-button>
    <el-button @click="resetForm('formName')">重置</el-button>
  </el-form>
</el-main>
  </el-container>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {getRequest} from '../utils/api'

  export default {
    methods: {
      savePatient() {
        var _this = this;
        var data = {
          patientName: _this.patient.patientName,
          sexCode: _this.patient.sexCode,
          idCard: _this.patient.idCard,
          nation: _this.patient.nation,
          birthday: _this.patient.birthday,
          age: _this.patient.age,
          country: _this.patient.country,
          dist: _this.patient.dist,
          birthArea: _this.patient.birthArea,
          mariCode: _this.patient.mariCode,
          workName: _this.patient.workName,
          occupation: _this.patient.occupation,
          workTel: _this.patient.workTel,
          home: _this.patient.home,
          homeTel: _this.patient.homeTel,
          linkmanName: _this.patient.linkmanName,
          linkmanRelation: _this.patient.linkmanRelation,
          linkmanAdd: _this.patient.linkmanAdd,
          linkmanTel: _this.patient.linkmanTel,
          pactName: _this.patient.pactName,
          inSource: _this.patient.inSource,
          inPath: _this.patient.inPath,
          houseDocNo: _this.patient.houseDocNo,
          diagnose: _this.patient.diagnose,
          prepayCost: _this.patient.prepayCost,
          prepayMethod: _this.patient.prepayMethod,
          patientState: _this.patient.patientState,
          deptNo: _this.patient.deptNo,
          bedNo: _this.patient.bedNo,
          inDate: _this.patient.inDate,

        };
        //console.log("data:"+JSON.stringify(data));
        //data=JSON.parse(JSON.stringify(data));
        data = JSON.stringify(data);
        console.log("data:" + data);
        postRequest("/patientAdd", data).then(resp => {
          if (resp.status == 200 && resp.data.status == 'success') {
            console.log("resp:" + JSON.stringify(resp));
            alert("添加成功");
          }

        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    },
      data() {
        return {
          form:'',
          patient: {
            cardNo: '',
            patientName: '',
            patientState: '',
            sexCode: '',
            idCard: '',
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
            inSource: '',
            inPath: '',
            houseDocNo: '',
            prepayCost: '',
            prepayMethod: '',
            diagnose: '',
            bedNo: '',
            deptNo: '',
            inDate: '',
          },
          options1: [{
            index: 1,
            label: '未婚',
          }, {
            index: 2,
            label: '已婚',
          }, {
            index: 3,
            label: '丧偶',
          }, {
            index: 4,
            label: '离婚',
          }],

          options2: [{
            index: 11,
            label: '国家公务人员',
          }, {
            index: 13,
            label: '专业技术人员',
          }, {
            index: 17,
            label: '职员',
          }, {
            index: 21,
            label: '企业管理人员',
          },
            {
              index: 24,
              label: '工人',
            }, {
              index: 27,
              label: '农民',
            }, {
              index: 31,
              label: '学生',
            }, {
              index: 37,
              label: '现役军人',
            },
            {
              index: 11,
              label: '国家公务人员',
            }, {
              index: 51,
              label: '自由职业者',
            }, {
              index: 54,
              label: '个人经营者',
            }, {
              index: 70,
              label: '无业人员',
            },
            {
              index: 80,
              label: '退（离）休人员',
            }, {
              index: 90,
              label: '其他',
            }],
          options3: [{
            index: 1,
            label: '配偶',
          }, {
            index: 2,
            label: '子',
          }, {
            index: 3,
            label: '女',
          }, {
            index: 4,
            label: '孙子、孙女或外孙子、外孙女',
          },
            {
              index: 5,
              label: '父母',
            }, {
              index: 6,
              label: '祖父母或外祖父母',
            }, {
              index: 7,
              label: '兄弟姐妹',
            }, {
              index: 8,
              label: '其他',
            }],
          options4: [{
            index: 1,
            label: '男',
          }, {
            index: 2,
            label: '女',
          }],
          options5: [{
            index: 0,
            label: '出院',
          }, {
            index: 1,
            label: '住院',
          }]
        }
      }

  }
</script>
<style>
  .cate_mana_second{
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    margin-top: 20px;
    padding-top: 10px;
  }

</style>
