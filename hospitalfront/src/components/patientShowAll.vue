<template>
<el-container>
  <el-header   :inline="true"  :model="formInline"class="cate_mana_header1">

    <el-form :inline="true" :model="formInline" class="cate_mana_header2" style="height: 50px">
      <el-form-item label="查询条件">
        <el-select v-model="formInline.searchOption" style="width: 200px" placeholder="请选择">
          <el-option label="全部" value="10"></el-option>
          <el-option label="病历号" value="1"></el-option>
          <el-option label="患者姓名" value="2"></el-option>
          <el-option label="住院号" value="6"></el-option>
          <el-option label="科室号" value="7"></el-option>
          <el-option label="床位号" value="8"></el-option>
          <el-option label="民族" value="3"></el-option>
          <el-option label="工作单位" value="4"></el-option>
          <el-option label="籍贯" value="5"></el-option>
          <el-option label="收住医师" value="9"></el-option>
        </el-select>
      </el-form-item >
      <el-input
      placeholder="请输入查询条件"
      v-model="formInline.searchCondition" style="width: 200px;" class="elInput">
      </el-input>
      <el-form-item>
        <el-button type="primary" size="medium" style="margin-left: 10px" @click="getPatientList">查询</el-button>
      </el-form-item>
    </el-form>

  </el-header>

   <br>
  <el-main class="cate_mana_main">
    <el-table
      :data="tableData"
      stripe
      style="width: 100%">
      <el-table-column
        prop="inpatientNo"
        label="住院号"
        >
      </el-table-column>
      <el-table-column
        prop="cardNo"
        label="病历号"
        >
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
        prop="deptNo"
        label="科室">
      </el-table-column>
      <el-table-column
        prop="bedNo"
        label="病床号">
      </el-table-column>
      <el-table-column
        prop="houseDocNo"
        label="收住医师">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row.cardNo)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-main>
</el-container>
</template>

<script>
    import {postRequest} from "../utils/api";

    export default {
      methods:{
        handleClick(row){
          console.log(row);

    },
          getPatientList(){
               var _this=this;

            postRequest('/getPatientList', {searchCondition: this.formInline.searchCondition, searchOption: this.formInline.searchOption}).then(resp => {
                if (resp.status == 200) {
                  var Data = resp.data;
                  console.log("JSON"+JSON.stringify(Data));
                  _this.patientList = Data.patientJSON;
                  _this.inpatientList = Data.inpatientJSON;
                  _this.tableData = [];

                  for(var i = 0; i < _this.patientList.length; i++){
                    var form = {
                        inpatientNo:'',
                        cardNo:'',
                        patientName: '',
                        workName: '',
                        deptNo: '',
                        houseDocNo: '',
                        bedNo: '',
                        dist: '',
                        nation:''
                    }

                    form.patientName = _this.patientList[i].patientName;
                    form.cardNo = _this.patientList[i].cardNo;
                    form.inpatientNo = _this.inpatientList[i].inpatientNo;
                    form.nation = _this.patientList[i].nation;
                    form.workName = _this.patientList[i].workName;
                    form.dist = _this.patientList[i].dist;
                    form.deptNo = _this.inpatientList[i].deptNo;
                    form.bedNo = _this.inpatientList[i].bedNo;
                    form.houseDocNo = _this.inpatientList[i].houseDocNo;

                    _this.tableData.push(form);

                  }

                }
            }, resp => {
              if (resp.response.status == 403) {
                _this.$message({
                  type: 'error',
                  message: resp.response.data
                });
              }
            });
          }
      },
      data(){
        return {
          patientList:[],//接收返回的满足查询条件的所有病人信息
          inpatientList:[],//接收返回的满足查询条件的所有住院信息
           formInline:{
             searchOption:'',
             searchCondition:''},
          tableData:[],

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
