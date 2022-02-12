<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px">
                    <el-row>
                        <el-col :span="12">
                            <span>活动名:</span>
                            <el-input style="width:200px" v-model="activeName"></el-input>
                        </el-col>
                        <el-col :span="12">
                            <span>是否启用:</span>
                            <el-select clearable @clear="clear" style="width:200px" v-model="deleteFlag" placeholder="请选择">
                                <el-option
                                v-for="item in deleteFlagOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" icon="el-icon-search" @click="searchButton">搜索</el-button>
                </el-col>
            </el-row>
            <el-row>
                 <el-divider><i class="el-icon-search"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:380px;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-table
                    :data="tableData"
                    style="width: 100%">
                    <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="活动名称:">
                            <span>{{ props.row.activeName }}</span>
                        </el-form-item>
                        <el-form-item label="是否启用:">
                            <span v-if="props.row.deleteFlag==0">启用</span>
                            <span v-if="props.row.deleteFlag==1">禁用</span>
                        </el-form-item>
                        <el-form-item label="活动描述:">
                            <span>{{ props.row.activeContent }}</span>
                        </el-form-item>
                        <el-form-item label="活动满减:">
                            <span>满{{ props.row.fullPrice }}元减{{ props.row.minusPrice }}元</span>
                        </el-form-item>
                        <el-form-item label="活动图片:">
                            <el-image 
                                style="width: 200px; height: 100px;"
                                :src="props.row.activePath" 
                                :preview-src-list="[props.row.activePath]">
                            </el-image>
                        </el-form-item>
                        </el-form>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="活动名"
                    prop="activeName">
                    </el-table-column>
                    <el-table-column
                    width="300px"
                    label="活动图片"
                    prop="activePath">
                    <template slot-scope="scope">
                        <el-image 
                            style="width: 200px; height: 35px；display: block;"
                            :src="scope.row.activePath" 
                            :preview-src-list="[scope.row.activePath]">
                        </el-image>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="是否启用"
                    prop="deleteFlag">
                    <template slot-scope="scope">
                        <span v-if="scope.row.deleteFlag == 0">启用</span>
                        <span v-if="scope.row.deleteFlag == 1">禁用</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="更新时间"
                    prop="updateTime">
                     <template slot-scope="scope">
                        <span>{{scope.row.updateTime.split('.')[0]}}</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                        fixed="right"
                        label="操作"
                        width="120">
                        <template slot-scope="scope">
                                <el-button
                                    @click="updateGood(scope.row)"
                                    slot="reference"
                                    type="text"
                                    size="small"
                                >
                                    编辑
                                </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <el-row>
                <el-divider><i class="el-icon-tickets"></i></el-divider>
            </el-row>
            <el-row>
                <el-col>
                    <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="1"
                        :page-sizes="[3, 5, 10]"
                        :page-size="3"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="pageTotal">
                    </el-pagination>
                </el-col>
            </el-row>
        </sidebar>

        <el-dialog title="编辑活动" :visible.sync="updateVisible">
            <el-form style="margin-top:30px;background-color: #F0F8FF;" :model="formModel" status-icon :rules="rules" ref="ruleForm" label-width="100px">
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="活动名称" prop="goodName">
                                <el-input v-model="formModel.activeName" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="是否启用" prop="deleteFlag">
                                <el-select v-model="formModel.deleteFlag" style="width:90%" placeholder="请选择">
                                    <el-option
                                    v-for="item in deleteFlagOptions"
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
                            <el-form-item label="满减金额" prop="fullPrice">
                                <el-input v-model="formModel.fullPrice" style="width:90%"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="优惠金额" prop="minusPrice">
                                <el-input v-model="formModel.minusPrice" style="width:90%"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            </el-form-item>
                        </el-col>    
                     </el-row>
                     <el-row>
                         <el-col :span="24">
                            <el-form-item label="活动描述" prop="activeContent">
                                <el-input type="textarea" v-model="formModel.activeContent" style="width:96%"></el-input>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                        <el-col :span="24">
                            <el-form-item label="活动图片" prop="goodPath">
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
                </el-form>
                 <span slot="footer" class="dialog-footer">
                    <el-button @click="updateVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirm('ruleForm')">确 定</el-button>
                </span>
        </el-dialog>

  </div>
</template>

<script>
import sidebar from "@/components/menu"
export default {
  name: 'activeInfoView',
  data () {
    return {
      activeIndex:"4-1",
      tableData: [],
       pageTotal:0,
       page:1,
       pageNum:3,
       activeName:"",
        smallPrice:null,
        bigPrice:null,
        deleteFlag:null,
        deleteFlagOptions:[{
          value: 0,
          label: '启用'
        }, {
          value: 1,
          label: '禁用'
        }],
        updateVisible:false,
        formModel:{},
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
        commentVisible:false,
        commendList:[],
         replyVisible:false,
         replyComment:"",
         commendId:0,
    }
  },
  mounted () {
    this.searchActive({data:{}});
  },
  methods:{
    searchActive(sendData){
        this.$axios.post('/active/search',sendData).then(resp => {
            var activeData = resp.data.data;
            this.pageTotal=activeData.length
            this.tableData=[]
            activeData.forEach((item,index)=>{
                if((this.page-1)*this.pageNum-1<index&&index<this.page*this.pageNum){
                    this.tableData.push(item)
                }
            })
        })
    },

    //分页
     handleSizeChange(val) {
         this.pageNum=val
         this.searchActive({data:{}});
         console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
          this.page=val
          this.searchActive({data:{}});
          console.log(`当前页: ${val}`);
      },

      searchButton(){
          var sendData={data:{
                                activeName:this.activeName,
                                deleteFlag:this.deleteFlag
                        }}
          this.searchActive(sendData);
      },
      updateGood(tableData){
          this.updateVisible=true
          this.formModel= JSON.parse(JSON.stringify(tableData))
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
      },
      confirm(formName){
        this.$refs[formName].validate((valid) => {
            if (valid) {
                var data={data:{
                    activeId:this.formModel.activeId,
                    activeName:this.formModel.activeName,
                    activeContent:this.formModel.activeContent,
                    fullPrice:this.formModel.fullPrice,
                    minusPrice:this.formModel.minusPrice,
                    deleteFlag:this.formModel.deleteFlag,
                    activePath:this.formModel.activePath
                }}
                this.$axios.post('/active/update', data).then(resp => {
                    this.$message({
                        showClose:true,
                        message:"修改成功",
                        type:"success"
                    })
                    this.updateVisible=false
                    this.searchActive({data:{}});
                    
                })
            }
      })
   },
    clear(){
        this.deleteFlag=null
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
    width: 100%;
    height: 178px;
    display: block;
  }

  .avatar_div {
    float:left;
    width: 100%;
    height: 178px;
    border: 1px solid lightgray;
  }

  >>>.el-upload{
    float:left;
    padding-left:10px ;
  }
  .box-dev {
    margin-top:5px;
    width: 90%;
    height: 200px;
    overflow:auto;
    overflow-x:auto;
    overflow-y:auto;
    margin-left:5%;
  }
  .box-dev >>>.el-divider__text{
      background-color:white
    }

  >>>.el-dialog__header{
      float:left
  }
</style>
