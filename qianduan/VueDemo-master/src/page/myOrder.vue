<template>
  <div>
    <headermenu :aStyle1="aStyle1" :aStyle2="aStyle2" :aStyle3="aStyle3" :aStyle4="aStyle4">
        <el-row style="margin-top:30px;width:100%;background-color:lightblue">
          <el-col :span="4">
              <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
          </el-col>
          <el-col :span="4">
              <div style="float:left"><el-image style="height:70px" src="../../static/img/myOrder.png"></el-image></div>
          </el-col>
        </el-row>
        <el-row style="margin-top:30px">
             <el-input placeholder="输入订单号" style="width:50%;" v-model="orderNum"><template  slot="append"><el-button icon="el-icon-search" @click="searchButton"></el-button></template></el-input>
        </el-row>
        <el-card v-for="(item,index) in orderData" :key="index" style="margin:50px;">
            <el-row>
                <el-col :span="24">
                    <span style="float:left;font-size:24px">
                        订单编号：{{item.orderNum}}
                    </span>
                </el-col>
            </el-row>
            <el-row  v-for="(item2,index2) in item.goodList2" :key="index2">
                <div style="cursor:pointer" @click="clickImg(item2.goodId)">
                    <el-col :span="5">
                        <div style="float:left">
                            <el-image style="width:200px;height:200px;margin-top:30px;" :src="item2.goodPath" />
                        </div>
                    </el-col>
                    <el-col :span="19" style="margin-top:30px;">
                        <el-row>
                            <el-col :span="6">
                                <span style="float:left;font-size:24px;font-weight:900">{{item2.goodName}}</span>
                            </el-col>
                            <el-col :span="18">
                                <span style="float:right;font-size:24px;font-weight:400">￥{{item2.goodPrice}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="margin-top:30px">
                            <el-col :span="18">
                                <span style="float:left;font-size:24px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">{{item2.goodDescription}}</span>
                            </el-col>
                            <el-col :span="6">
                                <span style="float:right;font-size:24px;font-weight:400">x{{item2.goodNum}}</span>
                            </el-col>
                        </el-row>
                        <el-row style="margin-top:30px">
                            <el-col :span="24">
                                <div style="float:right">
                                    <el-button style="border-radius:15px" v-if="!item2.commentFlag" @click.stop="comment(item2.goodId,true,item.orderNum)">评论</el-button>
                                    <el-button style="border-radius:15px" v-if="item2.commentFlag" @click.stop="comment(item2.goodId,false)">查看评论</el-button>
                                </div>
                            </el-col>
                        </el-row>
                    </el-col>
                </div>
            </el-row>
            <el-row style="margin-top:20px">
                <el-col :span="24">
                    <div style="float:right">
                        <span style="font-size:24px;color:grey">总价：￥{{item.money+item.preferentialAmount}}，优惠：￥{{item.preferentialAmount}}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="font-size:24px;font-weight:900">实付款：￥{{item.money}}</span>
                    </div>
                </el-col>
            </el-row>
            <el-row style="margin-top:20px">
                <el-col :span="24">
                    <div style="float:right">
                        <el-button style="border-radius:15px" @click="addShopping(item.goodList)">加入购物车</el-button>
                        <el-button style="border-radius:15px" @click="deleteOrder(item.orderId)">删除订单</el-button>
                    </div>
                </el-col>
            </el-row>
        </el-card>
    </headermenu>
    <el-dialog
            title="商品评论"
            :visible.sync="visible"
            width="30%"
            >
            <el-input type="textarea" v-model="userComment"/>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="commentConfirm">确 定</el-button>
            </span>
    </el-dialog>
    </div>
</template>

<script>
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "myOrder",
  data() {
    return {
      orderData:[],
       aStyle1:"color:orange",
        aStyle2:"",
        aStyle3:"",
        aStyle4:"",
        orderNum:"",
        goodData:[],
        goodList:[],
        visible:false,
        userComment:"",
        goodId:0,
        orderNum:""
    }
  },
  mounted() {
      this.searchOrder({data:{userId:this.$cookies.get("userId")}});
  },
  methods: {
    back(){
        this.$router.push({name:"main"})
    },
    searchOrder(sendData){
        this.$axios.post('/order/search',sendData).then(resp => {
            var orderData = resp.data.data;
            orderData.forEach((item,index)=>{
                var goodData = item.goodList;
                var goodList=[]
                goodData.forEach((item2,index2)=>{
                    var sendData={data:{flag:0,goodId:item2.goodId,userId:this.$cookies.get("userId")}}
                    this.$axios.post('/goods/search',sendData).then(resp => {
                        var flag=0;
                        resp.data.data[0].commendList.forEach((item3,index3)=>{
                            if(item3.flag==1&&item3.orderNum==item.orderNum){
                                flag=1
                            }
                        })
                        if(flag==1){
                             var data={  commentFlag:true,
                                            goodId:resp.data.data[0].goodId,
                                            goodPath:resp.data.data[0].goodPath,
                                            goodName:resp.data.data[0].goodName,
                                            goodPrice:resp.data.data[0].goodPrice,
                                            goodDescription:resp.data.data[0].goodDescription,
                                            goodNum:item2.goodNum
                                         }
                        }else{
                            var data={  commentFlag:false,
                                            goodId:resp.data.data[0].goodId,
                                            goodPath:resp.data.data[0].goodPath,
                                            goodName:resp.data.data[0].goodName,
                                            goodPrice:resp.data.data[0].goodPrice,
                                            goodDescription:resp.data.data[0].goodDescription,
                                            goodNum:item2.goodNum
                                         }
                        }
                        goodList.push(data)
                    })
                })
                item.goodList2=goodList
            })
            this.orderData = orderData
        })
    },
    searchButton(){
        var sendData={data:{orderNum:this.orderNum,userId:this.$cookies.get("userId")}}
        this.searchOrder(sendData)
    },
    comment(goodid,boolean,orderNum){
      if(!boolean){
           this.$router.push({name:"goodDetail",query:{goodId:goodid}})
      }else{
          this.goodId=goodid
          this.orderNum=orderNum
          this.visible=true
      }
    },
    clickImg(goodid){
      this.$router.push({name:"goodDetail",query:{goodId:goodid}})
    },
    deleteOrder(orderId){
         this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('/order/delete',{data:{orderId:orderId}}).then(resp => {
                this.$message({
                    showClose:true,
                    message:"删除订单成功",
                    type:"success"
                })
                this.searchOrder({data:{userId:this.$cookies.get("userId")}});
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      
    },
    addShopping(goodList){
        goodList.forEach((item,index)=>{
            this.$axios.post('/shopping/add',{data:{goodId:item.goodId,userId:this.$cookies.get("userId")}}).then(resp => {
               
            })
        })
         this.$message({
            showClose:true,
            message:"添加购物车成功",
            type:"success"
        })
   },
   commentConfirm(){
       this.$axios.post('/goods/addCommend',{data:{goodId:this.goodId,userId:this.$cookies.get("userId"),orderNum:this.orderNum,comment:this.userComment}}).then(resp => {
            this.$message({
                showClose:true,
                message:"评论成功",
                type:"success"
            })
            this.searchOrder({data:{userId:this.$cookies.get("userId")}});
            this.visible=false
        })
   },
   cancel(){
       this.visible=false
       this.userComment=""
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
</style>