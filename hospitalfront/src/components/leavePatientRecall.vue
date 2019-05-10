<template>
  <el-container>
    <el-header class="cate_mana_header" v-show="isShow">
      <el-input
        placeholder="请输入住院号进行出院召回"
        v-model="inpatientNoSearch" style="width: 200px;" @keyup.enter.native="this.recallInpatientInfoSearch"
      >
      </el-input>
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="this.recallInpatientInfoSearch" >查询</el-button>
    </el-header>

    <el-main class="cate_mana_main" v-show="isShow" >
      <el-form :inline="true"  class="demo-form-inline" labelWidth="120px" align="left">
        <el-form-item label="患者姓名：">
          <el-input readonly="true" v-model="patient.patientName"></el-input>
        </el-form-item>
        <el-form-item label="患者状态：">
          <el-input readonly="true" v-model="patient.patientState"></el-input>
        </el-form-item>
        <el-form-item label="身份证号：">
          <el-input readonly="true" v-model="patient.idcard"></el-input>
        </el-form-item>
        <el-form-item label="性别：">
          <el-input readonly="true" v-model="patient.sexCode"></el-input>
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
          <el-input readonly="true"  v-model="patient.country"></el-input>
        </el-form-item>
        <el-form-item label="籍贯：">
          <el-input readonly="true"  v-model="patient.dist"></el-input>
        </el-form-item>
        <el-form-item label="出生地：">
          <el-input readonly="true"  v-model="patient.birthArea"></el-input>
        </el-form-item>
        <el-form-item label="婚姻状况">
          <el-input readonly="true" v-model="patient.mariCode"></el-input>
        </el-form-item>
        <el-form-item label="工作单位：">
          <el-input readonly="true"  v-model="patient.workName"></el-input>
        </el-form-item>
        <el-form-item label="职业：">
          <el-input readonly="true" v-model="patient.occupation"></el-input>
        </el-form-item>
        <el-form-item label="单位电话：">
          <el-input readonly="true" v-model="patient.workTel" ></el-input>
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
      </el-form>
    </el-main>

    <el-main class="cate_mana_main" v-show="isShowinput&&isShow">
      <el-form :inline="true"  :model="inpatient" class="demo-form-inline" align="left" :rules="rules" ref="inpatient" labelWidth="150px">
        <el-form-item label="入院来源：" class="inSource" prop="inSource">
          <el-select v-model="inpatient.inSource" placeholder="请选择" style="width: 206.4px">
            <el-option
              v-for="item in inSourceOptions"
              :key="item.index"
              :label="item.label"
              :value="item.index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入院途径：" class="inPath" prop="inPath">
          <el-select v-model="inpatient.inPath" placeholder="请选择" style="width: 206.4px">
            <el-option
              v-for="item in inPathOptions"
              :key="item.index"
              :label="item.label"
              :value="item.index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入院情况：" prop="status">
          <el-input v-model="inpatient.status" placeholder="入院情况" ></el-input>
        </el-form-item>
        <el-form-item label="科室名称：" prop="deptName">
          <el-input v-model="inpatient.deptName" placeholder="科室名称" ></el-input>
        </el-form-item>
        <el-form-item label="病床编号：" prop="bedNo">
          <el-input v-model="inpatient.bedNo" placeholder="病床编号" ></el-input>
        </el-form-item>
        <el-form-item label="住院医师：" prop="houseDocName">
          <el-input v-model="inpatient.houseDocName" placeholder="住院医师" ></el-input>
        </el-form-item>
        <el-form-item label="主治医师：" prop="chargeDocName">
          <el-input v-model="inpatient.chargeDocName" placeholder="主治医师" ></el-input>
        </el-form-item>
        <el-form-item label="主任医师：" prop="chiefDocName">
          <el-input v-model="inpatient.chiefDocName" placeholder="主任医师" ></el-input>
        </el-form-item>
        <el-form-item label="责任护士：" prop="dutyNurseName">
          <el-input v-model="inpatient.dutyNurseName" placeholder="责任护士" ></el-input>
        </el-form-item>
        <el-row>
          <el-button type="primary" size="medium" style="margin-left: 10px" @click="recallPatientRegister('inpatient')" >
            出院召回
          </el-button>
        </el-row>
      </el-form>
    </el-main>

    <el-main class="cate_mana_second" v-show="!isShow">
      <el-form :inline="true" :model="form" class="demo-form-inline" align="left" labelWidth="150px">
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
        <el-form-item label="科室名称：">
          <el-input readonly="true" v-model="inpatient.deptName"></el-input>
        </el-form-item>
        <el-form-item label="病床编号：">
          <el-input readonly="true" v-model="inpatient.bedNo"></el-input>
        </el-form-item>
        <el-form-item label="住院医师：">
          <el-input readonly="true" v-model="inpatient.houseDocName"></el-input>
        </el-form-item>
        <el-form-item label="主治医师：">
          <el-input readonly="true" v-model="inpatient.chargeDocName"></el-input>
        </el-form-item>
        <el-form-item label="主任医师：">
          <el-input readonly="true" v-model="inpatient.chiefDocName"></el-input>
        </el-form-item>
        <el-form-item label="责任护士：">
          <el-input readonly="true" v-model="inpatient.dutyNurseName"></el-input>
        </el-form-item>
      </el-form>
    </el-main>

    <el-main class="cate_mana_second" v-show="!isShow">
      <el-form :inline="true" :model="form" class="demo-form-inline" align="left" labelWidth="150px">
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
          <el-input readonly="true" v-model="patient.patientState"></el-input>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
  import moment from "moment";
  import {postRequest} from '../utils/api';
  import {putRequest} from '../utils/api';
  import {deleteRequest} from '../utils/api';
  import {getRequest} from '../utils/api';
  import ElInput from "../../node_modules/element-ui/packages/input/src/input.vue";
  import ElRow from "element-ui/packages/row/src/row";
  export default {
    /*
    往回发送的时候，我们传输的是patient的idcard
     */
    components: {
      ElRow,
      ElInput},
    data() {
      return {
        inpatient: {
          inpatientNo: '',
          patientName: '',
          idCard: '',
          inDate: '',
          inSource: '',
          inPath: '',
          status: '',
          deptName: '',
          bedNo: '',
          houseDocName: '',
          chargeDocName: '',
          chiefDocName: '',
          dutyNurseName: '',
          outDate: '',
          outState: ''
        },
        patient: {
          cardNo: '',
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
          patientState: ''
        },
        bed: {
          bedNo: '',
          bedState: ''
        },
        isShow: true,
        isShowinput: false,
        inpatientNoSearch:'',
        inSourceOptions:[
          {
            index: 1,
            label: '门诊'
          },{
            index: 2,
            label: '急诊'
          },{
            index: 3,
            label: '转科'
          },{
            index: 4,
            label: '转院'
          }
        ],
        inPathOptions:[
          {
            index: 1,
            label: '本市'
          },{
            index: 2,
            label: '外市'
          },
        ],

        rules:{
          inSource: [
            { required: true, message: '请选择入院来源', trigger: 'change' },
            ],
          inPath:[
            { required: true, message: '请选择入院途径', trigger: 'change'},
          ],
          status:[
            { required: true, message: '请输入入院情况', trigger: 'blur'},
          ],
          deptName:[
            { required: true, message: '请输入科室名称', trigger: 'blur'},
          ],
          bedNo:[
            { required: true, message: '请输入病床号', trigger: 'blur'},
          ],
          houseDocName:[
            { required: true, message: '请输入住院医师', trigger: 'blur'},
          ],
          chargeDocName:[
            { required: true, message: '请输入主治医师', trigger: 'blur'},
          ],
          chiefDocName:[
            { required: true, message: '请输入主任医师', trigger: 'blur'},
          ],
          dutyNurseName:[
            { required: true, message: '请输入责任护士', trigger: 'blur'},
          ]
        }
      }
    },
    methods: {
      recallInpatientInfoSearch() {
        var _this = this;
        postRequest('/recallInpatientInfoSearch', {inpatientNoSearch: this.inpatientNoSearch}).then(resp => {
          if (resp.status == 200) {

            var Data = resp.data;
            var patientJSON = Data.patientJSON;

            if (patientJSON.patientState == 1) {
              //只有出院状态的病人才能住院

              _this.isShowinput = true;

              _this.inpatient.patientName = patientJSON.patientName;
              _this.inpatient.idCard = patientJSON.idCard;

              _this.patient.cardNo = patientJSON.cardNo;
              _this.patient.patientName = patientJSON.patientName;
              if (patientJSON.sexCode == 1) {
                _this.patient.sexCode = '男';
              } else if (patientJSON.sexCode == 2) {
                _this.patient.sexCode = '女';
              }
              _this.patient.idcard = patientJSON.idCard;
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
            }
            else if (patientJSON.patientState == 0) {
              alert('该病人正在住院，无法进行出院召回操作');
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

      recallPatientRegister(formName){
        var _this = this;

        console.log('formName:'+formName)
        var data = {
          patientName: _this.patient.patientName,
          idCard: _this.patient.idcard,
          //indate直接在后台生成
          inSource: _this.inpatient.inSource,
          inPath : _this.inpatient.inPath,
          status: _this.inpatient.status,
          deptName: _this.inpatient.deptName,
          bedNo: _this.inpatient.bedNo,
          houseDocName: _this.inpatient.houseDocName,
          chargeDocName: _this.inpatient.chargeDocName,
          chiefDocName: _this.inpatient.chiefDocName,
          dutyNurseName: _this.inpatient.dutyNurseName,
          //不需要outDate和outState
        }

        this.$refs[formName].validate((valid) => {
          if (valid) {
            postRequest("/recallPatientRegister", data).then(resp => {
              if (resp.status == 200) {
                alert('出院召回成功');
                this.isShow = false;
                //从后台发起请求，请求最新的inpatient数据
                var Data = resp.data;
                var inpatientJSON = Data.inpatientJSON;
                _this.inpatient.patientName = inpatientJSON.patientName;
                _this.inpatient.inpatientNo = inpatientJSON.inpatientNo;
                _this.inpatient.patientName = inpatientJSON.patientName;
                _this.inpatient.idCard = inpatientJSON.idCard;
                _this.inpatient.inDate = inpatientJSON.inDate;
                console.log("住院时间" + inpatientJSON.inDate);
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
                _this.inpatient.deptName = inpatientJSON.deptName;
                _this.inpatient.bedNo = inpatientJSON.bedNo;
                _this.inpatient.houseDocName = inpatientJSON.houseDocName;
                _this.inpatient.chargeDocName = inpatientJSON.chargeDocName;
                _this.inpatient.chiefDocName = inpatientJSON.chiefDocName;
                _this.inpatient.dutyNurseName = inpatientJSON.dutyNurseName;

                if (inpatientJSON.outState == 1) {
                  _this.inpatient.outState = '治愈';
                } else if (inpatientJSON.outState == 2) {
                  _this.inpatient.outState = '好转';
                } else if (inpatientJSON.outState == 3) {
                  _this.inpatient.outState = '死亡';
                } else if (inpatientJSON.outState == 4) {
                  _this.inpatient.outState = '治残';
                } else if (inpatientJSON.outState == 5) {
                  _this.inpatient.outState = '未治愈';
                } else if (inpatientJSON.outState == 6) {
                  _this.inpatient.outState = '正常分娩';
                } else if (inpatientJSON.outState == 7) {
                  _this.inpatient.outState = '其他';
                }
              }
            })
          }else{
            alert('必填内容不能为空');
            return;
          }
        });
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

</style>
