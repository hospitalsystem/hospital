
<template>
  <el-card class="box-card card" v-if="flag">
  <register></register>
  </el-card>
</template>

<script>

  import axios from '../axios.js'
  import {postRequest} from '../utils/api'
  import register from '@/components/register'
  export default {
    created(){
      var staffNo=this.$store.state.token;
      console.log("userToken:"+staffNo);
      var staffNo={"staffNo":staffNo};
      postRequest('/getUserRole',staffNo).then(resp=>{
        console.log("resp:"+JSON.stringify(resp));
        var userRole=resp.data.staffRole;
        console.log("userRole:"+userRole);
        if(userRole=="1"){
          this.flag=true;
        }
      })
    },
    data () {
      return {
        users:'',
        flag:false
      }
    },
    methods:{
    },
    components: {
      register
    }
  }

</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }

  .hello {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    width: 400px;
    margin: 60px auto 0 auto;
  }
  .card{
    width: 50%;
    margin-top: 5%;
    margin-left: 20%;
  }
</style>
