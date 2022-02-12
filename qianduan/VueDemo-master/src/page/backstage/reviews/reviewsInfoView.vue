<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px">
                    <span>用户名:</span>
                    <el-input style="width:200px" v-model="userName"></el-input>
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
                        <el-form label-position="left" inline class="demo-table-expand" v-if="props.row.reply!=''">
                            <el-form-item label="商家回复:" >
                                <span>{{ props.row.reply }}</span>
                            </el-form-item>
                        </el-form>
                         <span v-else style="color:red">您还没有回复该评论</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="用户名"
                    prop="userName">
                    </el-table-column>
                    <el-table-column
                    label="头像"
                    prop="userPath">
                    <template slot-scope="scope">
                        <el-image 
                            style="width: 35px; height: 35px;border-radius: 27.5px"
                            :src="scope.row.userPath" 
                            :preview-src-list="[scope.row.userPath]">
                        </el-image>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="留言内容"
                    prop="reviewsComment">
                    <template slot-scope="scope">
                        <span>{{scope.row.reviewsComment}}</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="留言时间"
                    prop="createTime">
                     <template slot-scope="scope">
                        <span>{{scope.row.createTime.split('.')[0]}}</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                        fixed="right"
                        label="操作"
                        width="120">
                        <template slot-scope="scope">
                            <el-popconfirm v-if="scope.row.reply!=''" title="确定删除该回复吗？" @confirm="deleteRow(scope.row.reviewsId)">
                                <el-button
                                    slot="reference"
                                    type="text"
                                    size="small">
                                    删除回复
                                </el-button>
                            </el-popconfirm>
                            <el-button v-else
                                    slot="reference"
                                    type="text"
                                    size="small" @click="reply(scope.row.reviewsId)">
                                    回复
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
  name: 'reviewsInfoView',
  data () {
    return {
      activeIndex:"5-1",
      tableData: [],
       pageTotal:0,
       page:1,
       pageNum:3,
       userName:"",
       replyVisible:false,
       reviewId:0,
       replyComment:""
    }
  },
  mounted () {
    this.searchReview({data:{}});
  },
  methods:{
    searchReview(sendData){
        this.$axios.post('/reviews/search',sendData).then(resp => {
            var reviewsData = resp.data.data;
            this.pageTotal=reviewsData.length
            this.tableData=[]
            reviewsData.forEach((item,index)=>{
                if((this.page-1)*this.pageNum-1<index&&index<this.page*this.pageNum){
                    this.tableData.push(item)
                }
            })
        })
    },

    //分页
     handleSizeChange(val) {
         this.pageNum=val
         this.searchReview({data:{}});
         console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
          this.page=val
          this.searchReview({data:{}});
          console.log(`当前页: ${val}`);
      },

      searchButton(){
          this.searchReview({data:{userName:this.userName}});
      },
      deleteRow(reviewId){
          this.$axios.post('/reviews/addReviewReply', {data:{replyFlag:2,reviewId:reviewId}}).then(resp => {
           this.$message({
                showClose:true,
                message:"删除成功",
                type:"success"
            })
            this.searchReview({data:{}});
        })
      },
      reply(reviewId){
       this.replyVisible=true
       this.reviewId=reviewId
       this.replyComment=""
    },
      replyConfirm(){
       this.$axios.post('/reviews/addReviewReply', {data:{replyFlag:1,reviewId:this.reviewId,reply:this.replyComment}}).then(resp => {
            this.$message({
                showClose:true,
                message:"回复成功",
                type:"success"
            })
            this.replyVisible=false
            this.searchReview({data:{}});
        })
    },
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
</style>