<template>
  <div>
    <div style="height:30px;width:100%;background-color:#1C1C1C ">
        <el-dropdown trigger="click" style="float:right;padding-right:30px;padding-top:5px;">
            <span class="el-dropdown-link">
                <i class="el-icon-user"></i>
                {{adminName}}
                <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
                <div @click="logOut"><el-dropdown-item>退出</el-dropdown-item></div>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
    <el-row class="tac">
        <el-col :span="4">
            <div style="height: 595px;background-color:#1C1C1C;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-menu
                :default-active="activeIndex"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                background-color="#1C1C1C "
                text-color="#fff"
                active-text-color="#ffd04b">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-user-solid" ></i>
                            <span>用户管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('1-1')"><el-menu-item index="1-1"><i class="el-icon-view"></i> 用户信息一览</el-menu-item></div>
                            <div @click="push('1-2')"><el-menu-item index="1-2"><i class="el-icon-circle-plus-outline"></i>新增用户</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-document"></i>
                            <span>简介管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('2-1')"><el-menu-item index="2-1"><i class="el-icon-edit"></i>修改简介</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-s-goods"></i>
                            <span>商品管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('3-1')"><el-menu-item index="3-1"><i class="el-icon-view"></i>商品一览</el-menu-item></div>
                            <div @click="push('3-2')"><el-menu-item index="3-2"><i class="el-icon-circle-plus-outline"></i>新增商品</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                     <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-s-opportunity"></i>
                            <span>活动管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('4-1')"><el-menu-item index="4-1"><i class="el-icon-view"></i>活动一览</el-menu-item></div>
                            <div @click="push('4-2')"><el-menu-item index="4-2"><i class="el-icon-circle-plus-outline"></i>新增活动</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-chat-line-square"></i>
                            <span>留言管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('5-1')"><el-menu-item index="5-1"><i class="el-icon-view"></i>留言一览</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="6">
                        <template slot="title">
                            <i class="el-icon-s-order"></i>
                            <span>订单管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('6-1')"><el-menu-item index="6-1"><i class="el-icon-view"></i>订单一览</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="7">
                        <template slot="title">
                            <i class="el-icon-s-flag"></i>
                            <span>首页管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('7-1')"><el-menu-item index="7-1"><i class="el-icon-star-off"></i>首页推荐设置</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="8">
                        <template slot="title">
                            <i class="el-icon-collection"></i>
                            <span>积分管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('8-1')"><el-menu-item index="8-1"><i class="el-icon-view"></i>用户积分信息一览</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="9">
                        <template slot="title">
                            <i class="el-icon-pie-chart"></i>
                            <span>财务报表管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('9-1')"><el-menu-item index="9-1"><i class="el-icon-view"></i>财务报表一览</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="10">
                        <template slot="title">
                            <i class="el-icon-notebook-2"></i>
                            <span>基础数据管理</span>
                        </template>
                        <el-menu-item-group>
                            <div @click="push('10-1')"><el-menu-item index="10-1"><i class="el-icon-setting"></i>基础数据设置</el-menu-item></div>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </div>
        </el-col>
        <el-col :span="20" style="padding-top:5px;padding-left:5px;background-color: #F0F8FF;height:595px;overflow:auto;overflow-x:auto;overflow-y:auto;">
             <slot></slot>
        </el-col>
      </el-row>
  </div>
</template>

<script>
  export default {
      props:{
          activeIndex:{
              type:String,
              default:"0"
          }
      },
      data(){
          return{
              adminName:""
          }
      },
      mounted(){

          if(this.$cookies.isKey("adminName")){
             this.adminName=this.$cookies.get("adminName")
          }else{
              this.$router.push({name:"adminLogin"})
          }
          
      },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      push(index){
          var path=""
          if(index=="1-1"){
              path="userInfoView"
          }else if(index=="1-2"){
              path="userInfoAdd"
          }else if(index=="2-1"){
              path="write"
          }else if(index=="3-1"){
              path="goodInfoView"
          }else if(index=="3-2"){
              path="goodInfoAdd"
          }else if(index=="4-1"){
              path="activeInfoView"
          }else if(index=="4-2"){
              path="activeInfoAdd"
          }else if(index=="5-1"){
              path="reviewsInfoView"
          }else if(index=="6-1"){
              path="orderInfoView"
          }else if(index=="7-1"){
              path="recommendSet"
          }else if(index=="8-1"){
              path="integralInfoView"
          }else if(index=="9-1"){
              path="financialInfoView"
          }else if(index=="10-1"){
              path="basicDataInfoView"
          }

          this.$router.push({name:path})
      },
      logOut(){
        //   this.$store.commit('setAdminName', "");
        this.$cookies.remove("adminName") 
          this.$router.push({name:"adminLogin"})
      }
    }
  }
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
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
</style>