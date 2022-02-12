<template>
  <div style="height:625px;width:100%;background-color:#1C1C1C ">
      <div style="margin-left:25%;padding-top:150px;">

      </div>
      <div class="login">
          <span style="color:white;font-size:18px">管理员登录</span>
            <el-form :model="formModel" status-icon :rules="rules" ref="ruleForm" label-width="80px" style="padding-top:20px;width:60%;padding-left:35%">
                <el-form-item label="管理员名" prop="userName">
                    <el-input v-model="formModel.userName" style="width:100%"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="formModel.password" style="width:100%"></el-input>
                </el-form-item>
                    <el-button style="width:50%;margin-left:85px;" @click="login('ruleForm')">登录</el-button>
                </el-form>
        </div>
  </div>
</template>

<script>
  export default {
      data(){
          return{
              formModel:{},
              rules:
                {
                    userName: [
                    { required: true, message: '管理员名不能为空', trigger: 'blur' }
                    ],
                    password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                    ],
                },
          }
      },
    methods: {
      login(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var data={data:{userName:this.formModel.userName,password:this.formModel.password,operation:0}}
          this.$axios.post('/user/login', data).then(resp => {
            if(resp.data.data.successFlag==1){
              this.$cookies.set("adminName",resp.data.data.userName,60 * 60 * 1)
              // this.$store.commit('setAdminName', resp.data.data.userName);
              this.$message({
                showClose:true,
                message:"登录成功",
                type:"success"
              })
              this.$router.push({name:"userInfoView"})
            }else{
              this.$message({
                showClose:true,
                message:"管理员名或密码错误，请重新输入",
                type:"warning"
              })
              this.formModel.userName=""
              this.formModel.password=""
            }
          })
        }
      })
   }
       
    }
  }
</script>

<style scoped>
  >>>.el-form-item__label{
      color:white
  }
</style>