<template>
  <div>
    <headermenu :aStyle1="aStyle1" :aStyle2="aStyle2" :aStyle3="aStyle3" :aStyle4="aStyle4">
        <el-row style="margin-top:30px;width:100%;background-color:lightblue">
          <el-col :span="4">
              <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
          </el-col>
          <el-col :span="4">
              <div style="float:left"><el-image style="height:70px" src="../../static/img/reviews.png"></el-image></div>
          </el-col>
        </el-row>
        <el-row style="margin-top:10px;">
            <div style="float:right;margin-right:100px"><el-button type="primary" @click="reviews" size="small">我要留言</el-button></div>
        </el-row>
       <el-row style="margin-top:30px;margin-left:50px">
        <div v-for="o in messageList" :key="o.reviewsId">
                <el-row style="background-color: #F0F8FF;">
                    <el-col :span="12" style="padding:5px;">
                        <img :src="o.userPath" style="width: 25px; height: 25px;border-radius: 12.5px;float:left;" />
                        <span style="color:#7a7e83;float:left;margin-left:5px">{{o.userName}}</span>
                    </el-col>
                    <el-col :span="12" style="padding:5px;">
                        <span style="color:#7a7e83;float:right">{{o.createTime.split('.')[0]}}</span>
                    </el-col>
                </el-row>
                <el-row style="background-color: #F0F8FF;">
                    <el-col :span="24">
                        <span style="float:left;margin-top:5px;margin-left:10px;margin-bottom:10px">{{o.reviewsComment}}</span>
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
    <el-dialog
            title="网站留言"
            :visible.sync="visible"
            width="30%"
            >
            <el-input type="textarea" v-model="reviewsComment"/>
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
  name: "message",
  data() {
    return {
      messageList:[],
       aStyle1:"",
        aStyle2:"",
        aStyle3:"",
        aStyle4:"color:orange",
        reviewsComment:"",
        visible:false
    }
  },
  mounted() {
    this.$axios.post('/reviews/search',{data:{}}).then(resp => {
        this.messageList=resp.data.data
    })
  },
  methods: {
    back(){
        this.$router.push({name:"main"})
    },
    reviews(){
        this.visible=true
    },
   commentConfirm(){
       this.$axios.post('/reviews/add',{data:{reviewsContent:this.reviewsComment,userId:this.$cookies.get("userId")}}).then(resp => {
            this.$message({
                showClose:true,
                message:"留言成功",
                type:"success"
            })
            this.$axios.post('/reviews/search',{data:{}}).then(resp => {
                this.messageList=resp.data.data
            })
            this.visible=false
        })
   },
   cancel(){
       this.visible=false
       this.reviewsComment=""
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