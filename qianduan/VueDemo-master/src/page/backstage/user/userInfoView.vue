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
                        <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="用户名:">
                            <span>{{ props.row.userName }}</span>
                        </el-form-item>
                        <el-form-item label="身份证号:">
                            <span>{{ props.row.idcard }}</span>
                        </el-form-item>
                        <el-form-item label="电话号码:">
                            <span>{{ props.row.telephone }}</span>
                        </el-form-item>
                        <el-form-item label="电子邮箱:">
                            <span>{{ props.row.email }}</span>
                        </el-form-item>
                        <el-form-item label="地址:">
                            <span>{{ props.row.address }}</span>
                        </el-form-item>
                        <el-form-item label="性别:">
                            <span v-if="props.row.gender==1">男</span>
                            <span v-if="props.row.gender==2">女</span>
                        </el-form-item>
                        <el-form-item label="年龄:">
                            <span>{{ props.row.age }}</span>
                        </el-form-item>
                        <el-form-item label="真实姓名:">
                            <span>{{ props.row.realName }}</span>
                        </el-form-item>
                        </el-form>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="用户名"
                    prop="userName">
                    </el-table-column>
                    <el-table-column
                    label="头像"
                    prop="path">
                    <template slot-scope="scope">
                        <el-image 
                            style="width: 35px; height: 35px;border-radius: 27.5px"
                            :src="scope.row.path" 
                            :preview-src-list="[scope.row.path]">
                        </el-image>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="性别"
                    prop="gender">
                    <template slot-scope="scope">
                        <span v-if="scope.row.gender == 1">男</span>
                        <span v-if="scope.row.gender == 2">女</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="年龄"
                    prop="age">
                    </el-table-column>
                    <el-table-column
                    label="上一次登录时间"
                    prop="loginTime">
                     <template slot-scope="scope">
                        <span>{{scope.row.loginTime.split('.')[0]}}</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                        fixed="right"
                        label="操作"
                        width="120">
                        <template slot-scope="scope">
                            <el-popconfirm title="确定删除该用户吗？" @confirm="deleteRow(scope.row)">
                                <el-button
                                    slot="reference"
                                    type="text"
                                    size="small">
                                    删除
                                </el-button>
                            </el-popconfirm>
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
  </div>
</template>

<script>
import sidebar from "@/components/menu"
export default {
  name: 'userInfoView',
  data () {
    return {
      activeIndex:"1-1",
      tableData: [],
       pageTotal:0,
       page:1,
       pageNum:3,
       userName:""
    }
  },
  mounted () {
    this.searchUser({data:{}});
  },
  methods:{
    searchUser(sendData){
        this.$axios.post('/user/detail',sendData).then(resp => {
            var userData = resp.data.data;
            this.pageTotal=userData.length
            this.tableData=[]
            userData.forEach((item,index)=>{
                if((this.page-1)*this.pageNum-1<index&&index<this.page*this.pageNum){
                    this.tableData.push(item)
                }
            })
        })
    },

    //分页
     handleSizeChange(val) {
         this.pageNum=val
         this.searchUser({data:{}});
         console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
          this.page=val
          this.searchUser({data:{}});
          console.log(`当前页: ${val}`);
      },

      searchButton(){
          this.searchUser({data:{userName:this.userName}});
      },
      deleteRow(tableData){
          this.$axios.post('/user/delete',{data:{userIds:[tableData.userId]}}).then(resp => {
           this.$message({
                showClose:true,
                message:"删除成功",
                type:"success"
            })
            this.searchUser({data:{}});
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
</style>