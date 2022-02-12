<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px;padding-top:8px">
                    <span>添加活动</span>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addActive('ruleForm')">添加</el-button>
                </el-col>
            </el-row>
            <el-row>
                 <el-divider><i class="el-icon-circle-plus-outline"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:450px;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-form style="padding-top:5px" :model="formModel" status-icon :rules="rules" ref="ruleForm" label-width="100px">
                     <el-row>
                        <el-col :span="8">
                            <el-form-item label="活动名称" prop="activeName">
                                <el-input v-model="formModel.activeName" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="满减金额" prop="fullPrice">
                                <el-input v-model="formModel.fullPrice" style="width:90%"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="优惠金额" prop="minusPrice">
                                <el-input v-model="formModel.minusPrice" style="width:90%"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="24">
                            <el-form-item label="活动图片" prop="activePath">
                                <el-upload
                                    class="avatar-uploader"
                                    accept=".jpg,.jpeg,.png,.JPG,.JPEG"
                                    action=""
                                    :http-request="upload"
                                    name="file"
                                    :show-file-list="false">
                                    <img v-if="formModel.activePath" :src="formModel.activePath" class="avatar">
                                    <div v-else class="avatar_div"><i class="el-icon-plus avatar-uploader-icon"></i></div>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="24">
                            <el-form-item label="活动描述" prop="activeContent">
                                <el-input :rows="5" type="textarea" v-model="formModel.activeContent" style="width:97%"></el-input>
                            </el-form-item>
                        </el-col>
                     </el-row>
                </el-form>
            </div>
        </sidebar>
        
  </div>
</template>

<script>
import sidebar from "@/components/menu"
export default {
  name: 'goodInfoAdd',
  data () {
    return {
      activeIndex:"4-2",
      formModel:{
        activeName:"",
        activeContent:"",
        fullPrice:"",
        minusPrice:"",
        activePath:""
      },
      rules:
        {
            activeName: [
            { required: true, message: '活动名称不能为空', trigger: 'blur' }
            ],
            activeContent: [
            { required: true, message: '活动描述不能为空', trigger: 'blur' }
            ],
            fullPrice: [
            { required: true, message: '满减金额不能为空', trigger: 'blur' }
            ],
            deleteFlag: [
            { required: true, message: '请选择是否启用', trigger: 'blur' }
            ],
            minusPrice: [
            { required: true, message: '优惠金额不能为空', trigger: 'blur' },
            ],
            activePath: [
            { required: true, message: '活动图片不能为空', trigger: 'blur' }
            ]
        },
    }
  },
  mounted () {
   
  },
  methods:{
     addActive(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
            var data={data:this.formModel}
            this.$axios.post('/active/add', data).then(resp => {
              this.$message({
                  showClose:true,
                  message:"添加成功",
                  type:"success"
              })
              this.$router.push({name:"activeInfoView"})
            })
        }
      })
   },

     upload (item) {
        const fileObj = item.file
        // FormData 对象
        const form01 = new FormData()
        // 文件对象
        form01.append('file', fileObj)
        this.$axios.post('/upload/uploadPhoto', form01).then(resp => {
          this.formModel.activePath=resp.data.data
          this.$message({
            showClose:true,
            message:"上传成功",
            type:"success"
          })
        })
      }
  },
  components: {sidebar}
}
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .el-divider__text{
      background-color:#F0F8FF
  }

  .zhuye{
    width:1350px;
    height:400px
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
    width: 685px;
    height: 100px;
    line-height: 178px;
  }
  .avatar {
    float:left;
    width: 99%;
    height: 178px;
    display: block;
  }

  .avatar_div {
     float:left;
    width: 97%;
    height: 178px;
    border: 1px solid lightgray;
  }

  >>>.el-upload{
    float:left;
    padding-left:14px ;
  }
</style>