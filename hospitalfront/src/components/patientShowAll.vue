<template>
<el-container>
  <!--当点击“查看并修改”按钮后，隐藏以下内容-->
  <el-header :inline="true"  :model="formInline"class="cate_mana_header1" v-show="isShow">
    <el-form :inline="true" :model="formInline" class="cate_mana_header2" style="height: 50px">
      <el-form-item label="查询条件">
        <el-select v-model="formInline.searchOption" style="width: 200px" placeholder="请选择">
          <el-option label="全部" value="10"></el-option>
          <el-option label="病历号" value="1"></el-option>
          <el-option label="患者姓名" value="2"></el-option>
          <el-option label="住院号" value="6"></el-option>
          <el-option label="科室名称" value="7"></el-option>
          <el-option label="床位号" value="8"></el-option>
          <el-option label="民族" value="3"></el-option>
          <el-option label="工作单位" value="4"></el-option>
          <el-option label="籍贯" value="5"></el-option>
          <el-option label="收住医师" value="9"></el-option>
        </el-select>
      </el-form-item >
      <el-input placeholder="请输入查询条件" :readonly="formInline.searchOption == '10'"v-model="formInline.searchCondition" style="width: 200px;" class="elInput"></el-input>
      <el-form-item>
        <el-button type="primary" size="medium" style="margin-left: 10px" @click="getPatientList">查询</el-button>
      </el-form-item>
    </el-form>
  </el-header>
  <br>
  <el-main class="cate_mana_main"  v-show="isShow">
    <el-table
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      stripe
      style="width: 100%">
      <el-table-column
        prop="inpatientNo"
        label="住院号">
      </el-table-column>
      <el-table-column
        prop="cardNo"
        label="病历号">
      </el-table-column>
      <el-table-column
        prop="patientName"
        label="患者姓名">
      </el-table-column>
      <el-table-column
        prop="nation"
        label="民族">
      </el-table-column>
      <el-table-column
        prop="workName"
        label="单位">
      </el-table-column>
      <el-table-column
        prop="dist"
        label="籍贯">
      </el-table-column>
      <el-table-column
        prop="deptName"
        label="科室名称">
      </el-table-column>
      <el-table-column
        prop="bedNo"
        label="病床号">
      </el-table-column>
      <el-table-column
        prop="houseDocName"
        label="收住医师">
      </el-table-column>
      <el-table-column
        prop="patientState"
        label="病人状态">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="ShowAndEditPatientInfo(scope.row.inpatientNo)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>

    </el-table>
    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :pageSize="pageSize"
        background
        @current-change="CurrentChange"
        :total="tableData.length">
      </el-pagination>
    </div>
  </el-main>

  <!--当点击“查看并修改”按钮后，显示以下内容-->
  <el-header class="cate_mana_header1"  v-show="!isShow">
    <p>查看并修改当前病人信息</p>
    <el-button type="primary" size="medium" style="margin-left: 10px" @click="back">返回</el-button>
  </el-header>
  <el-main class="cate_mana_second"  v-show="!isShow">
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
        <el-form-item label="主治医师" prop="chargeDocName">
          <el-input v-model="patient.chargeDocName"></el-input>
        </el-form-item>
        <el-form-item label="住院日期：" required prop="inDate">
          <el-form-item >
            <el-date-picker  :disabled="true" v-model="patient.inDate" style="width:202px"></el-date-picker>
          </el-form-item>
        </el-form-item>
      </el-col>
      <el-col span="8">
        <el-form-item label="病历号：" prop="cardNo">
          <el-input v-model="patient.cardNo" :disabled="true" ></el-input>
        </el-form-item>
        <el-form-item label="患者状态" prop="patientState" >
          <el-select v-model="patient.patientState"  :disabled="true" style="width:202px">
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
        <el-form-item label="收住医师：" prop="houseDocName">
          <el-input v-model="patient.houseDocName" ></el-input>
        </el-form-item>
        <el-form-item label="责任护士" prop="dutyNurseName">
          <el-input v-model="patient.dutyNurseName"></el-input>
        </el-form-item>
        <el-form-item  label="出院日期：" required prop="outDate">
          <el-form-item >
            <el-date-picker  :disabled="true" v-model="patient.outDate" style="width:202px"></el-date-picker>
          </el-form-item>
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
        <el-form-item label="入院情况："  prop="status">
          <el-input v-model="patient.status"></el-input>
        </el-form-item>
        <el-form-item label="主任医师" prop="chiefDocNo">
          <el-input v-model="patient.chiefDocName"></el-input>
        </el-form-item>


        <el-form-item label="出院状态:" class="outState">
          <el-select v-model="patient.outState" placeholder="请选择" style="width:206.4px">
            <el-option
              v-for="item in options6"
              :key="item.index"
              :label="item.label"
              :value="item.index">
            </el-option>
          </el-select>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" size="medium" style="margin-left: 10px" @click="patientChange">修改并保存</el-button>
        </el-form-item>
      </el-col>
    </el-form>
  </el-main>
</el-container>

</template>

<script>
    import {postRequest} from "../utils/api";
    import {getRequest} from '../utils/api'
    import ElFormItem from "../../node_modules/element-ui/packages/form/src/form-item.vue";
    import ElInput from "../../node_modules/element-ui/packages/input/src/input.vue";
    export default {
      components: {
        ElInput,
        ElFormItem},
      methods:{
        //根据查询选项及查询条件向后台发起请求并对返回的数据进行处理
        getPatientList(){
          var _this=this;
          postRequest('/getPatientList', {searchCondition: this.formInline.searchCondition, searchOption: this.formInline.searchOption}).then(
            resp => {
            if (resp.status == 200) {
              var Data = resp.data;
              console.log("JSON"+JSON.stringify(Data));
              _this.patientList = Data.patientJSON;
              _this.inpatientList = Data.inpatientJSON;
              _this.tableData = [];
              for(var i = 0; i < _this.patientList.length; i++) {
                var form = {
                  inpatientNo:'',
                  cardNo:'',
                  patientName: '',
                  workName: '',
                  deptName: '',
                  houseDocNo: '',
                  bedNo: '',
                  dist: '',
                  nation:'',
                  patientState:''
                }
                form.patientName = _this.patientList[i].patientName;
                form.cardNo = _this.patientList[i].cardNo;
                form.inpatientNo = _this.inpatientList[i].inpatientNo;
                form.nation = _this.patientList[i].nation;
                form.workName = _this.patientList[i].workName;
                form.dist = _this.patientList[i].dist;
                form.deptName = _this.inpatientList[i].deptName;
                form.bedNo = _this.inpatientList[i].bedNo;
                form.houseDocName = _this.inpatientList[i].houseDocName;
                form.patientState = _this.patientList[i].patientState;

                _this.tableData.push(form);
              }
            }
            },
            resp => {
            if (resp.response.status == 403) {
              _this.$message({
                type: 'error',
                message: resp.response.data
              });
            }
          });
        },

        //点击某一住院信息后，查看或修改某个用户的信息
        ShowAndEditPatientInfo(row){
          this.isShow = !this.isShow; //隐藏查询列表界面，展示特定住院记录
          var _this = this;
          postRequest("/getPatient",{inPatientNo : row}).then(
            resp=> {
              if (resp.status == 200) {
              var Data = resp.data;
              var patientJSON = Data.patientJSON;
              var inpatientJSON = Data.inpatientJSON;

              console.log("前端接收到的Data:" + JSON.stringify(Data));

              //接收JSON中的patient部分
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

              //接收JSON中的inpatient部分
              _this.patient.patientName = inpatientJSON.patientName;
              _this.patient.inpatientNo = inpatientJSON.inpatientNo;
              _this.patient.patientName = inpatientJSON.patientName;
              _this.patient.idCard = inpatientJSON.idCard;
              _this.patient.inDate = inpatientJSON.inDate;
              if (inpatientJSON.inSource == 1) {
                _this.patient.inSource = '门诊';
              } else if (inpatientJSON.inSource == 2) {
                _this.patient.inSource = '急诊';
              } else if (inpatientJSON.inSource == 3) {
                _this.patient.inSource = '转科';
              } else if (inpatientJSON.inSource == 4) {
                _this.patient.inSource = '转院';
              }
              if (inpatientJSON.inPath == 1) {
                _this.patient.inPath = '本市';
              } else if (inpatientJSON.inPath == 2) {
                _this.patient.inPath = '外市';
              }
              _this.patient.status = inpatientJSON.status;
              _this.patient.deptName = inpatientJSON.deptName;
              _this.patient.bedNo = inpatientJSON.bedNo;
              _this.patient.houseDocName = inpatientJSON.houseDocName;
              _this.patient.chargeDocName = inpatientJSON.chargeDocName;
              _this.patient.chiefDocName = inpatientJSON.chiefDocName;
              _this.patient.dutyNurseName = inpatientJSON.dutyNurseName;
              _this.patient.outDate = inpatientJSON.outDate;
              if(inpatientJSON.outState == 1){
                _this.patient.outState = '治愈';
              }else if(inpatientJSON.outState == 2){
                _this.patient.outState = '好转';
              }else if(inpatientJSON.outState == 3){
                _this.patient.outState = '死亡';
              }else if(inpatientJSON.outState == 4){
                _this.patient.outState = '治残';
              }else if(inpatientJSON.outState == 5){
                _this.patient.outState = '未治愈';
              }else if(inpatientJSON.outState == 6){
                _this.patient.outState = '正常分娩';
              }else if(inpatientJSON.outState == 7){
                _this.patient.outState = '其他';
              }
              }
            },
            resp => {
            if (resp.response.status == 403) {
              _this.$message({
                type: 'error',
                message: resp.response.data
              });
            }
          });
        },

        //修改新保存的记录并传输到后台服务器
        patientChange(){
          var _this = this;
          if(_this.patient.sexCode == '男'){
            _this.patient.sexCode = '1';
          }else if(_this.patient.sexCode == '女'){
            _this.patient.sexCode = '2';
          }
          if(_this.patient.mariCode == '未婚'){
            _this.patient.mariCode = '1';
          }else if(_this.patient.mariCode == '已婚'){
            _this.patient.mariCode = '2';
          }else if(_this.patient.mariCode == '丧偶'){
            _this.patient.mariCode = '3';
          }else if(_this.patient.mariCode == '离婚'){
            _this.patient.mariCode = '4';
          }
          if(_this.patient.occupation == '国家公务员'){
            _this.patient.occupation = '11';
          }else if(_this.patient.occupation == '专业技术人员'){
            _this.patient.occupation = '13';
          }else if(_this.patient.occupation == '职员'){
            _this.patient.occupation = '17';
          }else if(_this.patient.occupation == '企业管理人员'){
            _this.patient.occupation = '21';
          }else if(_this.patient.occupation == '工人'){
            _this.patient.occupation = '24';
          }else if(_this.patient.occupation == '农民'){
            _this.patient.occupation = '27';
          }else if(_this.patient.occupation == '学生'){
            _this.patient.occupation = '31';
          }else if(_this.patient.occupation == '现役军人'){
            _this.patient.occupation = '37';
          }else if(_this.patient.occupation == '自由职业者'){
            _this.patient.occupation = '51';
          }else if(_this.patient.occupation == '个人经营者'){
            _this.patient.occupation = '54';
          }else if(_this.patient.occupation == '无业人员'){
            _this.patient.occupation = '70';
          }else if(_this.patient.occupation == '退（离）休人员'){
            _this.patient.occupation = '80';
          }else if(_this.patient.occupation == '其他'){
            _this.patient.occupation = '90';
          }
          if(_this.patient.linkmanRelation == '配偶'){
            _this.patient.linkmanRelation = '1';
          }else if(_this.patient.linkmanRelation == '子'){
            _this.patient.linkmanRelation = '2';
          }else if(_this.patient.linkmanRelation == '女'){
            _this.patient.linkmanRelation = '3';
          }else if(_this.patient.linkmanRelation == '孙子、孙女或外孙子、外孙女'){
            _this.patient.linkmanRelation = '4';
          }else if(_this.patient.linkmanRelation == '父母'){
            _this.patient.linkmanRelation = '5';
          }else if(_this.patient.linkmanRelation == '祖父母或外祖父母'){
            _this.patient.linkmanRelation = '6';
          }else if(_this.patient.linkmanRelation == '兄弟姐妹'){
            _this.patient.linkmanRelation = '7';
          }else if(_this.patient.linkmanRelation == '其他'){
            _this.patient.linkmanRelation = '8';
          }
          if(_this.patient.inSource == '门诊'){
            _this.patient.inSource = '1';
          }else if(_this.patient.inSource == '急诊'){
            _this.patient.inSource = '2';
          }else if(_this.patient.inSource == '转科'){
            _this.patient.inSource = '3';
          }else if(_this.patient.inSource == '转院'){
            _this.patient.inSource = '4';
          }
          if(_this.patient.inPath == '本市'){
            _this.patient.inPath = '1';
          }else if(_this.patient.inPath == '外市'){
            _this.patient.inPath = '2';
          }
          if(_this.patient.outState == '治愈'){
            _this.patient.outState = '1';
          }else if(_this.patient.outState == '好转'){
            _this.patient.outState = '2';
          }else if(_this.patient.outState == '死亡'){
            _this.patient.outState = '3';
          }else if(_this.patient.outState == '治残'){
            _this.patient.outState = '4';
          }else if(_this.patient.outState == '未治愈'){
            _this.patient.outState = '5';
          }else if(_this.patient.outState == '正常分娩'){
            _this.patient.outState = '6';
          }else if(_this.patient.outState == '其他'){
            _this.patient.outState = '7';
          }
          var data={
            cardNo: _this.patient.cardNo,
            patientName: _this.patient.patientName,
            sexCode:_this.patient.sexCode,
            idCard:_this.patient.idCard,
            nation:_this.patient.nation,
            birthday:_this.patient.birthday,
            age:_this.patient.age,
            country:_this.patient.country,
            dist:_this.patient.dist,
            birthArea:_this.patient.birthArea,
            mariCode:_this.patient.mariCode,
            workName:_this.patient.workName,
            occupation:_this.patient.occupation,
            workTel:_this.patient.workTel,
            home:_this.patient.home,
            homeTel:_this.patient.homeTel,
            linkmanName:_this.patient.linkmanName,
            linkmanRelation:_this.patient.linkmanRelation,
            linkmanAdd:_this.patient.linkmanAdd,
            linkmanTel:_this.patient.linkmanTel,
            pactName:_this.patient.pactName,

            //以下为inpatient的属性
            inpatientNo:_this.patient.inpatientNo,
            inSource:_this.patient.inSource,
            inPath:_this.patient.inPath,
            status:_this.patient.status,
            deptName:_this.patient.deptName,
            bedNo:_this.patient.bedNo,
            houseDocName:_this.patient.houseDocName,
            chargeDocName:_this.patient.chargeDocName,
            chiefDocName:_this.patient.chiefDocName,
            dutyNurseName:_this.patient.dutyNurseName,
            outState:_this.patient.outState,
          }

          console.log("即将发送的数据"+data.toString());

          postRequest("/editPatientInfo", data).then(
            resp => {
              if (resp.status == 200) {
                //修改结束后提示修改成功并返回上一个界面
                alert('信息修改成功！');
                this.$router.push('/home');
                this.isShow = !this.isShow;
                console.log("发送的data为"+data.inDate);
              }
            });
        },

        //用于从“查看”界面，返回“查询”界面
        back(){
          this.isShow = !this.isShow;
        },
        CurrentChange:function(currentPage){
          this.currentPage = currentPage;
          console.log(this.currentPage)
        },
        },
      data(){
        return {
          currentPage:1, //分页的默认开始页数
          pageSize: 3, //每页显示的数据个数
          isShow: true, //用于控制界面的显示
          patientList:[],//接收返回的满足查询条件的所有病人信息
          inpatientList:[],//接收返回的满足查询条件的所有住院信息
          formInline:{
            searchOption:'',
            searchCondition:''
          },
          tableData:[],
          total: 0,
          pagesize:10,
          currentPage:1,
          //以下内容用于查看并修改具体的信息
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
            patientState:'', //只读

            //以下为inpatient的属性，为了方便，写在了patient里面
            inpatientNo:'',
            inDate:'',
            inSource:'',
            inPath:'',
            status:'',
            deptName:'',
            bedNo:'',
            houseDocName:'',
            chargeDocName:'',
            chiefDocName:'',
            dutyNurseName:'',
            outDate:'',
            outState:'',

            //以下为outpatient的属性，为了方便，写在patient里面。其实没用到。。。
            diagnose:''
          },

          //婚姻状况下拉选项
          options1:[
            {
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
          //职业下拉选项
          options2:[
            {
              index: 11,
              label:'国家公务员',
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
          //与患者关系下拉选项
          options3:[
            {
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
          //性别下拉选项
          options4:[
            {
              index: 1,
              label:'男',
            }, {
              index: 2,
              label: '女',
            }],
          //出院状况的下拉选项
          options6:[
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
          ],
        }
      }
    }
</script>

<style scoped>
  .cate_mana_header1 {
    background-color: #ececec;
    margin-top: 20px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
    height: 80px;
  }
  .cate_mana_header2 {
    background-color: #ececec;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
    padding-top: 10px;
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
  .prepayAll{
    float: left;
    margin-left: 25px;
  }
  .remain{
    float: left;
    margin-left: 6px;
  }
  .button{
    float: left;
    width: 110px;
  }
  .elInput{
    display: block;
    width: 35%;
    margin-left: 220px;
  }
</style>
