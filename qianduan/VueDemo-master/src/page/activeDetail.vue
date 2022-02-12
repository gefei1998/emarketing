<template>
  <div>
    <headermenu>
      <el-row style="margin-top:30px;width:100%;background-color:lightblue">
        <el-col :span="4">
            <div @click="back"><img style="width:70px;height:70px;cursor: pointer;" src="../../static/img/logo.png" /></div>
        </el-col>
        <el-col :span="4">
              <div style="float:left"><el-image style="height:70px" src="../../static/img/activeDetail.png"></el-image></div>
          </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
            <el-image style="width:100%;height:300px;margin-top:50px;margin-left:50px" :src="activeData[0].activePath" :preview-src-list="[activeData[0].activePath]" />
        </el-col>
        <el-col :span="12">
            <el-row style="width:90%;margin-top:50px;margin-left:100px;height:50px">
              <el-col>
                <span style="float:left;font-size:30px;font-weight:900">{{activeData[0].activeName}}</span>
              </el-col>
            </el-row>
            <el-row style="width:90%;margin-top:50px;margin-left:100px;height:50px">
              <el-col>
                <span style="float:left;font-size:26px;font-weight:500">满{{activeData[0].fullPrice}}减{{activeData[0].minusPrice}}</span>
              </el-col>
            </el-row>
            <el-row style="width:90%;margin-top:20px;margin-left:100px;height:150px">
              <el-col>
                <span style="float:left;font-size:20px;">{{activeData[0].activeContent}}</span>
              </el-col>
            </el-row>
        </el-col>
      </el-row>
    </headermenu>
  </div>
</template>

<script>
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "activeDetail",
  data() {
    return {
       activeData:[{goodPath:""}],
       goodId:"",
       userId:""
    }
  },
  mounted() {
    this.$axios.post('/active/search',{data:{activeId:this.$cookies.get("activeId")}}).then(resp => {
      this.activeData = resp.data.data;
      console.log(this.activeData)
    })
  },
  methods: {
    back(){
        this.$router.push({name:"main"})
    },
  }
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