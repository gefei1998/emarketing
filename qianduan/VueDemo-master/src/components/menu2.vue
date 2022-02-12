<template>
  <div>
    <el-row style="height:30px;width:100%;background-color:#D8D8D8;position:fixed;top:0;z-index:999;">
        <div style="float:right;padding-right:20px;border:none">
          <span v-if="userName==null||userName==''" style="line-height:30px"><a style="color: red;cursor: pointer;" href="http://localhost:8090/#/login">你好,请登录</a></span>
          <span v-if="userName==null||userName==''"><a href="http://localhost:8090/#/register">免费注册</a></span>

          <el-dropdown v-else trigger="click" style="line-height:30px">
            <span class="el-dropdown-link">
                <i class="el-icon-user"></i>
                {{userName}}
                <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
                <div @click="logOut"><el-dropdown-item>退出登录</el-dropdown-item></div>
            </el-dropdown-menu>
        </el-dropdown>


          <el-divider direction="vertical"></el-divider>
          <a class="aTag" :style="aStyle1" @click="push('order')"> 
            <i class="el-icon-s-order"></i><span>我的订单</span>
          </a>
          
          <el-divider direction="vertical"></el-divider>
          <a class="aTag" :style="aStyle2" @click="push('shopping')"> 
            <i class="el-icon-shopping-cart-2"></i><span>购物车</span>
          </a>
          
          <el-divider direction="vertical"></el-divider>
          <a  class="aTag" :style="aStyle3" @click="push('user')"> 
            <i class="el-icon-user-solid"></i><span>个人中心</span>
          </a>

          <el-divider direction="vertical"></el-divider>
          <a  class="aTag" :style="aStyle4" @click="push('message')"> 
            <i class="el-icon-message"></i><span>留言箱</span>
          </a>
          
        </div>
    </el-row>
    <slot></slot>
  </div>
</template>

<script>
  export default {
    props:{
          aStyle1:{
              type:String,
              default:""
          },
          aStyle2:{
              type:String,
              default:""
          },
          aStyle3:{
              type:String,
              default:""
          },
          aStyle4:{
              type:String,
              default:""
          }
      },
      data(){
          return{
              userName:"",
              checked:true
          }
      },
      mounted(){
          if(this.$cookies.isKey("userName")){
             this.userName=this.$cookies.get("userName")
          }
      },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      push(flag){
          var path=""
          if(flag=="order"){
              path="myOrder"
          }else if(flag=="shopping"){
              path="shopping"
          }else if(flag=="user"){
              path="personalCenter"
          }else if(flag=="message"){
              path="message"
          }
          if(!this.$cookies.isKey("userName")){
            this.$message({
              showClose:true,
              message:"请先登录",
              type:"warning"
            })
              this.$router.push({name:"login"})
          }else{
            this.$router.push({name:path})
          }
      },
      logOut(){
          this.$cookies.remove("userName") 
          this.$router.push({name:"login"})
      }
    }
  }
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #2E2E2E;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }

    .el-menu {
      border: none !important;
      text-align: left;
    }
    .el-submenu .el-menu-item{
        padding-left : 60px !important;
    }

    >>> .el-divider--vertical{
        background-color:#2E2E2E
    }

    .aTag{
      color: #2E2E2E;
      cursor: pointer;
      text-decoration: none;
    }
</style>
