<template>
  <el-container>
<el-main class="cate_mana_second">
  <el-form :inline="true" :model="patient" :rules="rules" ref="patient" class="demo-form-inline" label-width="120px">
    <el-col span="8" label-position="left">
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

    <el-button type="primary" @click="savePatient('patient')">添加病人</el-button>
    <el-button @click="resetForm('patient')">重置</el-button>
  </el-form>
</el-main>
  </el-container>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  import moment from 'moment'

  export default {
    methods: {
      savePatient(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid) {
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
              })
            }else {
              console.log('error submit!!');
              return false;
            }
        });
      },
      resetForm(formName) {
        if (this.$refs[formName] !== undefined) {
          this.$refs[formName].resetFields();
        }
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
            // prepayCost: '',
            // prepayMethod: '',
            diagnose: '',
            bedNo: '',
            deptNo: '',
            inDate: '',
          },
          rules: {
            patientName: [
              { required: true, message: '请输入患者姓名', trigger: 'blur' },
              //{ min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            sexCode: [
              { required: true, message: '请选择性别', trigger: 'change' }
            ],
            birthArea: [
              { required: true, message: '请输入出生地', trigger: 'blur' }
            ],
            age: [
              { required: true, message: '请选择年龄', trigger: 'blur' }
            ],
            occupation: [
              { required: true, message: '请输入职业', trigger: 'blur' }
            ],
            homeTel: [
              { required: true, message: '请输入家庭电话', trigger: 'blur' }
            ],
            linkmanTel: [
              { required: true, message: '请输入联系人电话', trigger: 'blur' }
            ],
            deptNo: [
              { required: true, message: '请输入科室编号', trigger: 'blur' }
            ],
            inSource: [
              { required: true, message: '请选择入院来源', trigger: 'change' }
            ],
            patientState: [
              { required: true, message: '请输入病人状态', trigger: 'change' }
            ],
            nation: [
              { required: true, message: '请输入民族', trigger: 'blur' }
            ],
            country: [
              { required: true, message: '请输入国籍', trigger: 'blur' }
            ],
            mariCode: [
              { required: true, message: '请选择婚姻状态', trigger: 'change' }
            ],
            workTel: [
              { required: true, message: '请输入单位电话', trigger: 'blur' }
            ],
           linkmanName: [
              { required: true, message: '请输入联系人姓名', trigger: 'blur' }
            ],
            linkmanRelation: [
              { required: true, message: '请选择与患者关系', trigger: 'change' }
            ],
            bedNo: [
              { required: true, message: '请输入病床号', trigger: 'blur' }
            ],
            houseDocNo: [
              { required: true, message: '请输入收住医师号', trigger: 'blur' }
            ],
            idCard: [
              { required: true, message: '请输入身份证号', trigger: 'blur' }
            ],
            birthday: [
              { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
            ],
           dist: [
              { required: true, message: '请输入籍贯', trigger: 'blur' }
            ],
            workName: [
              { required: true, message: '请输入单位名称', trigger: 'blur' }
            ],
            home: [
              { required: true, message: '请输入家庭住址', trigger: 'blur' }
            ],
            linkmanAdd: [
              { required: true, message: '请输入联系人住址', trigger: 'blur' }
            ],
            pactName: [
              { required: true, message: '请输入合同单位名称', trigger: 'blur' }
            ],
            inPath: [
              { required: true, message: '请选择入院途径', trigger: 'change' }
            ],
            diagnose: [
              { required: true, message: '请输入门诊诊断', trigger: 'blur' }
            ]
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
