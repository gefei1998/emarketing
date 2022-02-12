<template>
  <div>
    <headermenu>
      <el-row style="margin-top:30px;width:100%;background-color:lightblue">
        <el-col :span="4">
            <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
        </el-col>
        <el-col :span="4">
              <div style="float:left"><el-image style="height:70px" src="../../static/img/goodDetail.png"></el-image></div>
          </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
            <el-image style="width:300px;height:300px;margin-top:50px;margin-left:50px" :src="goodData[0].goodPath" :preview-src-list="[goodData[0].goodPath]" />
        </el-col>
        <el-col :span="18">
            <el-row style="width:90%;margin-top:50px;margin-left:50px;height:50px">
              <el-col>
                <span style="float:left;font-size:30px;font-weight:900">{{goodData[0].goodName}}</span>
              </el-col>
            </el-row>
            <el-row style="width:90%;margin-top:20px;margin-left:50px;height:150px">
              <el-col>
                <span style="float:left;font-size:20px;">{{goodData[0].goodDescription}}</span>
              </el-col>
            </el-row>
            <el-row style="width:90%;margin-left:50px">
              <el-col :span="5">
                <div style="width:100%;height:80px;background-color:#4083FF;line-height:80px;color:white;font-size:30px">￥{{goodData[0].goodPrice}}</div>
              </el-col>
              <el-col :span="5">
                <div style="width:100%;height:80px;background-color:#FFA940;line-height:80px;color:white;font-size:30px">销量：{{goodData[0].saleNum}}</div>
              </el-col>
              <el-col :span="5" style="float:right">
                <div @click="buy" style="width:100%;height:80px;background-color:#4083FF;line-height:80px;color:white;font-size:26px;cursor:pointer">立即购买</div>
              </el-col>
              <el-col :span="5" style="float:right">
                <div @click="addShopping" style="width:100%;height:80px;background-color:#FFA940;line-height:80px;color:white;font-size:26px;cursor:pointer"><i class="el-icon-shopping-cart-1"></i>加入购物车</div>
              </el-col>
            </el-row>
        </el-col>
      </el-row>
      <el-row style="margin-top:30px;margin-left:50px">
        <div v-for="o in goodData[0].commendList" :key="o.commendId">
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
                        <span style="float:left;">{{o.reply}}</span>
                        <span style="float:right;"> {{o.replyTime.toString().split('.')[0]}}</span>
                    </el-collapse-item>
                </el-collapse>
                <el-divider><i class="el-icon-chat-round"></i></el-divider>
            </div>
      </el-row>
    </headermenu>
  </div>
</template>

<script>
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "goodDetail",
  data() {
    return {
       goodData:[{goodPath:""}],
       goodId:"",
       userId:""
    }
  },
  mounted() {
    this.goodId=this.$route.query.goodId
    this.searchGood({data:{flag:0,goodId:this.$route.query.goodId}})
    // console.log(this.$route.query.goodId)
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
    searchGood(sendData){
        this.$axios.post('/goods/search',sendData).then(resp => {
            this.goodData = resp.data.data;
        })
    },
    buy(){
      this.userId=this.$cookies.get("userId")
     if(this.userId==null||this.userId==""){
       this.$router.push({name:"login"})
       this.$message({
                showClose:true,
                message:"请先登录",
        })
     }else{
       this.$router.push({name:"orderConfirm",query:{goodIds:[{goodId:this.goodId,goodNum:1}]}})
       this.$cookies.set("goodIds",{goodIds:[{goodId:this.goodId,goodNum:1}]},60 * 60 * 1)
     }
      
    }
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
</style>