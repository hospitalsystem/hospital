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
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="2"></el-option>
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
      <el-form-item label="家庭电话：">
        <el-input v-model="patient.homeTel" ></el-input>
      </el-form-item>
      <el-form-item label="联系人电话：" prop="linkmanTel">
        <el-input v-model="patient.linkmanTel"  ></el-input>
      </el-form-item>
      <el-form-item label="科室名称："prop="deptName">
        <el-input v-model="patient.deptName"></el-input>
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
  <el-form-item label="单位电话：">
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
  <el-form-item label="收住医师：" prop="houseDocName">
    <el-input v-model="patient.houseDocName" ></el-input>
  </el-form-item>
</el-col>
<el-col span="8">
  <el-form-item label="身份证号：" prop="idCard">
    <el-input v-model="patient.idCard" ></el-input>
  </el-form-item>
  <el-form-item label="出生年月：" required prop="birthday">
      <el-date-picker  placeholder="选择日期" v-model="patient.birthday" style="width:202px"></el-date-picker>
  </el-form-item>
  <el-form-item label="籍贯：" prop="dist">
    <el-input v-model="patient.dist" ></el-input>
  </el-form-item>
  <el-form-item label="工作单位：" prop="workName">
    <el-input v-model="patient.workName" ></el-input>
  </el-form-item>
  <el-form-item label="家庭住址：">
    <el-input v-model="patient.home"  ></el-input>
  </el-form-item>
  <el-form-item label="联系人地址：">
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
    data() {
      return {
        form:'',
        patient: {
          cardNo: '',
          patientName: '',
          patientState: '',
          sexCode: '',
          idCard: '',
          nation: '汉族',
          birthday: '',
          age: '',
          country: '中国',
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
          houseDocName: '',
          // prepayCost: '',
          // prepayMethod: '',
          diagnose: '',
          bedNo: '',
          deptName: '',
          inDate: '',
        },

        baseInfo:{},

        area: {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",
          31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",
          43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",
          61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
        },
        rules: {
          idCard:[
            {required: true, message:'身份证号不能为空',trigger:'blur',},
            {validator:this.validID, trigger:'blur'}
          ],
          patientName: [
            { required: true, message: '请输入患者姓名', trigger: 'blur' },
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
          linkmanTel: [
            { required: true, message: '请输入联系人电话', trigger: 'blur' }
          ],
          deptName: [
            { required: true, message: '请输入科室名称', trigger: 'blur' }
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
          linkmanName: [
            { required: true, message: '请输入联系人姓名', trigger: 'blur' }
          ],
          linkmanRelation: [
            { required: true, message: '请选择与患者关系', trigger: 'change' }
          ],
          bedNo: [
            { required: true, message: '请输入病床号', trigger: 'blur' }
          ],
          houseDocName: [
            { required: true, message: '请输入收住医师', trigger: 'blur' }
          ],
          birthday: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],
          dist: [
            { required: true, message: '请输入籍贯', trigger: 'blur' }
          ],
          workName: [
            { required: true, message: '请输入单位名称', trigger: 'blur' }
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
        options1: [
          {
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
        options2: [
          {
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
        options3: [
          {
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
        options5: [
          {
          index: 1,
          label: '出院',
        }, {
          index: 0,
          label: '住院',
        }]
      }
    },
    methods: {
      savePatient(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid) {
              var _this = this;
              var sexCodeTemp = '';
              if(_this.patient.sexCode == '男'){
                sexCodeTemp= '1';
              }else if(_this.patient.sexCode == '女'){
                sexCodeTemp= '2'
              }
              var data = {
                patientName: _this.patient.patientName,
                sexCode: sexCodeTemp,
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
                houseDocName: _this.patient.houseDocName,
                diagnose: _this.patient.diagnose,
                patientState: _this.patient.patientState,
                deptName: _this.patient.deptName,
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
                  console.log("data:"+data.sex);
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
      async validID(rule,value,callback) {
        let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(value)) {
          await this.go(value.length);
          callback()
        } else {
          callback(new Error('身份证号码不正确'))
        }
      },
        go(val) {
          let iden = this.patient.idCard;
          let sex = null;
          let birth = null;
          let myDate = new Date();
          let month = myDate.getMonth() + 1;
          let day = myDate.getDate();
          let age = 0;

          if(val===18){
            age = myDate.getFullYear() - iden.substring(6, 10) - 1;
            sex = iden.substring(16,17);
            birth = iden.substring(6,10)+"-"+iden.substring(10,12)+"-"+iden.substring(12,14)+" 00:00:00";
            if (iden.substring(10, 12) < month || iden.substring(10, 12) == month && iden.substring(12, 14) <= day) age++;

          }
          if(val===15){
            age = myDate.getFullYear() - iden.substring(6, 8) - 1901;
            sex = iden.substring(13,14);
            birth = "19"+iden.substring(6,8)+"-"+iden.substring(8,10)+"-"+iden.substring(10,12);
            if (iden.substring(8, 10) < month || iden.substring(8, 10) == month && iden.substring(10, 12) <= day) age++;
          }

          if(sex%2 === 0)
            sex = '0';//女
          else
            sex = '1';//男

          if(sex == '0')
            this.patient.sexCode  = '女';
          if(sex == '1')
            this.patient.sexCode  = '男';


          this.patient.age = age;
          this.patient.birthday = birth;
          this.patient.birthArea = this.area[iden.substring(0,2)];
          this.patient.dist = this.area[iden.substring(0,2)];
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
