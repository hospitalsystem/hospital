<template>
  <el-container>
    <el-header class="cate_mana_header">
      <el-input
        placeholder="请输入住院号"
        v-model="inPatientNumber" style="width: 200px;">
      </el-input>
      <el-button type="primary" size="medium" style="margin-left: 10px" @click="getPrepay">查询</el-button>
    </el-header>
    <el-main class="cate_mana_second">
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <el-form-item label="患者姓名:">
          <el-input  v-model="form.patientName" placeholder="患者姓名"></el-input>
        </el-form-item>
        <el-form-item label="住院科室:">
          <el-input v-model="form.deptNo" placeholder="住院科室"></el-input>
        </el-form-item>
        <el-form-item label="工作单位:">
          <el-input v-model="form.workName" placeholder="工作单位"></el-input>
        </el-form-item>
        <el-form-item label="入院日期:">
          <el-input v-model="form.inDate" placeholder="入院日期"></el-input>
        </el-form-item>
        <el-form-item label="住院医师:">
          <el-input v-model="form.houseDocNo" placeholder="住院医师"></el-input>
        </el-form-item>
        <el-form-item label="病床号:">
          <el-input v-model="form.bedNo" placeholder="病床号"></el-input>
        </el-form-item>
        <el-form-item label="出生日期:">
          <el-input v-model="form.birthday" placeholder="出生日期"></el-input>
        </el-form-item>
        <el-form-item label="支付方式:">
          <el-input v-model="form.prepayMethod" placeholder="支付方式"></el-input>
        </el-form-item>
        <el-form-item label="预交金总额:" class="prepayAll">
          <el-input v-model="form.prepayAll" placeholder="预交金总额"></el-input>
        </el-form-item>
        <el-form-item label="费用余额:" class="remain">
          <el-input v-model="form.remain" placeholder="费用余额"></el-input>
        </el-form-item>
        <el-form-item class="button">
          <el-button type="primary" @click="onSubmit">修改</el-button>
        </el-form-item>
      </el-form>
    </el-main>
    <el-main class="cate_mana_main">
      <el-table
        :data="tableData"
        stripe
        style="width: 100%">
        <el-table-column
          prop="operDate"
          label="操作日期"
          width="180">
        </el-table-column>
        <el-table-column
          prop="staffNo"
          label="操作员编号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="recipeNo"
          label="收据号">
        </el-table-column>
        <el-table-column
          prop="prepayState"
          label="状态">
        </el-table-column>
        <el-table-column
          prop="prepayCost"
          label="金额">
        </el-table-column>
        <el-table-column
          prop="issettlement"
          label="是否结算">
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  export default{
    methods: {
      getPrepay(){
        var _this = this;
        postRequest('/getPrepay', {inPatientNumber: this.inPatientNumber}).then(resp=> {
          if (resp.status == 200) {
            console.log("resp:"+JSON.stringify(resp));
            console.log("resp.data:"+JSON.stringify(resp.data));
            console.log("resp.data.json:"+JSON.stringify(resp.data.json));
            console.log("resp.data.data"+JSON.stringify(resp.data.data));
            var data=resp.data;
            var json=data.json;
            if(json.prepayMethod==1){
              _this.form.prepayMethod="现金支付";
            }
            else if(json.prepayMethod==2){
              _this.form.prepayMethod="银行卡支付";
            }
            else if(json.prepayMethod==3){
              _this.form.prepayMethod="支票支付";
            }
            _this.form.patientName=json.patientName;
            _this.form.inPath=json.inPath;
            _this.form.inDate=json.inDate;
            _this.form.workName=json.workName;
            _this.form.deptNo=json.deptNo;
            _this.form.houseDocNo=json.houseDocNo;
            _this.form.bedNo=json.bedNo;
            _this.form.birthday=json.birthday;
            _this.form.prepayAll=json.prepayAll;
            _this.form.remain=json.remain;
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
      deleteAll(){
        var _this = this;
        this.$confirm('确认删除这 ' + this.selItems.length + ' 条数据?', '提示', {
          type: 'warning',
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(()=> {
          var selItems = _this.selItems;
          var ids = '';
          for (var i = 0; i < selItems.length; i++) {
            ids += selItems[i].id + ",";
          }
          _this.deleteCate(ids.substring(0, ids.length - 1));
        }).catch(() => {
          //取消
          _this.loading = false;
        });
      },

      handleSelectionChange(val) {
        this.selItems = val;
      },

      handleEdit(index, row){
        var _this = this;
        this.$prompt('请输入新名称', '编辑', {
          confirmButtonText: '更新',
          inputValue: row.cateName,
          cancelButtonText: '取消'
        }).then(({value}) => {
          //value就是输入值
          if (value == null || value.length == 0) {
            _this.$message({
              type: 'info',
              message: '数据不能为空!'
            });
          } else {
            _this.loading = true;
            putRequest("/admin/category/", {id: row.id, cateName: value}).then(resp=> {
              var json = resp.data;
              _this.$message({
                type: json.status,
                message: json.msg
              });
              _this.refresh();
            }, resp=> {
              if (resp.response.status == 403) {
                _this.$message({
                  type: 'error',
                  message: resp.response.data
                });
              }
              _this.loading = false;
            });
          }
        });
      },

      handleDelete(index, row){
        let _this = this;
        this.$confirm('确认删除 ' + row.cateName + ' ?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.deleteCate(row.id);
        }).catch(() => {
          //取消
          _this.loading = false;
        });
      },

      deleteCate(ids){
        var _this = this;
        this.loading = true;
        //删除
        deleteRequest("/admin/category/" + ids).then(resp=> {
          var json = resp.data;
          _this.$message({
            type: json.status,
            message: json.msg
          });
          _this.refresh();
        }, resp=> {
          _this.loading = false;
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          } else if (resp.response.status == 500) {
            _this.$message({
              type: 'error',
              message: '该栏目下尚有文章，删除失败!'
            });
          }
        })
      },

      refresh(){
        let _this = this;
        getRequest("/admin/category/all").then(resp=> {
          _this.categories = resp.data;
          _this.loading = false;
        }, resp=> {
          if (resp.response.status == 403) {
            _this.$message({
              type: 'error',
              message: resp.response.data
            });
          }
          _this.loading = false;
        });
      },

      onSubmit() {
        console.log('submit!');
      }
    },
    // mounted: function () {
    //   this.loading = true;
    //   this.refresh();
    // },
    data(){
      return {
        inPatientNumber: '',
        selItems: [],
        categories: [],
        loading: false,
        tableData: [],
        form: {
          patientName: '',
          workName: '',
          deptNo:'',
          inPath:'',
          inDate:'',
          houseDocNo:'',
          bedNo:'',
          birthday:'',
          prepayMethod:'',
          prepayAll:'',
          remain:''
        }
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
</style>
