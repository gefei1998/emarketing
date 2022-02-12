<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px">
                    <el-row>
                        <el-col :span="7">
                            <span>订单号:</span>
                            <el-input style="width:180px" v-model="orderNum"></el-input>
                        </el-col>
                        <el-col :span="5">
                            <span>用户名:</span>
                            <el-input style="width:120px" v-model="userName"></el-input>
                        </el-col>
                        
                        <el-col :span="12">
                            <span>订单时间:</span>
                            <el-date-picker
                            v-model="orderTime"
                            type="datetimerange"
                            :picker-options="pickerOptions"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            align="right"
                            style="width:385px">
                            </el-date-picker>
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
                        <el-table
                            :data="props.row.goodList"
                            style="width: 100%">
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
                            label="商品价格"
                            prop="goodPrice">
                            <template slot-scope="scope">
                                <span v-if="scope.row.goodPrice.toString().split('.').length==1">{{ scope.row.goodPrice}}.00元</span>
                                <span v-if="scope.row.goodPrice.toString().split('.').length==2&&scope.row.goodPrice.toString().split('.')[1].length==1">{{ scope.row.goodPrice }}0元</span>
                                <span v-if="scope.row.goodPrice.toString().split('.').length==2&&scope.row.goodPrice.toString().split('.')[1].length==2">{{ scope.row.goodPrice }}元</span>
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
                            label="购买数量"
                            prop="goodNum">
                            </el-table-column>
                        </el-table>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="订单编号"
                    prop="orderNum" width="170px">
                    </el-table-column>
                    <el-table-column
                    label="用户名"
                    prop="userName">
                    </el-table-column>
                    <el-table-column
                    label="交易额"
                    prop="money">
                   <template slot-scope="scope">
                        <span v-if="scope.row.money.toString().split('.').length==1">{{ scope.row.money}}.00元</span>
                        <span v-if="scope.row.money.toString().split('.').length==2&&scope.row.money.toString().split('.')[1].length==1">{{ scope.row.money }}0元</span>
                        <span v-if="scope.row.money.toString().split('.').length==2&&scope.row.money.toString().split('.')[1].length==2">{{ scope.row.money }}元</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="所获积分"
                    prop="integral">
                    </el-table-column>
                    <el-table-column
                    label="参与的活动"
                    prop="activeName">
                    </el-table-column>
                    <el-table-column
                    label="优惠金额"
                    prop="preferentialAmount">
                    <template slot-scope="scope">
                        <span v-if="scope.row.preferentialAmount.toString().split('.').length==1">{{ scope.row.preferentialAmount}}.00元</span>
                        <span v-if="scope.row.preferentialAmount.toString().split('.').length==2&&scope.row.preferentialAmount.toString().split('.')[1].length==1">{{ scope.row.preferentialAmount }}0元</span>
                        <span v-if="scope.row.preferentialAmount.toString().split('.').length==2&&scope.row.preferentialAmount.toString().split('.')[1].length==2">{{ scope.row.preferentialAmount }}元</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="订单时间"
                    prop="orderTime" width="150px">
                     <template slot-scope="scope">
                        <span>{{scope.row.orderTime.split('.')[0]}}</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                        fixed="right"
                        label="操作"
                        width="120">
                        <template slot-scope="scope">
                                <el-button
                                    @click="detailOrder(scope.row)"
                                    slot="reference"
                                    type="text"
                                    size="small"
                                >
                                    详细
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

        <div v-if="detailVisible">
            <el-dialog title="订单详情"  :visible.sync="detailVisible" width="30%">
                    <el-form style="margin-top:30px;background-color: #F0F8FF;padding-top:10px" :model="formModel" status-icon label-width="100px">
                        <el-row v-for="(item,index) in formModel.goodList" :key="index">
                            <el-col :span="8">
                                <el-image 
                                    style="width: 80px; height: 80px；float:left"
                                    :src="item.goodPath" 
                                    :preview-src-list="[item.goodPath]">
                                </el-image>
                            </el-col>
                            <el-col :span="16">
                                <el-row>
                                    <el-col :span="16">
                                        <span  style="float:left">{{item.goodName}}</span>
                                    </el-col>
                                    <el-col :span="8" >
                                        <span style="float:right;padding-right:5px" v-if="item.goodPrice.toString().split('.').length==1">￥{{item.goodPrice}}.00</span>
                                        <span style="float:right;padding-right:5px" v-if="item.goodPrice.toString().split('.').length==2&&item.goodPrice.toString().split('.')[1].length==1">￥{{item.goodPrice}}0</span>
                                        <span style="float:right;padding-right:5px" v-if="item.goodPrice.toString().split('.').length==2&&item.goodPrice.toString().split('.')[1].length==2">￥{{item.goodPrice}}</span>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="24">
                                        <span style="float:right;padding-right:5px">x{{item.goodNum}}</span>
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <span>商品总价：</span>
                            </el-col>
                            <el-col :span="16">
                                <span style="float:right;padding-right:5px" >￥{{data[0].totalMoney}}</span>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <span>优惠金额：</span>
                            </el-col>
                            <el-col :span="16">
                                <span style="float:right;padding-right:5px;color:orangered">-￥{{data[0].preferentialAmount}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="margin-top:10px">
                            <el-col :span="24">
                                <span style="float:right;padding-right:5px;">
                                    <span style="font-size:16px;color:#000000"><b>实付款</b></span><span style="color:orangered">￥{{data[0].money}}</span>
                                </span>
                            </el-col>
                        </el-row>

                        <el-row style="margin-top:10px">
                            <el-col :span="24">
                                <el-row>
                                    <el-col :span="3"><div class="integral">积分</div></el-col>
                                    <el-col :span="7">
                                        获得
                                        <span style="color:red">
                                        {{formModel.integral}}
                                        </span>
                                        积分
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>

                        <el-row style="margin-top:20px">
                            <el-col :span="24">
                                <span style="float:left;font-size:16px;margin-left:5px"><b>订单信息</b></span>
                            </el-col>
                        </el-row>

                        <el-row style="margin-top:10px">
                            <el-col :span="8">
                                <span style="float:left;margin-left:5px">收获信息:</span>
                            </el-col>
                            <el-col :span="16">
                                <span style="float:left;text-align:left">{{formModel.realName}},{{formModel.telephone}},{{formModel.address}}</span>
                            </el-col>
                        </el-row>

                        <el-row style="margin-top:10px;padding-bottom:10px">
                            <el-col :span="8">
                                <span style="float:left;margin-left:5px">订单编号:</span>
                            </el-col>
                            <el-col :span="12">
                                <span style="float:left;text-align:left">{{formModel.orderNum}}</span>
                            </el-col>
                            <el-col :span="4">
                                <div style="float:right;padding-right:5px;color:orangered;cursor:pointer" type="button" class="testCopy" @click="copyOrderNumClick()">复制</div>
                            </el-col>
                        </el-row>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="detailVisible = false">取 消</el-button>
                    </span>
            </el-dialog>
        </div>
  </div>
</template>

<script>
import Clipboard from 'clipboard'
import sidebar from "@/components/menu"
export default {
  name: 'orderInfoView',
  data () {
    return {
        test:"",
      activeIndex:"6-1",
      orderTime:"",
      orderNum:"",
      userName:"",
      tableData: [],
      formModel:{},
       pageTotal:0,
       page:1,
       pageNum:3,
       pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        detailVisible:false,
        data:[
            {
                preferentialAmounty:"",
                totalMoney:"",
                money:""
            }
        ],
        copyOrderNum:""
    }
  },
  mounted () {
    this.searchOrder({data:{}});
  },
  methods:{
    searchOrder(sendData){
        this.$axios.post('/order/search',sendData).then(resp => {
            var orderData = resp.data.data;
            this.pageTotal=orderData.length
            this.tableData=[]
            orderData.forEach((item,index)=>{
                if((this.page-1)*this.pageNum-1<index&&index<this.page*this.pageNum){
                    this.tableData.push(item)
                }
            })
        })
    },

    //分页
     handleSizeChange(val) {
         this.pageNum=val
         this.searchOrder({data:{}});
         console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
          this.page=val
          this.searchOrder({data:{}});
          console.log(`当前页: ${val}`);
      },
      searchButton(){
          var sendData={data:{
                                orderNum:this.orderNum,
                                userName:this.userName,
                                startTime:null,
                                endTime:null,
                               
                        }}
            if(this.orderTime!=null&&this.orderTime!=""){
                sendData.data.startTime=this.dateTransformation(this.orderTime[0])
                sendData.data.endTime=this.dateTransformation(this.orderTime[1])
            }
          this.searchOrder(sendData);
      },
      dateTransformation(date){
          let y = date.getFullYear()
            let m = date.getMonth() + 1
            m = m < 10 ? ('0' + m) : m
            let d = date.getDate()
            d = d < 10 ? ('0' + d) : d
            let h =date.getHours()
            h = h < 10 ? ('0' + h) : h
            let M =date.getMinutes()
            M = M < 10 ? ('0' + M) : M
            let s =date.getSeconds()
            s = s < 10 ? ('0' + s) : s
            let dateTime= y + '-' + m + '-' + d + ' ' + h + ':' + M + ':' + s;
            return dateTime;
      },
      detailOrder(tableData){
          this.formModel= JSON.parse(JSON.stringify(tableData))
          if(this.formModel.preferentialAmount.toString().split('.').length==1){
              this.$set(this.data[0],"preferentialAmount",this.formModel.preferentialAmount+".00");
          }else if(this.formModel.preferentialAmount.toString().split('.').length==2&&this.formModel.preferentialAmount.toString().split('.')[1].length==1){
             this.$set(this.data[0],"preferentialAmount",this.formModel.preferentialAmount+"0");
          }else if(this.formModel.preferentialAmount.toString().split('.').length==2&&this.formModel.preferentialAmount.toString().split('.')[1].length==2){
             this.$set(this.data[0],"preferentialAmount",this.formModel.preferentialAmount);
          }

          if((this.formModel.preferentialAmount+this.formModel.money).toString().split('.').length==1){
              this.$set(this.data[0],"totalMoney",(this.formModel.preferentialAmount+this.formModel.money)+".00");
          }else if((this.formModel.preferentialAmount+this.formModel.money).toString().split('.').length==2&&(this.formModel.preferentialAmount+this.formModel.money).toString().split('.')[1].length==1){
             this.$set(this.data[0],"totalMoney",(this.formModel.preferentialAmount+this.formModel.money)+"0");
          }else if((this.formModel.preferentialAmount+this.formModel.money).toString().split('.').length==2&&(this.formModel.preferentialAmount+this.formModel.money).toString().split('.')[1].length==2){
             this.$set(this.data[0],"totalMoney",(this.formModel.preferentialAmount+this.formModel.money));
          }

          if(this.formModel.money.toString().split('.').length==1){
              this.$set(this.data[0],"money",this.formModel.money+".00");
          }else if(this.formModel.money.toString().split('.').length==2&&this.formModel.money.toString().split('.')[1].length==1){
             this.$set(this.data[0],"money",this.formModel.money+"0");
          }else if(this.formModel.money.toString().split('.').length==2&&this.formModel.money.toString().split('.')[1].length==2){
             this.$set(this.data[0],"money",this.formModel.money);
          }
          this.detailVisible=true;
      },
       copyOrderNumClick() {
           let txt = this.formModel.orderNum;
            let clipboard = new Clipboard('.testCopy', {
                text: function () {
                return txt
                }
            });
            clipboard.on('success', () => {
                this.$message({
                    showClose:true,
                    message:"复制成功",
                    type:"success"
                })
                clipboard.destroy()
            })
            clipboard.on('error', () => {
                this.$message({
                    showClose:true,
                    message:"复制失败",
                    type:"warning"
                })
                clipboard.destroy()
            })
        }
  },
  components: {sidebar,Clipboard}
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

  .integral{
      border: 1px solid red;
      color: red;
      width: 40px;
      margin-left:5px;
  }
</style>
