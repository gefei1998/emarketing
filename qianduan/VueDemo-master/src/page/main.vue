<template>
  <div>
    <headermenu>
      <el-row>
        <el-col :span="6" style="margin-top:50px;">
          <el-image style="width:100px;height:100px;margin-left:50px" src="http://localhost:8090/static/img/logo.jpg" />
        </el-col>
        <el-col :span="12" style="margin-top:80px;">
          <el-input style="width:80%" v-model="goodName"><template  slot="append"><el-button icon="el-icon-search" @click="searchButton"></el-button></template></el-input>
        </el-col>
        <el-col :span="6" style="margin-top:50px;">
          <el-image style="width:100px;height:100px;margin-right:50px;cursor:pointer" @click="jumpIntr" src="http://localhost:8090/static/img/jianjie.jpg" />
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <div style="margin-left:20%;height:300px;width:80%;background-color:lightgrey;margin-top:50px">
            <el-row>
              <el-col :span="12">
                <div :class="typeClass0" @mouseenter="mouseenter(0)" @mouseleave="mouseleave(0)" @click="selectType(0)">办公设备</div>
              </el-col>
              <el-col :span="12">
                <div :class="typeClass1" @mouseenter="mouseenter(1)" @mouseleave="mouseleave(1)" @click="selectType(1)">办公耗材</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <div :class="typeClass2"  @mouseenter="mouseenter(2)" @mouseleave="mouseleave(2)" @click="selectType(2)">办公纸类</div>
              </el-col>
              <el-col :span="12">
                <div :class="typeClass3"  @mouseenter="mouseenter(3)" @mouseleave="mouseleave(3)" @click="selectType(3)">笔类</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <div :class="typeClass4"  @mouseenter="mouseenter(4)" @mouseleave="mouseleave(4)" @click="selectType(4)">记事本类</div>
              </el-col>
              <el-col :span="12">
                <div :class="typeClass5"  @mouseenter="mouseenter(5)" @mouseleave="mouseleave(5)" @click="selectType(5)">装订器材</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <div :class="typeClass6" @mouseenter="mouseenter(6)" @mouseleave="mouseleave(6)" @click="selectType(6)">文件夹类</div>
              </el-col>
              <el-col :span="12">
                <div :class="typeClass7"  @mouseenter="mouseenter(7)" @mouseleave="mouseleave(7)" @click="selectType(7)">办公刀具</div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <div :class="typeClass8" @mouseenter="mouseenter(8)" @mouseleave="mouseleave(8)" @click="selectType(8)">财会用品</div>
              </el-col>
              <el-col :span="12">
                <div :class="typeClass9" @mouseenter="mouseenter(9)" @mouseleave="mouseleave(9)" @click="selectType(9)">全部</div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="12" style="margin-top:50px;">
          <el-image style="width:90%;height:300px;cursor:pointer" :src="activePath" @click="clickActive()" />
        </el-col>
        <el-col :span="6" style="margin-top:50px;">
          <el-carousel :interval="3000" arrow="always" >
            <el-carousel-item v-for="(item,index) in dataimg" :key="index">
              <el-image style="width:100%;height:300px;cursor:pointer" :src="item.src" @click="clickImg(item.goodId)" />
            </el-carousel-item>
          </el-carousel>
        </el-col>
      </el-row>
      <el-row>
        <el-col v-for="(item,index) in goodData" :key="index" style="width:250px;height:300px;border:solid 1px lightgrey;margin-left:10%;margin-top:100px">
          <div style="cursor:pointer" @click="clickImg(item.goodId)">
            <el-image style="width:250px;height:300px" :src="item.goodPath"/>
            <span>{{item.goodName}}</span><br/>
            <span>￥{{item.goodPrice}}</span>
          </div>
        </el-col>
      </el-row>
    </headermenu>
  </div>
</template>

<script>
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "mainpage",
  data() {
    return {
        goodName:"",
        dataimg: [],
        activePath:"",
        src1:"",
        src2:"",
        src3:"",
        activeId:"",
        typeClass0:"typeDiv",
        typeClass1:"typeDiv",
        typeClass2:"typeDiv",
        typeClass3:"typeDiv",
        typeClass4:"typeDiv",
        typeClass5:"typeDiv",
        typeClass6:"typeDiv",
        typeClass7:"typeDiv",
        typeClass8:"typeDiv",
        typeClass9:"typeDiv fontColor2",
        goodType:9,
        goodData:[]
    }
  },
  mounted() {
    this.searchGood({data:{flag:0}})
    this.$axios.post('/recommend/search').then(resp => {
      var src1=resp.data.data.goodPath1.split('http://localhost:8090/static/upload/')[1]
      var src2=resp.data.data.goodPath2.split('http://localhost:8090/static/upload/')[1]
      var src3=resp.data.data.goodPath3.split('http://localhost:8090/static/upload/')[1]
      this.dataimg=[{
						src: require(`../../static/upload/${src1}`),
            goodId:resp.data.data.goodId1
					},
					{
						src: require(`../../static/upload/${src2}`),
            goodId:resp.data.data.goodId2
					},
					{
						src: require(`../../static/upload/${src3}`),
            goodId:resp.data.data.goodId3
					}
				]
        this.activePath=resp.data.data.activePath
        this.activeId=resp.data.data.activeId
        this.$cookies.set("activeId",this.activeId,60 * 60 * 1)
    })
  },
  methods: {
   clickImg(goodid){
     this.$router.push({name:"goodDetail",query:{goodId:goodid}})
   },
   jumpIntr(){
     this.$router.push({name:"introduction"})
   },
   clickActive(){
     this.$router.push({name:"activeDetail"})
   },
   mouseenter(index){
     if(index==0&&this.goodType!=0){
       this.typeClass0="typeDiv fontColor"
     }else if(index==1&&this.goodType!=1){
       this.typeClass1="typeDiv fontColor"
     }else if(index==2&&this.goodType!=2){
       this.typeClass2="typeDiv fontColor"
     }else if(index==3&&this.goodType!=3){
       this.typeClass3="typeDiv fontColor"
     }else if(index==4&&this.goodType!=4){
       this.typeClass4="typeDiv fontColor"
     }else if(index==5&&this.goodType!=5){
       this.typeClass5="typeDiv fontColor"
     }else if(index==6&&this.goodType!=6){
       this.typeClass6="typeDiv fontColor"
     }else if(index==7&&this.goodType!=7){
       this.typeClass7="typeDiv fontColor"
     }else if(index==8&&this.goodType!=8){
       this.typeClass8="typeDiv fontColor"
     }else if(index==9&&this.goodType!=9){
       this.typeClass9="typeDiv fontColor"
     }
   },
   mouseleave(index){
     if(index==0&&this.goodType!=0){
       this.typeClass0="typeDiv"
     }else if(index==1&&this.goodType!=1){
       this.typeClass1="typeDiv"
     }else if(index==2&&this.goodType!=2){
       this.typeClass2="typeDiv"
     }else if(index==3&&this.goodType!=3){
       this.typeClass3="typeDiv"
     }else if(index==4&&this.goodType!=4){
       this.typeClass4="typeDiv"
     }else if(index==5&&this.goodType!=5){
       this.typeClass5="typeDiv"
     }else if(index==6&&this.goodType!=6){
       this.typeClass6="typeDiv"
     }else if(index==7&&this.goodType!=7){
       this.typeClass7="typeDiv"
     }else if(index==8&&this.goodType!=8){
       this.typeClass8="typeDiv"
     }else if(index==9&&this.goodType!=9){
       this.typeClass9="typeDiv"
     }
   },
   selectType(index){
     this.goodType = index
     var sendData={data:{
        flag:0,
         goodType:[index],
         goodName:this.goodName,
      }}
      if(sendData.data.goodType[0]==9){
        sendData.data.goodType=[0,1,2,3,4,5,6,7,8]
      }
      this.searchGood(sendData);
     if(index==0){
       this.typeClass0="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==1){
       this.typeClass1="typeDiv fontColor2"
       this.typeClass0="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==2){
       this.typeClass2="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==3){
       this.typeClass3="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==4){
       this.typeClass4="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==5){
       this.typeClass5="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==6){
       this.typeClass6="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==7){
       this.typeClass7="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass8="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==8){
       this.typeClass8="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass9="typeDiv"
     }else if(index==9){
       this.typeClass9="typeDiv fontColor2"
       this.typeClass1="typeDiv"
       this.typeClass2="typeDiv"
       this.typeClass3="typeDiv"
       this.typeClass4="typeDiv"
       this.typeClass5="typeDiv"
       this.typeClass6="typeDiv"
       this.typeClass7="typeDiv"
       this.typeClass0="typeDiv"
       this.typeClass8="typeDiv"
     }
   },
   searchGood(sendData){
        this.$axios.post('/goods/search',sendData).then(resp => {
            this.goodData = resp.data.data;
        })
    },
    searchButton(){
          var sendData={data:{
                                flag:0,
                                goodType:null,
                                goodName:this.goodName,
                        }}
          if(this.goodType!=""&&this.goodType!=9){
              sendData.data.goodType=[this.goodType]
          }
          this.searchGood(sendData);
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
</style>