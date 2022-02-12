<template>
  <div>
    <el-row>
        <el-col :span="4">
            <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.jpg" /></div>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="24">
            <div class="zhuye">
                <div class="login">
                    <el-form :model="formModel" status-icon :rules="rules" ref="ruleForm" label-width="100px" style="padding-top:20px">
                        <el-form-item label="用户名" prop="userName">
                            <el-input v-model="formModel.userName" style="width:90%"></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                            <el-input type="password" v-model="formModel.password" style="width:90%"></el-input>
                        </el-form-item>
                            <el-button class="login-button" @click="login('ruleForm')">登录</el-button>
                    </el-form>
                </div>
            </div>
        </el-col>
    </el-row>
   
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      formModel:{
        userName:"",
        password:""
      },
      rules:
      {
        userName: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
      },
      loginStatus:false
    }
  },
  mounted() {

  },
  methods: {
    back(){
        this.$router.push({name:"main"})
    },
    login(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var data={data:{userName:this.formModel.userName,password:this.formModel.password,operation:1}}
          this.$axios.post('/user/login', data).then(resp => {
            if(resp.data.data.successFlag==1){
              // this.$store.commit('setUserId', resp.data.data.userId);
              // this.$store.commit('setUserName', resp.data.data.userName);
              this.$cookies.set("userName",resp.data.data.userName,60 * 60 * 1)
              this.$cookies.set("userId",resp.data.data.userId,60 * 60 * 1)
              this.$message({
                showClose:true,
                message:"登录成功",
                type:"success"
              })
              this.$router.push({ name:"main"})
            }else{
              this.$message({
                showClose:true,
                message:"用户名或密码错误，请重新输入",
                type:"warning"
              })
              this.formModel.userName=""
              this.formModel.password=""
            }
          })
        }
      })
   }
    
  },
};
</script>

<style scoped>
.zhuye{
    background-image:url("http://localhost:8090/static/img/zhuye.jpg") ;
    width:1350px;
    height:550px
}
.login{
    float:right;
    margin-top:200px;
    margin-right:20px;
    background-color:#fff ;
    width:300px;
    height:200px;
}
.login-button{
    background: #f40;
    border:#f40;
    width:90%;
    color: #fff;
}
</style>