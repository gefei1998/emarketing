<template>
  <div>
    <headermenu>
      <el-row style="margin-top:30px;width:100%;background-color:lightblue">
        <el-col :span="4">
            <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
        </el-col>
        <el-col :span="4">
            <div style="float:left"><el-image style="height:70px" src="../../static/img/orderConfirm.png"></el-image></div>
        </el-col>
      </el-row>
       <el-card style="margin:50px">
           <div class="cardBarkground">
            <el-row style="height:50px">
                    <el-col :span="4"><span style="float:left;line-height:50px;font-size:20px;font-weight:900;margin-left:100px">{{realName}}</span></el-col>
                    <el-col :span="4"><span style="float:left;line-height:50px">{{telephone}}</span></el-col>
            </el-row>
            <el-row>
                <el-col :span="24"><span style="float:left;margin-left:100px">{{address}}</span></el-col>
            </el-row>
            <el-row style="margin-top:20px" v-for="(item,index) in goodData" :key="index">
                <div style="cursor:pointer"  @click="clickImg(item.goodId)">
                <el-col :span="6">
                    <el-image 
                        style="width: 100px; height: 100px；float:left"
                        :src="item.goodPath" 
                        :preview-src-list="[item.goodPath]">
                    </el-image>
                </el-col>
                <el-col :span="6">
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
                            <span style="float:right;padding-right:5px">x{{goodIds[index].goodNum}}</span>
                        </el-col>
                    </el-row>
                </el-col>
                </div>
            </el-row>
            <el-row>
                    <el-col :span="5" style="margin-top:50px">
                        <span style="margin-left:25px">商品总价：</span>
                    </el-col>
                    <el-col :span="7" style="margin-top:50px">
                        <span style="float:right;padding-right:5px" >￥{{totalMoney}}</span>
                    </el-col>
            </el-row>
            <el-row>
                <el-col :span="8" style="margin-top:10px">
                    <span style="margin-left:5px">优惠金额：</span><span style="font-size:8px;color:grey">{{activeName}}:满{{fullPrice}}元减{{minusPrice}}元</span>
                </el-col>
                <el-col :span="4" style="margin-top:10px">
                    <span style="float:right;padding-right:5px;color:orangered">-￥{{preferentialAmount}}</span>
                </el-col>
            </el-row>
            <el-row style="margin-top:10px">
                <el-col :span="13">
                    <el-row>
                        <el-col :span="18">
                            <span style="font-size:8px;color:grey;float:right;padding-top:4px">
                                共{{num}}件
                            </span>
                        </el-col>
                        <el-col :span="6">
                            <span style="float:right;padding-right:55px;">
                                <span style="font-size:16px;color:#000000"><b>小计：</b></span><span style="color:orangered">￥{{money}}</span>
                            </span>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>

            <el-row style="margin-top:10px">
                <el-col :span="24">
                    <el-row>
                        <el-col :span="3"><div class="integral">积分</div></el-col>
                        <el-col :span="7" >
                            <span style="float:left">
                                获得
                                <span style="color:red">
                                {{integral}}
                                </span>
                                积分
                            </span>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>

            <el-row style="margin-top:20px;">
                <el-col :span="12" style="margin-left:90px">
                    <span style="float:left;font-size:16px;margin-left:10px"><b>订单信息</b></span>
                </el-col>
            </el-row>

            <el-row style="margin-top:10px">
                <el-col :span="10" style="margin-left:90px">
                    <el-row style="margin-top:10px;padding-bottom:10px">
                        <el-col :span="6">
                            <span style="float:left;margin-left:10px">订单编号:</span>
                        </el-col>
                        <el-col :span="6">
                            <span style="float:left;text-align:left">{{orderNum}}</span>
                        </el-col>
                        <el-col :span="12">
                            <div style="float:right;color:orangered;cursor:pointer" type="button" class="testCopy" @click="copyOrderNumClick()">复制</div>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12" style="margin-top:20px">
                    <el-col :span="18">
                         <span style="float:right;line-height:50px">
                            <span style="font-size:8px;color:grey;padding-top:4px">
                                共{{num}}件，
                            </span>
                            <span style="font-size:16px;color:#000000">合计：</span><span style="color:orangered">￥{{money}}</span>   
                        </span>
                    </el-col>
                    <el-col :span="6">
                        <div style="float:right">
                            <el-button style="background-color:orangered;color:white;border-radius:15px;width:100px" @click="pay">提交订单</el-button>
                        </div>
                    </el-col>
                </el-col>
            </el-row>
            </div>
        </el-card>
    </headermenu>
  </div>
</template>

<script>
import Clipboard from 'clipboard'
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "orderConfirm",
  data() {
    return {
        goodIds:[],
        goodData:[],
       goodId:"",
       userId:"",
       telephone:"",
       address:"",
       realName:"",
       orderNum:"",
       fullPrice:"",
       activeName:"",
       minusPrice:"",
       totalMoney:0,
       preferentialAmount:0,
       money:"",
       integral:"",
       dictionaryData:[],
       num:0,
    }
  },
  mounted() {
    //  this.goodIds=this.$route.query.goodIds
     this.goodIds=this.$cookies.get("goodIds").goodIds
     this.num=this.goodIds.length
     this.goodIds.forEach((item,index)=>{
         this.searchGood({data:{flag:0,goodId:parseInt(item.goodId)}},index)
     })
     this.userId=this.$cookies.get("userId")
     this.$axios.post('/user/detail',{data:{userId:this.userId}}).then(resp => {
        this.realName=resp.data.data[0].realName
        this.address=resp.data.data[0].address
        this.telephone=resp.data.data[0].telephone
      })
      this.$axios.post('/order/getOrderNum').then(resp => {
          this.orderNum=resp.data.data.orderNum
      })
      this.goodData.forEach((item,index)=>{
          this.totalMoney=this.totalMoney+this.goodIds[index].goodNum*item.goodPrice
      })
      this.$axios.post('/active/search',{data:{activeId:this.$cookies.get("adminId")}}).then(resp => {
          this.activeName=resp.data.data[0].activeName
          this.fullPrice=resp.data.data[0].fullPrice
          this.minusPrice=resp.data.data[0].minusPrice
          if(this.totalMoney>=this.fullPrice){
              this.money=this.totalMoney-this.minusPrice
              this.preferentialAmount=this.minusPrice
          }else{
              this.money=this.totalMoney
              this.preferentialAmount=0
          }
          this.$axios.post('/dictionary/search').then(resp => {
            this.dictionaryData=resp.data.data
            this.dictionaryData.forEach((item,index)=>{
              if(item.dictionaryName=="cash_proportion"){
                  this.integral= parseFloat(item.dictionaryValue)*this.money
                  this.integral=Math.round(this.integral)
              }
          })
        })
          
      })
  },
  methods: {
    back(){
        this.$router.push({name:"main"})
    },
   addShopping(){
     this.userId=this.$cookies.get("userId")
     if(this.userId==null||this.userId==""){
       this.$router.push({name:"login"})
       this.$message({
                showClose:true,
                message:"请先登录",
        })
     }else{
       this.$axios.post('/shopping/add',{data:{goodId:this.goodId,userId:this.userId}}).then(resp => {
        this.$message({
                  showClose:true,
                  message:"添加购物车成功",
                  type:"success"
          })
      })
     }
     
   },
    searchGood(sendData,index){
        this.$axios.post('/goods/search',sendData).then(resp => {
            this.goodData.push(resp.data.data[0]);
            this.totalMoney=this.totalMoney+this.goodIds[index].goodNum*resp.data.data[0].goodPrice
        })
    },
     copyOrderNumClick() {
           let txt = this.orderNum;
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
        },
        pay()
        {
            var sendData={
                data:{
                    orderNum:this.orderNum,
                    userId:this.userId,
                    money:this.money,
                    integral:this.integral,
                    activeId:this.$cookies.get("activeId"),
                    preferentialAmount:this.preferentialAmount,
                    goodList:[]
                }
            }
            this.goodData.forEach((item,index)=>{
                var goodListData={goodId:item.goodId,goodNum:this.goodIds[index].goodNum}
                sendData.data.goodList.push(goodListData)
            })
            this.$axios.post('/order/add',sendData).then(resp => {
                
            })

            this.$router.push(
                { name:"pay",    
                query: {  
                    orderNum:this.orderNum,
                    money:this.money,
                    title:"order"
                }
            })
        },
    clickImg(goodid){
      this.$router.push({name:"goodDetail",query:{goodId:goodid}})
    },
  },
};
</script>

<style scoped>
>>> .el-input__inner{
  border: 2px solid red;
}
>>> .el-input-group__append{
  border: 2px solid red;
  background-color: red;
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

  .cardBarkground{
    background-image:url("http://localhost:8090/static/img/order.jpg") ;
    width:100%;
    height:100%
  }
</style>