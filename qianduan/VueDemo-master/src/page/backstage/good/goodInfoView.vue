<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px">
                    <el-row>
                        <el-col :span="5">
                            <span>商品名:</span>
                            <el-input style="width:120px" v-model="goodName"></el-input>
                        </el-col>
                        <el-col :span="7">
                            <span>商品种类:</span>
                            <el-select style="width:170px" collapse-tags v-model="goodType" multiple placeholder="请选择">
                                <el-option
                                v-for="item in goodTypeOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="5">
                            <span>是否启用:</span>
                            <el-select clearable @clear="clear" style="width:100px" v-model="deleteFlag" placeholder="请选择">
                                <el-option
                                v-for="item in deleteFlagOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="7">
                            <span>价格区间:</span>
                            <el-input style="width:80px" v-model="smallPrice"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            ~
                            <el-input style="width:80px" v-model="bigPrice"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
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
                        <el-form-item label="商品名称:">
                            <span>{{ props.row.goodName }}</span>
                        </el-form-item>
                        <el-form-item label="商品价格:">
                            <span v-if="props.row.goodPrice.toString().split('.').length==1">{{ props.row.goodPrice}}.00元</span>
                            <span v-if="props.row.goodPrice.toString().split('.').length==2&&props.row.goodPrice.toString().split('.')[1].length==1">{{ props.row.goodPrice }}0元</span>
                            <span v-if="props.row.goodPrice.toString().split('.').length==2&&props.row.goodPrice.toString().split('.')[1].length==2">{{ props.row.goodPrice }}元</span>
                        </el-form-item>
                        <el-form-item label="销量:">
                            <span>{{ props.row.saleNum }}</span>
                        </el-form-item>
                        <el-form-item label="商品种类:">
                            <span v-if="props.row.goodType == 0">办公设备</span>
                            <span v-if="props.row.goodType == 1">办公耗材</span>
                            <span v-if="props.row.goodType == 2">办公纸类</span>
                            <span v-if="props.row.goodType == 3">笔类</span>
                            <span v-if="props.row.goodType == 4">记事本类</span>
                            <span v-if="props.row.goodType == 5">装订器材</span>
                            <span v-if="props.row.goodType == 6">文件夹类</span>
                            <span v-if="props.row.goodType == 7">办公刀具</span>
                            <span v-if="props.row.goodType == 8">财会用品</span>
                        </el-form-item>
                        <el-form-item label="是否启用:">
                            <span v-if="props.row.deleteFlag==0">启用</span>
                            <span v-if="props.row.deleteFlag==1">禁用</span>
                        </el-form-item>
                        <el-form-item label="商品描述:">
                            <span>{{ props.row.goodDescription }}</span>
                        </el-form-item>
                        <el-form-item label="商品图片:">
                            <el-image 
                                style="width: 100px; height: 100px;"
                                :src="props.row.goodPath" 
                                :preview-src-list="[props.row.goodPath]">
                            </el-image>
                        </el-form-item>
                        </el-form>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="商品名"
                    prop="goodName">
                    </el-table-column>
                    <el-table-column
                    label="商品图片"
                    prop="goodPath">
                    <template slot-scope="scope">
                        <el-image 
                            style="width: 35px; height: 35px；"
                            :src="scope.row.goodPath" 
                            :preview-src-list="[scope.row.goodPath]">
                        </el-image>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="商品种类"
                    prop="goodType">
                    <template slot-scope="scope">
                        <span v-if="scope.row.goodType == 0">办公设备</span>
                        <span v-if="scope.row.goodType == 1">办公耗材</span>
                        <span v-if="scope.row.goodType == 2">办公纸类</span>
                        <span v-if="scope.row.goodType == 3">笔类</span>
                        <span v-if="scope.row.goodType == 4">记事本类</span>
                        <span v-if="scope.row.goodType == 5">装订器材</span>
                        <span v-if="scope.row.goodType == 6">文件夹类</span>
                        <span v-if="scope.row.goodType == 7">办公刀具</span>
                        <span v-if="scope.row.goodType == 8">财会用品</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="商品价格"
                    prop="goodPrice">
                    <template slot-scope="scope">
                        <span v-if="scope.row.goodPrice.toString().split('.').length==1">{{ scope.row.goodPrice}}.00元</span>
                        <span v-if="scope.row.goodPrice.toString().split('.').length==2&&scope.row.goodPrice.toString().split('.')[1].length==1">{{ scope.row.goodPrice }}0元</span>
                        <span v-if="scope.row.goodPrice.toString().split('.').length==2&&scope.row.goodPrice.toString().split('.')[1].length==2">{{ scope.row.goodPrice }}元</span>
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
                                <el-button
                                    @click="viewCommend(scope.row.commendList)"
                                    slot="reference"
                                    type="text"
                                    size="small"
                                >
                                    查看评论
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

        <el-dialog title="编辑商品" :visible.sync="updateVisible">
            <el-form style="margin-top:30px;background-color: #F0F8FF;" :model="formModel" status-icon :rules="rules" ref="ruleForm" label-width="100px">
                     <el-row>
                        <el-col :span="12">
                            <el-form-item label="商品名称" prop="goodName">
                                <el-input v-model="formModel.goodName" style="width:90%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="商品价格" prop="goodPrice">
                                <el-input v-model="formModel.goodPrice" style="width:90%"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            </el-form-item>
                        </el-col>
                     </el-row>
                     <el-row>
                          <el-col :span="12">
                            <el-form-item label="商品种类" prop="goodType">
                                <el-select v-model="formModel.goodType" style="width:90%" placeholder="请选择">
                                    <el-option
                                    v-for="item in goodTypeOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    </el-option>
                                </el-select>
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
                         <el-col :span="24">
                            <el-form-item label="商品描述" prop="goodDescription">
                                <el-input type="textarea" v-model="formModel.goodDescription" style="width:96%"></el-input>
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
                </el-form>
                 <span slot="footer" class="dialog-footer">
                    <el-button @click="updateVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirm('ruleForm')">确 定</el-button>
                </span>
        </el-dialog>


        <el-dialog title="评论列表" :visible.sync="commentVisible" style="height: 500px;overflow:auto;overflow-x:auto;overflow-y:auto;">
            <div v-for="o in commendList" :key="o.commendId" class="box-dev">
                <el-row style="background-color: #F0F8FF;">
                    <el-col :span="12" style="padding:5px;">
                        <img :src="o.userPath" style="width: 25px; height: 25px;border-radius: 12.5px;float:left;" />
                        <span style="color:#7a7e83;float:left;margin-left:5px">{{o.userName}}</span>
                    </el-col>
                    <el-col :span="12" style="padding:5px;">
                        <span style="color:#7a7e83;float:right">{{o.commendTime.split('.')[0]}}</span>
                    </el-col>
                </el-row>
                <el-row style="background-color: #F0F8FF;">
                    <el-col :span="24">
                        <span style="float:left;margin-top:5px;margin-left:10px;margin-bottom:10px">{{o.comment}}</span>
                    </el-col>
                </el-row>
                <el-collapse v-if="o.reply!=''">
                    <el-collapse-item title="商家回复">
                        <span style="float:left;">{{o.reply}}{{o.replyTime}}</span>
                        <el-button @click="deleteReply(o.commendId)">删除</el-button>
                    </el-collapse-item>
                </el-collapse>
                <div v-else style="float:right;cursor:pointer" @click="reply(o.commendId)">回复</div>
                <el-divider><i class="el-icon-chat-round"></i></el-divider>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="commentVisible = false">取 消</el-button>
            </span>
        </el-dialog>

        <el-dialog
            title="商家回复"
            :visible.sync="replyVisible"
            width="30%"
            >
            <el-input type="textarea" v-model="replyComment"/>
            <span slot="footer" class="dialog-footer">
                <el-button @click="replyVisible = false">取 消</el-button>
                <el-button type="primary" @click="replyConfirm">确 定</el-button>
            </span>
        </el-dialog>

  </div>
</template>

<script>
import sidebar from "@/components/menu"
export default {
  name: 'goodInfoView',
  data () {
    return {
      activeIndex:"3-1",
      tableData: [],
       pageTotal:0,
       page:1,
       pageNum:3,
       goodName:"",
       goodType:[],
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
            goodName: [
            { required: true, message: '商品名称不能为空', trigger: 'blur' }
            ],
            goodPrice: [
            { required: true, message: '商品价格不能为空', trigger: 'blur' }
            ],
            goodType: [
            { required: true, message: '商品种类不能为空', trigger: 'blur' }
            ],
            deleteFlag: [
            { required: true, message: '请选择是否启用', trigger: 'blur' }
            ],
            goodDescription: [
            { required: true, message: '商品描述不能为空', trigger: 'blur' },
            ],
            goodPath: [
            { required: true, message: '商品图片不能为空', trigger: 'blur' }
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
    this.searchGood({data:{flag:1}});
  },
  methods:{
    searchGood(sendData){
        this.$axios.post('/goods/search',sendData).then(resp => {
            var goodData = resp.data.data;
            this.pageTotal=goodData.length
            this.tableData=[]
            goodData.forEach((item,index)=>{
                if((this.page-1)*this.pageNum-1<index&&index<this.page*this.pageNum){
                    this.tableData.push(item)
                }
            })
        })
    },

    //分页
     handleSizeChange(val) {
         this.pageNum=val
         this.searchGood({data:{flag:1}});
         console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
          this.page=val
          this.searchGood({data:{flag:1}});
          console.log(`当前页: ${val}`);
      },

      searchButton(){
          var sendData={data:{
                                flag:1,
                                goodType:null,
                                goodName:this.goodName,
                                smallPrice:null,
                                bigPrice:null,
                                deleteFlag:this.deleteFlag
                        }}
          if(this.goodType.length!=0){
              sendData.data.goodType=this.goodType
          }
          if(this.smallPrice!=""){
              sendData.data.smallPrice=this.smallPrice
          }
          if(this.bigPrice!=""){
              sendData.data.bigPrice=this.bigPrice
          }
          this.searchGood(sendData);
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
          this.formModel.goodPath=resp.data.data
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
                    goodId:this.formModel.goodId,
                    goodName:this.formModel.goodName,
                    goodPath:this.formModel.goodPath,
                    goodPrice:this.formModel.goodPrice,
                    goodType:this.formModel.goodType,
                    deleteFlag:this.formModel.deleteFlag,
                    goodDescription:this.formModel.goodDescription
                }}
                this.$axios.post('/goods/update', data).then(resp => {
                    this.$message({
                        showClose:true,
                        message:"修改成功",
                        type:"success"
                    })
                    this.updateVisible=false
                    this.searchGood({data:{flag:1}});
                    
                })
            }
      })
   },
   viewCommend(commendList){
       if(commendList.length==0){
           this.$message({
                showClose:true,
                message:"该商品暂无评论",
                type:"warning"
            })
       }else{
           this.commentVisible=true
           this.commendList=commendList
       }
   },
   reply(commendId){
       this.replyVisible=true
       this.commendId=commendId
   },
   replyConfirm(){
       this.$axios.post('/goods/addCommendReply', {data:{replyFlag:1,commendId:this.commendId,reply:this.replyComment}}).then(resp => {
            this.$message({
                showClose:true,
                message:"回复成功",
                type:"success"
            })
            this.replyVisible=false
            this.searchGood({data:{flag:1}});
            this.commentVisible=false
        })
    },
    deleteReply(commendId){
        this.$axios.post('/goods/addCommendReply', {data:{replyFlag:2,commendId:commendId}}).then(resp => {
            this.$message({
                showClose:true,
                message:"删除成功",
                type:"success"
            })
            this.searchGood({data:{flag:1}});
            this.commentVisible=false
            this.replyComment=""
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
