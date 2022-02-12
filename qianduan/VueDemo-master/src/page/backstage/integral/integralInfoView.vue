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
                    <el-table-column
                    label="用户名"
                    prop="userName">
                    </el-table-column>
                    <el-table-column
                    label="已获积分"
                    prop="integral">
                    </el-table-column>
                    <el-table-column
                    label="可抵扣金额"
                    prop="integral">
                    <template slot-scope="scope">
                        <span>{{scope.row.integral*proportion}}元</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="上一次登录时间"
                    prop="loginTime">
                     <template slot-scope="scope">
                        <span>{{scope.row.loginTime.split('.')[0]}}</span>
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
  name: 'integralInfoView',
  data () {
    return {
      activeIndex:"8-1",
      tableData: [],
       pageTotal:0,
       page:1,
       pageNum:3,
       userName:"",
       proportion:null
    }
  },
  mounted () {
    this.searchUser({data:{}});
    this.searchDictionary();
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
    searchDictionary(){
        this.$axios.post('/dictionary/search').then(resp => {
            resp.data.data.forEach((item,index)=>{
                if(item.dictionaryName=="integral_proportion"){
                    this.proportion=item.dictionaryValue
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