<template>
  <div>
    <headermenu :aStyle1="aStyle1" :aStyle2="aStyle2" :aStyle3="aStyle3" :aStyle4="aStyle4">
      <div v-show="shoppinNotNull">
        <el-row style="margin-top:30px;width:100%;background-color:lightblue">
          <el-col :span="4">
              <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
          </el-col>
          <el-col :span="4">
              <div style="float:left"><el-image style="height:70px" src="../../static/img/shopping.png"></el-image></div>
          </el-col>
        </el-row>
        <el-card v-for="(item,index) in shoppingData" :key="index" style="margin:50px;">
          
          <el-row>
            <el-col :span="2">
                <el-checkbox size="medium" style="line-height:200px;" v-model="item.checked"></el-checkbox>
            </el-col>
            <div @click="clickImg(item.goodId)" style="cursor:pointer;">
            <el-col :span="4">
                <el-image :src="item.goodPath" style="width:100%;height:200px;"></el-image>
            </el-col>
            <el-col :span="18">
                <el-row style="height:170px">
                    <el-col :span="12"><span style="font-size:20px;font-weight:900;float:left;margin-left:50px">{{item.goodName}}</span></el-col>                
                    <el-col :span="12"><div @click.stop="deleteShopping(index)" style="z-index:3;font-size:20px;font-weight:900;float:right;margin-right:50px;cursor:pointer"><i class="el-icon-delete" /></div></el-col>                
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <span v-if="item.goodPrice.toString().split('.').length==1" style="font-size:20px;font-weight:900;float:left;margin-left:50px;color:orange">￥{{item.goodPrice}}.00</span>
                        <span v-if="item.goodPrice.toString().split('.').length==2&&item.goodPrice.toString().split('.')[1].length==1" style="font-size:20px;font-weight:900;float:left;margin-left:50px;color:orange">￥{{item.goodPrice}}0</span>
                        <span v-if="item.goodPrice.toString().split('.').length==2&&item.goodPrice.toString().split('.')[1].length==2" style="font-size:20px;font-weight:900;float:left;margin-left:50px;color:orange">￥{{item.goodPrice}}</span>
                    </el-col>
                    <el-col :span="12">
                        <el-button icon="el-icon-minus" @click.stop="minus(index)" style="margin-right:30px"></el-button>
                        <span style="font-size:20px;">x{{item.goodNum}}</span>
                        <el-button icon="el-icon-plus" @click.stop="plus(index)" style="margin-left:30px"></el-button>
                    </el-col>
                </el-row>
            </el-col>
            </div>
            </el-row>
        </el-card>
        <el-row class="footer">
          <el-col :span="2">
            <el-checkbox size="medium" v-model="checkedAll" style="line-height:50px;margin-left:60px">全选</el-checkbox>
          </el-col>
          <el-col :span="22" style="line-height:50px;">
            <div style="float:right;margin-right:50px">
              <span>合计：</span>
              <span>
                <span style="color:orangered;" v-if="totalMoney.toString().split('.').length==1">￥{{totalMoney}}.00</span>
                <span style="color:orangered;" v-if="totalMoney.toString().split('.').length==2&&totalMoney.toString().split('.')[1].length==1">￥{{totalMoney}}0</span>
                <span style="color:orangered;" v-if="totalMoney.toString().split('.').length==2&&totalMoney.toString().split('.')[1].length==2">￥{{totalMoney}}</span>
                <span style="color:orangered;font-size:8px" v-if="totalMoney!=0">(优惠金额见结算页面)</span>
              </span>
              <el-button style="background-color:orangered;color:white;border-radius:15px;width:100px" @click="pay">{{buttonText}}</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
      <div v-show="!shoppinNotNull">
        <el-row style="margin-top:30px;width:100%;background-color:lightblue">
          <el-col :span="4">
              <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
          </el-col>
           <el-col :span="4">
              <div style="float:left"><el-image style="height:70px" src="../../static/img/shopping.png"></el-image></div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24" style="padding-top:50px;font-size:24px;font-weight:900">
              购物车空空如也,请先加购再查看
          </el-col>
          
        </el-row>
        <el-row style="margin-top:50px">
          <el-divider>猜你喜欢</el-divider>
        </el-row>
        <el-row>
          <el-col v-for="(item,index) in goodData" :key="index" style="width:250px;height:300px;border:solid 1px lightgrey;margin-left:10%;margin-bottom:70px">
            <div style="cursor:pointer" @click="clickImg(item.goodId)">
              <el-image style="width:250px;height:300px" :src="item.goodPath"/>
              <span>{{item.goodName}}</span><br/>
              <span>￥{{item.goodPrice}}</span>
            </div>
          </el-col>
      </el-row>
      </div>
    </headermenu>
  </div>
</template>

<script>
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "shopping",
  data() {
    return {
      goodData:[],
        goodIds:[],
       goodId:"",
       userId:"",
       totalMoney:0,
       integral:"",
       shoppingData:[],
       shoppingData2:[],
       shoppinNotNull:true,
       checkedAll:false,
       buttonText:"结算",
       aStyle1:"",
        aStyle2:"color:orange",
        aStyle3:"",
        aStyle4:""
    }
  },
  mounted() {
     this.$axios.post('/shopping/search',{data:{userId:this.$cookies.get("userId")}}).then(resp => {
         this.shoppingData2=resp.data.data
         if(this.shoppingData2.length==0){
           this.shoppinNotNull=false
         }
         else{
           this.shoppingData2.forEach((item,index)=>{
              var data={checked:false,goodId:item.goodId,goodPath:item.goodPath,goodName:item.goodName,goodPrice:item.goodPrice,goodNum:item.goodNum}
              this.shoppingData.push(data)
          })
         }
      }),
        this.$axios.post('/goods/search',{data:{flag:0}}).then(resp => {
          this.goodData=[]
          resp.data.data.forEach((item,index)=>{
            if(index<6){
              this.goodData.push(item)
            }
          })
        })

  },
   watch:{
      checkedAll:{
          deep:true,
          immeediate:true,
          handler:function(value) {
            if(value){
              this.shoppingData.forEach((item,index)=>{
                item.checked=true
              })
            }else{
              var num=0
              this.shoppingData.forEach((item,index)=>{
                if(item.checked){
                  num=num+1
                }
              })
              if(num!=this.shoppingData.length){

              }else{
                this.shoppingData.forEach((item,index)=>{
                  item.checked=false
                })
              }
            }
          }
      },
      shoppingData:{
          deep:true,
          immeediate:true,
          handler:function(value) {
            this.totalMoney=0
            var num=0
            value.forEach((item,index)=>{
              if(item.checked){
                var price = (item.goodNum*item.goodPrice).toString().split('.')
                if(price.length==1){
                  var price1 = (item.goodNum*item.goodPrice).toString().split('.')[0]
                  var finalprice = price1
                }else{
                  var price1 = (item.goodNum*item.goodPrice).toString().split('.')[0]
                  var price2 = (item.goodNum*item.goodPrice).toString().split('.')[1]
                  if(price2.length<2){
                    var price3 = (item.goodNum*item.goodPrice).toString().split('.')[1].substring(0,1)
                  }else{
                    var price3 = (item.goodNum*item.goodPrice).toString().split('.')[1].substring(0,2)
                  }
                  var finalprice = price1+"."+price3
                }
                this.totalMoney=this.totalMoney+parseFloat(finalprice)
                var totalprice = this.totalMoney.toString().split('.')
                if(totalprice.length==1){
                  var totalprice1 = this.totalMoney.toString().split('.')[0]
                  var finaltotalprice = totalprice1
                }else{
                  var totalprice1 = this.totalMoney.toString().split('.')[0]
                  var totalprice2 = this.totalMoney.toString().split('.')[1]
                  if(totalprice2.length<2){
                    var totalprice3 = this.totalMoney.toString().split('.')[1].substring(0,1)
                  }else{
                    var totalprice3 = this.totalMoney.toString().split('.')[1].substring(0,2)
                  }
                  var finaltotalprice = totalprice1+"."+totalprice3
                }
                this.totalMoney=parseFloat(finaltotalprice)
                num=num+1
              }
            })
            if(num>0){
              this.buttonText="结算("+num+")"
            }else{
              this.buttonText="结算"
            }
            if(num!=value.length){
              this.checkedAll=false
            }else{
              this.checkedAll=true
            }
          },
      }
      
  },
  methods: {
    back(){
        this.$router.push({name:"main"})
    },
    minus(index){
        if(this.shoppingData[index].goodNum==1){
          this.$message({
                showClose:true,
                message:"不能再减少了",
                type:"warning"})
        }else{
          this.shoppingData[index].goodNum=this.shoppingData[index].goodNum-1
          var sendData={data:{goodId:this.shoppingData[index].goodId,userId:this.$cookies.get("userId"),goodNum:this.shoppingData[index].goodNum}}
          this.$axios.post('/shopping/update',sendData).then(resp => {
                  
          })
        }
    },
    plus(index){
        this.shoppingData[index].goodNum=this.shoppingData[index].goodNum+1
        var sendData={data:{goodId:this.shoppingData[index].goodId,userId:this.$cookies.get("userId"),goodNum:this.shoppingData[index].goodNum}}
        this.$axios.post('/shopping/update',sendData).then(resp => {
                
        })
    },
    deleteShopping(index){
        var sendData={data:{goodId:this.shoppingData[index].goodId,userId:this.$cookies.get("userId")}}
        this.$axios.post('/shopping/delete',sendData).then(resp => {
            this.$delete(this.shoppingData,index)
            this.$axios.post('/shopping/search',{data:{userId:this.$cookies.get("userId")}}).then(resp => {
            if(resp.data.data.length==0){
              this.shoppinNotNull=false
            }
          })
        })
    },
    pay(){
        var flag=0
        this.shoppingData.forEach((item,index)=>{
            if(item.checked==true){
                flag=1
            }
        })
        if(flag==0){
                this.$message({
                showClose:true,
                message:"请先选择一个商品再结算",
                })
            }else{
                var goodIdsList=[]
                this.shoppingData.forEach((item,index)=>{
                    if(item.checked==true){
                        var goodIdsData={goodId:item.goodId,goodNum:item.goodNum}
                        goodIdsList.push(goodIdsData)
                    }
                })
               this.$router.push({name:"orderConfirm",query:{goodIds:goodIdsList}})
               this.$cookies.set("goodIds",{goodIds:goodIdsList},60 * 60 * 1)
            }
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