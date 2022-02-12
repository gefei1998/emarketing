<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px;padding-top:8px">
                    <span>添加商品</span>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addGood('ruleForm')">添加</el-button>
                </el-col>
            </el-row>
            <el-row>
                 <el-divider><i class="el-icon-circle-plus-outline"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:450px;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-form style="padding-top:5px" :model="formModel" status-icon :rules="rules" ref="ruleForm" label-width="100px">
                     <el-row>
                        <el-col :span="8">
                            <el-form-item label="商品名" prop="goodName">
                                <el-input v-model="formModel.goodName" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="商品价格" prop="goodPrice">
                                <el-input v-model="formModel.goodPrice" style="width:90%"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="商品种类" prop="goodType">
                                <el-select clearable v-model="formModel.goodType" style="width:90%" placeholder="请选择">
                                    <el-option
                                    v-for="item in goodTypeOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="8">
                            <el-form-item label="商品图片" prop="goodPath">
                                <el-upload
                                    class="avatar-uploader"
                                    accept=".jpg,.jpeg,.png,.JPG,.JPEG"
                                    action=""
                                    :http-request="upload"
                                    name="file"
                                    :show-file-list="false">
                                    <img v-if="formModel.goodPath" :src="formModel.goodPath" class="avatar">
                                    <div v-else class="avatar_div"><i class="el-icon-plus avatar-uploader-icon"></i></div>
                                </el-upload>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="24">
                            <el-form-item label="商品描述" prop="goodDescription">
                                <el-input :rows="5" type="textarea" v-model="formModel.goodDescription" style="width:97%"></el-input>
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
      activeIndex:"3-2",
      goodTypeOptions: [{
          value: 0,
          label: '办公设备'
        }, {
          value: 1,
          label: '办公耗材'
        }, {
          value: 2,
          label: '办公纸类'
        }, {
          value: 3,
          label: '笔类'
        }, {
          value: 4,
          label: '记事本类'
        },{
          value: 5,
          label: '装订器材'
        }, {
          value: 6,
          label: '文件夹类'
        }, {
          value: 7,
          label: '办公刀具'
        }, {
          value: 8,
          label: '财会用品'
        }],
      formModel:{
        goodName:"",
        goodDescription:"",
        goodPath:"",
        goodPrice:"",
        goodType:"",
      },
      rules:
      {
        goodName: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        goodDescription: [
          { required: true, message: '商品描述不能为空', trigger: 'blur' }
        ],
        goodType: [
          { required: true, message: '请选择商品种类', trigger: 'blur' }
        ],
        goodPrice: [
          { required: true, message: '商品价格不能为空', trigger: 'blur' },
        ],
        goodPath: [
          { required: true, message: '商品图片必须上传', trigger: 'blur' },
        ]
      },
    }
  },
  mounted () {
   
  },
  methods:{
     addGood(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
            var data={data:this.formModel}
            this.$axios.post('/goods/add', data).then(resp => {
              this.$message({
                  showClose:true,
                  message:"添加成功",
                  type:"success"
              })
              this.$router.push({name:"goodInfoView"})
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
          this.formModel.goodPath=resp.data.data
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
  }

  >>>.el-upload{
    float:left;
    padding-left:14px ;
  }
</style>