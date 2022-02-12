<template>
  <div>
    <el-row>
        <el-col :span="4">
            <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.jpg" /></div>
        </el-col>
        <el-col :span="4">
            <div class="register-font"><b>用户注册</b></div>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="24">
            <div class="zhuye">
                 <el-form :model="formModel" status-icon :rules="rules" ref="ruleForm" label-width="100px">
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="用户名" prop="userName">
                                <el-input v-model="formModel.userName" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="电子邮箱" prop="email">
                                <el-input v-model="formModel.email" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="密码" prop="password">
                                <el-input type="password" v-model="formModel.password" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="地址" prop="address">
                                <el-row style="width:95%;padding-left:5%">
                                    <el-col :span="12">
                                        <el-cascader
                                            style="width:100%"
                                            :options="options"
                                            v-model="selectedOptions"
                                            @change="handleChange">
                                        </el-cascader>
                                    </el-col>
                                    <el-col :span="12">
                                         <el-input placeholder="详细地址：小区楼栋" v-model="formModel.address" style="width:100%"></el-input>
                                    </el-col>
                                </el-row>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="确认密码" prop="passwordSuccess">
                                <el-input @input="passwordSuccess" type="password" v-model="formModel.passwordSuccess" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="性别" prop="gender">
                                <el-select v-model="formModel.gender" style="width:90%" placeholder="请选择">
                                    <el-option
                                    v-for="item in genderOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="头像" prop="path">
                                <el-upload
                                    class="avatar-uploader"
                                    accept=".jpg,.jpeg,.png,.JPG,.JPEG"
                                    action=""
                                    :http-request="upload"
                                    name="file"
                                    :show-file-list="false">
                                    <img v-if="formModel.path" :src="formModel.path" class="avatar">
                                    <div v-else class="avatar_div"><i class="el-icon-plus avatar-uploader-icon"></i></div>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="身份证号码" prop="idcard">
                                <el-input v-model="formModel.idcard" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="年龄" prop="age">
                                <el-input :readonly="true" v-model="formModel.age" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="电话号码" prop="telephone">
                                <el-input v-model="formModel.telephone" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="真实姓名" prop="realName">
                                <el-input v-model="formModel.realName" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                     </el-row>
                    <el-button class="login-button" @click="register('ruleForm')">注册</el-button>
                </el-form>
            </div>
        </el-col>
    </el-row>
   
  </div>
</template>

<script>
import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode } from 'element-china-area-data'
export default {
  name: "login",
  data() {
    return {
        options: regionData,
        selectedOptions: [],
      genderOptions:[
          {
              label:"男",
              value:1
          },
          {
              label:"女",
              value:2
          },
      ],
      formModel:{
        userName:"",
        password:"",
        passwordSuccess:"",
        idcard:"",
        path:"",
        telephone:"",
        email:"",
        address:"",
        gender:"",
        age:"",
        realName:""
      },
      rules:
      {
        userName: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        passwordSuccess: [
          { required: true, message: '请确认密码', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        idcard: [
          { required: true, message: '身份证号码不能为空', trigger: 'blur' },
          {
            //自定义身份证号验证
            validator: (rule, value, callback) => {
              var checkCode = function (val) {
                var p = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
                var factor = [
                  7,
                  9,
                  10,
                  5,
                  8,
                  4,
                  2,
                  1,
                  6,
                  3,
                  7,
                  9,
                  10,
                  5,
                  8,
                  4,
                  2,
                ];
                var parity = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2];
                var code = val.substring(17);
                if (p.test(val)) {
                  var sum = 0;
                  for (var i = 0; i < 17; i++) {
                    sum += val[i] * factor[i];
                  }
                  if (parity[sum % 11] == code.toUpperCase()) {
                    return true;
                  }
                }
                return false;
              };
              var checkDate = function (val) {
                var pattern = /^(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)$/;
                if (pattern.test(val)) {
                  var year = val.substring(0, 4);
                  var month = val.substring(4, 6);
                  var date = val.substring(6, 8);
                  var date2 = new Date(year + "-" + month + "-" + date);
                  if (date2 && date2.getMonth() == parseInt(month) - 1) {
                    return true;
                  }
                }
                return false;
              };
              var checkProv = function (val) {
                var pattern = /^[1-9][0-9]/;
                var provs = {
                  11: "北京",
                  12: "天津",
                  13: "河北",
                  14: "山西",
                  15: "内蒙古",
                  21: "辽宁",
                  22: "吉林",
                  23: "黑龙江 ",
                  31: "上海",
                  32: "江苏",
                  33: "浙江",
                  34: "安徽",
                  35: "福建",
                  36: "江西",
                  37: "山东",
                  41: "河南",
                  42: "湖北 ",
                  43: "湖南",
                  44: "广东",
                  45: "广西",
                  46: "海南",
                  50: "重庆",
                  51: "四川",
                  52: "贵州",
                  53: "云南",
                  54: "西藏 ",
                  61: "陕西",
                  62: "甘肃",
                  63: "青海",
                  64: "宁夏",
                  65: "新疆",
                  71: "台湾",
                  81: "香港",
                  82: "澳门",
                };
                if (pattern.test(val)) {
                  if (provs[val]) {
                    return true;
                  }
                }
                return false;
              };
              if (checkCode(value)) {
                var date = value.substring(6, 14);
                if (checkDate(date)) {
                  if (checkProv(value.substring(0, 2))) {
                    callback();
                  }
                }
              } else {
                callback(new Error("请输入正确的身份证号码"));
                return false;
              }
            },
          }
        ],
        telephone: [
          { required: true, message: '电话号码不能为空', trigger: 'blur' },
          {
           pattern: /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/,
            message: '请输入正确的电话号码',
          },
        ],
        address: [
          { required: true, message: '地址不能为空', trigger: 'blur' }
        ],
        realName: [
          { required: true, message: '真实姓名不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '电子邮箱不能为空', trigger: 'blur' },
          {
            type: 'email',
            message: '请输入正确格式的电子邮箱地址',
            trigger: ['blur', 'change'],
          },
        ]
      },
      loginStatus:false,
      address:""
    }
  },
  mounted() {

  },
  watch:{
      formModel:{
          deep:true,
          immeediate:true,
          handler:function(value) {
            if(value.idcard.length==18){
                let birthday=value.idcard.substring(6,10)+"/"+value.idcard.substring(10,12)+"/"+value.idcard.substring(12,14)
                this.formModel.age = this.$moment().diff(this.$moment(birthday), 'years')
            }
          }
      },
      
  },
  methods: {
    back(){
        this.$router.push({name:"login"})
    },
    register(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.formModel.password==this.formModel.passwordSuccess){
          var data={data:{
              userName:this.formModel.userName,
              password:this.formModel.password,
              idcard:this.formModel.idcard,
              path:this.formModel.path,
              telephone:this.formModel.telephone,
              email:this.formModel.email,
              address:this.address+this.formModel.address,
              gender:this.formModel.gender,
              age:parseInt(this.formModel.age),
              realName:this.formModel.realName,
            }}
          this.$axios.post('/user/register', data).then(resp => {
            this.$message({
                showClose:true,
                message:"注册成功，请登录",
                type:"success"
            })
            this.$router.push({name:"main"})
          })
          }
          else{
            this.$message({
                  showClose:true,
                  message:"密码验证不通过，请重新输入",
                  type:"warning"
              })
              this.formModel.password=""
              this.formModel.passwordSuccess=""
          }
        }
      })
   },
   handleChange (value) {
       var codeStr=value[0]+","+value[1]+","+value[2]
       var codeArray=[value[0],value[1],value[2]]
       this.address=this.getCodeToText(codeStr,codeArray)
    },
    getCodeToText(codeStr, codeArray) {
      if (null === codeStr && null === codeArray) {
        return null;
      } else if (null === codeArray) {
        codeArray = codeStr.split(",");
      }

      let area = "";
      switch (codeArray.length) {
        case 1:
          area += CodeToText[codeArray[0]];
          break;
        case 2:
          area += CodeToText[codeArray[0]] + CodeToText[codeArray[1]];
          break;
        case 3:
          area +=
            CodeToText[codeArray[0]] +
            CodeToText[codeArray[1]] +
            CodeToText[codeArray[2]];
          break;
        default:
          break;
      }
      return area;
    },
    passwordSuccess(){
        if(this.formModel.passwordSuccess!=""&&this.formModel.password!=""){
            if(this.formModel.passwordSuccess==this.formModel.password){
                this.$message({
                    showClose:true,
                    message:"密码验证通过",
                    type:"success"
                })
            }else if(this.formModel.passwordSuccess.length>=this.formModel.password.length&&this.formModel.passwordSuccess!=this.formModel.password){
                this.$message({
                    showClose:true,
                    message:"密码不相同，请重新输入",
                    type:"warning"
                })
                this.formModel.passwordSuccess=""
                this.formModel.password=""
            }
        }  
    },
     upload (item) {
        const fileObj = item.file
        // FormData 对象
        const form01 = new FormData()
        // 文件对象
        form01.append('file', fileObj)
        this.$axios.post('/upload/uploadPhoto', form01).then(resp => {
          this.formModel.path=resp.data.data
          this.$message({
            showClose:true,
            message:"上传成功",
            type:"success"
          })
        })
      }
    
  },
};
</script>

<style scoped>
.zhuye{
  /* background-image:url("http://localhost:8090/static/img/zhuce.jpg") ; */
  padding-top:20px;
  background-color:antiquewhite;
    width:1350px;
    height:620px
}
.login{
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
.register-font{
    margin-top:15px;
    font-weight: 300;
    font-size: 30px;
}
.avatar-uploader .el-upload {
    border: 1px solid black;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 178px;
  }
  .avatar {
    float:left;
    width: 178px;
    height: 178px;
    display: block;
  }

  .avatar_div {
     float:left;
    width: 178px;
    height: 178px;
    border: 1px solid lightgray;
    background-color:white;
  }

  >>>.el-upload{
    float:left;
    padding-left:30px ;
  }
</style>