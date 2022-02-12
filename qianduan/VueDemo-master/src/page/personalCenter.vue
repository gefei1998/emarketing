<template>
  <div>
    <headermenu :aStyle1="aStyle1" :aStyle2="aStyle2" :aStyle3="aStyle3" :aStyle4="aStyle4">
        <el-row style="margin-top:30px;width:100%;background-color:lightblue">
          <el-col :span="4">
              <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
          </el-col>
          <el-col :span="4">
              <div style="text-align:left;width:100%"><el-image style="height:70px" src="../../static/img/personalCenter.png"></el-image></div>
          </el-col>
        </el-row>
        <el-card  style="margin:50px">
            <el-row>
                <el-col :span="4">
                    <el-image style="height:200px;width:200px;border-radius:100px" :src="userData.path"></el-image>
                    <div style="color:orange;cursor:pointer" @click="upload">更换头像</div>
                </el-col>
                <el-col :span="18">
                    <el-row>
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    用户名：<span v-if="userNameFlag">{{userData.userName}}</span>
                                    <el-input style="width:60%" v-else v-model="userName"></el-input>
                                    <i v-if="userNameFlag" @click="updateParm('userName')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!userNameFlag" @click="updateParmCheck('userName')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!userNameFlag" @click="updateParmClose('userName')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    密码：<span v-if="passwordFlag"><span v-if="seepassword">{{userData.password}}</span><span v-if="!seepassword">{{passwordHidden}}</span></span>
                                    <el-input type="password" style="width:60%" v-else v-model="password"></el-input>
                                    <i v-if="passwordFlag" @click="updateParm('password')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!passwordFlag" @click="updateParmCheck('password')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!passwordFlag" @click="updateParmClose('password')" style="cursor:pointer" class="el-icon-close"/>
                                    <i v-if="passwordFlag" @click="seePassword" style="cursor:pointer" class="el-icon-view"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                    </el-row>

                    <el-row style="margin-top:10px">
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    身份证号：<span v-if="idcardFlag">{{userData.idcard}}</span>
                                    <el-input style="width:60%" v-else v-model="idcard"></el-input>
                                    <i v-if="idcardFlag" @click="updateParm('idcard')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!idcardFlag" @click="updateParmCheck('idcard')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!idcardFlag" @click="updateParmClose('idcard')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    电话号码：<span v-if="telephoneFlag">{{userData.telephone}}</span>
                                    <el-input style="width:60%" v-else v-model="telephone"></el-input>
                                    <i v-if="telephoneFlag" @click="updateParm('telephone')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!telephoneFlag" @click="updateParmCheck('telephone')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!telephoneFlag" @click="updateParmClose('telephone')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                    </el-row>

                    <el-row style="margin-top:10px">
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    电子邮箱：<span v-if="emailFlag">{{userData.email}}</span>
                                    <el-input style="width:60%" v-else v-model="email"></el-input>
                                    <i v-if="emailFlag" @click="updateParm('email')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!emailFlag" @click="updateParmCheck('email')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!emailFlag" @click="updateParmClose('email')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    真实姓名：<span v-if="realNameFlag">{{userData.realName}}</span>
                                    <el-input style="width:60%" v-else v-model="realName"></el-input>
                                    <i v-if="realNameFlag" @click="updateParm('realName')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!realNameFlag" @click="updateParmCheck('realName')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!realNameFlag" @click="updateParmClose('realName')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                    </el-row>

                    <el-row style="margin-top:10px">
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    性别：<span v-if="genderFlag">
                                      <span v-if="userData.gender==1">男</span>
                                      <span v-if="userData.gender==2">女</span>
                                    </span>
                                     <el-select style="width:60%" v-else v-model="gender" placeholder="请选择">
                                        <el-option
                                        v-for="item in genderOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                        </el-option>
                                    </el-select>
                                    <i v-if="genderFlag" @click="updateParm('gender')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!genderFlag" @click="updateParmCheck('gender')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!genderFlag" @click="updateParmClose('gender')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                        <el-col :span="12">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    年龄：<span v-if="ageFlag">{{userData.age}}</span>
                                    <el-input style="width:60%" v-else v-model="age"></el-input>
                                    <i v-if="ageFlag" @click="updateParm('age')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!ageFlag" @click="updateParmCheck('age')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!ageFlag" @click="updateParmClose('age')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                    </el-row>

                    <el-row style="margin-top:10px">
                        <el-col :span="24">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    地址：<span v-if="addressFlag">{{userData.address}}</span>
                                    <el-input type="textarea" style="width:80%" v-else v-model="address"></el-input>
                                    <i v-if="addressFlag" @click="updateParm('address')" style="cursor:pointer" class="el-icon-edit"/>
                                    <i v-if="!addressFlag" @click="updateParmCheck('address')" style="cursor:pointer" class="el-icon-check"/>
                                    <i v-if="!addressFlag" @click="updateParmClose('address')" style="cursor:pointer" class="el-icon-close"/>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                    </el-row>
                    <el-row style="margin-top:10px">
                        <el-col :span="24">
                              <el-row>
                                <el-col :span="24">
                                  <div style="margin-left:20px;text-align:left;width:100%">
                                    积分：<span>{{userData.integral}}</span>
                                  </div>
                                </el-col>
                              </el-row> 
                        </el-col>
                    </el-row>

                    <el-row>
                      <el-button type="primary" @click="save">保存</el-button>
                    </el-row>
                </el-col>
            </el-row>
        </el-card>
    </headermenu>
    <el-dialog
            :visible.sync="visible"
            width="30%"
            >
            <el-upload
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.JPG,.JPEG"
                action=""
                :http-request="uploadConfirm"
                name="file"
                :show-file-list="false">
                <el-image style="height:200px;width:200px;border-radius:100px" v-if="userPath" :src="userPath"/>
                <el-button v-else>点击选择图片</el-button>
            </el-upload>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updatePath">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "personalCenter",
  data() {
    return {
      userData:[],
       aStyle1:"",
        aStyle2:"",
        aStyle3:"color:orange",
        aStyle4:"",
        orderNum:"",
        userPath:"",
        visible:false,
        seepassword:false,
        passwordHidden:"",
        userNameFlag:true,
        passwordFlag:true,
        idcardFlag:true,
        telephoneFlag:true,
        emailFlag:true,
        realNameFlag:true,
        ageFlag:true,
        genderFlag:true,
        addressFlag:true,
        userName:"",
        password:"",
        idcard:"",
        telephone:"",
        realName:"",
        email:"",
        age:"",
        gender:1,
        address:"",
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
    }
  },
  mounted() {
    this.$axios.post('/user/detail',{data:{userId:this.$cookies.get("userId")}}).then(resp => {
        this.userData=resp.data.data[0]
        this.passwordHidden=""
        for(var i=0;i<this.userData.password.length;i++){
          this.passwordHidden=this.passwordHidden+"*"
        }
    })
  },
  methods: {
    back(){
        this.$router.push({name:"main"})
    },
    upload(){
        this.visible=true
    },
    updatePath(){
        this.userData.path=this.userPath
        this.visible=false
    },
    uploadConfirm (item) {
        const fileObj = item.file
        // FormData 对象
        const form01 = new FormData()
        // 文件对象
        form01.append('file', fileObj)
        this.$axios.post('/upload/uploadPhoto', form01).then(resp => {
          this.userPath=resp.data.data
          this.$message({
            showClose:true,
            message:"上传成功",
            type:"success"
          })
        })
      },
    cancel(){
        this.visible=false
        this.userPath=""
    },
    updateParm(parm){
        if(parm=="userName"){
            this.userNameFlag=false
            this.userName=this.userData.userName
        }
        if(parm=="password"){
            this.passwordFlag=false
            this.password=this.userData.password
            this.passwordHidden=""
            for(var i=0;i<this.userData.password.length;i++){
              this.passwordHidden=this.passwordHidden+"*"
            }
        }
        if(parm=="idcard"){
            this.idcardFlag=false
            this.idcard=this.userData.idcard
        }
        if(parm=="telephone"){
            this.telephoneFlag=false
            this.telephone=this.userData.telephone
        }
        if(parm=="email"){
            this.emailFlag=false
            this.email=this.userData.email
        }
        if(parm=="realName"){
            this.realNameFlag=false
            this.realName=this.userData.realName
        }
        if(parm=="age"){
            this.ageFlag=false
            this.age=this.userData.age
        }
        if(parm=="gender"){
            this.genderFlag=false
            this.gender=this.userData.gender
        }
        if(parm=="address"){
            this.addressFlag=false
            this.address=this.userData.address
        }
    },
    updateParmCheck(parm){
        if(parm=="userName"){
            this.userData.userName=this.userName
            this.userNameFlag=true
        }
        if(parm=="password"){
            this.userData.password=this.password
            this.passwordHidden=""
            for(var i=0;i<this.userData.password.length;i++){
              this.passwordHidden=this.passwordHidden+"*"
            }
            this.passwordFlag=true   
        }
        if(parm=="idcard"){
            this.userData.idcard=this.idcard
            this.idcardFlag=true
        }
        if(parm=="telephone"){
            this.userData.telephone=this.telephone
            this.telephoneFlag=true
        }
        if(parm=="email"){
            this.userData.email=this.email
            this.emailFlag=true
        }
        if(parm=="realName"){
            this.userData.realName=this.realName
            this.realNameFlag=true
        }
        if(parm=="age"){
            this.userData.age=this.age
            this.ageFlag=true
        }
        if(parm=="gender"){
            this.userData.gender=this.gender
            this.genderFlag=true
        }
        if(parm=="address"){
            this.userData.address=this.address
            this.addressFlag=true
        }
    },
    updateParmClose(parm){
        if(parm=="userName"){
            this.userNameFlag=true
        }
        if(parm=="password"){
            this.passwordFlag=true
        }
        if(parm=="idcard"){
            this.idcardFlag=true
        }
        if(parm=="telephone"){
            this.telephoneFlag=true
        }
        if(parm=="email"){
            this.emailFlag=true
        }
        if(parm=="realName"){
            this.realNameFlag=true
        }
        if(parm=="age"){
            this.ageFlag=true
        }
        if(parm=="gender"){
            this.genderFlag=true
        }
        if(parm=="address"){
            this.addressFlag=true
        }
    },
    seePassword(){
      if(this.seepassword){
        this.seepassword=false
      }else{
        this.seepassword=true
      }
    },
    save(){
      var data={data:{
              userName:this.userData.userName,
              password:this.userData.password,
              idcard:this.userData.idcard,
              path:this.userData.path,
              telephone:this.userData.telephone,
              email:this.userData.email,
              address:this.userData.address,
              gender:this.userData.gender,
              age:parseInt(this.userData.age),
              realName:this.userData.realName,
              userId:parseInt(this.userData.userId)
            }}
      this.$axios.post('/user/update', data).then(resp => {
          this.$message({
            showClose:true,
            message:"保存成功",
            type:"success"
          })
        })
    }
  },
};
</script>

<style scoped>
>>> .el-input__inner{
  border: 2px solid grey;
}
>>> .el-input-group__append{
  border: 2px solid grey;
  background-color: grey;
  color:white;
  font-size:16px;
  font-weight: 800;
}
.el-carousel{
  /* margin-left:10%; */
  width:90%;
}
.el-carousel__item {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
    
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .typeDiv{
    font-size:16px;
    font-weight: 900;
    padding-top:22px;
    cursor: pointer;
  }

  .fontColor{
    color:orange
  }

.fontColor2{
    color:red
  }

  .integral{
      border: 1px solid red;
      color: red;
      width: 40px;
      margin-left:100px;
  }
  .footer{
    position:fixed;
    width: 92.5%;
    height:50px;
    background-color: white;
    z-index: 999;
    bottom:0px;
    margin-left:50px;
    border-radius: 15px;
    border:1px solid lightgrey;
  }

  >>> .el-dialog__body{
      height:250px
  }
</style>