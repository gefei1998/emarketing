<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px;padding-top:8px">
                    <span>首页推荐设置</span>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" icon="el-icon-setting" @click="saveSetting()">保存设置</el-button>
                </el-col>
            </el-row>
            <el-row>
                 <el-divider><i class="el-icon-setting"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:450px;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-row>
                    <el-col :span="24">
                        请设置推荐商品
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <div @click="selectGood(1)"><el-image :src="goodPath1"  class="goodDiv"></el-image></div>
                    </el-col>
                    <el-col :span="8">
                        <div @click="selectGood(2)"><el-image :src="goodPath2" class="goodDiv"></el-image></div>
                    </el-col>
                    <el-col :span="8">
                        <div @click="selectGood(3)"><el-image :src="goodPath3" class="goodDiv"></el-image></div>
                    </el-col>
                </el-row>
                 <el-row>
                    <el-col :span="24">
                        请设置推荐活动
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <div @click="selectActive()"><el-image :src="activePath" class="activeDiv"></el-image></div>
                    </el-col>
                </el-row>
            </div>
        </sidebar>

         <div v-if="goodVisible">
            <el-dialog title="选择商品"  :visible.sync="goodVisible" width="80%">
              <el-row style="padding-top:15px;">
                <el-col :span="20" style="text-align:left;padding-left:20px">
                    <el-row>
                        <el-col :span="5">
                            <span>商品名:</span>
                            <el-input style="width:120px" v-model="goodName"></el-input>
                        </el-col>
                        <el-col :span="7">
                            <span>商品种类:</span>
                            <el-select style="width:170px" collapse-tags v-model="goodType" multiple placeholder="请选择">
                                <el-option
                                v-for="item in goodTypeOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="12">
                            <span>价格区间:</span>
                            <el-input style="width:80px" v-model="smallPrice"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                            ~
                            <el-input style="width:80px" v-model="bigPrice"><i slot="suffix" style="font-style:normal;margin-right: 3px;line-height: 40px;">元</i></el-input>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" icon="el-icon-search" @click="goodSearchButton">搜索</el-button>
                </el-col>
            </el-row>
            <el-row>
                 <el-divider><i class="el-icon-search"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:200px;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-table
                    :data="goodTableData"
                    @row-click="clickGoodRow"
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
                    label="商品价格"
                    prop="goodPrice">
                    <template slot-scope="scope">
                        <span v-if="scope.row.goodPrice.toString().split('.').length==1">{{ scope.row.goodPrice}}.00元</span>
                        <span v-if="scope.row.goodPrice.toString().split('.').length==2&&scope.row.goodPrice.toString().split('.')[1].length==1">{{ scope.row.goodPrice }}0元</span>
                        <span v-if="scope.row.goodPrice.toString().split('.').length==2&&scope.row.goodPrice.toString().split('.')[1].length==2">{{ scope.row.goodPrice }}元</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="销量"
                    prop="saleNum">
                    </el-table-column>
                    
                </el-table>
            </div>
            <el-row>
                <el-divider><i class="el-icon-tickets"></i></el-divider>
            </el-row>
            <el-row>
                <el-col>
                    <el-pagination
                        @size-change="goodHandleSizeChange"
                        @current-change="goodHandleCurrentChange"
                        :current-page="1"
                        :page-sizes="[3, 5, 10]"
                        :page-size="3"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="goodPageTotal">
                    </el-pagination>
                </el-col>
            </el-row>
            </el-dialog>
        </div>

        <div v-if="activeVisible">
            <el-dialog title="选择活动"  :visible.sync="activeVisible" width="80%">
              <el-row style="padding-top:15px">
                <el-col :span="20" style="text-align:left;padding-left:20px">
                    <el-row>
                        <el-col :span="12">
                            <span>活动名:</span>
                            <el-input style="width:200px" v-model="activeName"></el-input>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" icon="el-icon-search" @click="activeSearchButton">搜索</el-button>
                </el-col>
            </el-row>
            <el-row>
                 <el-divider><i class="el-icon-search"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:380px;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-table
                    @row-click="clickActiveRow"
                    :data="activeTableData"
                    style="width: 100%">
                    <el-table-column
                    label="活动名"
                    prop="activeName">
                    </el-table-column>
                    <el-table-column
                    width="300px"
                    label="活动图片"
                    prop="activePath">
                    <template slot-scope="scope">
                        <el-image 
                            style="width: 200px; height: 35px；display: block;"
                            :src="scope.row.activePath" 
                            :preview-src-list="[scope.row.activePath]">
                        </el-image>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="活动满减"
                    >
                    <template slot-scope="scope">
                        满{{scope.fullPrice}}元减{{scope.minusPrice}}元
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
                        @size-change="activeHandleSizeChange"
                        @current-change="activeHandleCurrentChange"
                        :current-page="1"
                        :page-sizes="[3, 5, 10]"
                        :page-size="3"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="activePageTotal">
                    </el-pagination>
                </el-col>
            </el-row>
            </el-dialog>
        </div>
  </div>
</template>

<script>
import sidebar from "@/components/menu"
export default {
  name: 'recommendSet',
  data () {
    return {
      activeIndex:"7-1",
      activePageTotal:0,
      activePage:1,
       activePageNum:3,
      goodPageTotal:0,
      goodPage:1,
       goodPageNum:3,
       goodName:"",
      goodType:[],
       goodTypeOptions: [{
          value: 0,
          label: '办公设备'
        }, {
          value: 1,
          label: '办公耗材'
        }, {
          value: 2,
          label: '办公纸类'
        }, {
          value: 3,
          label: '笔类'
        }, {
          value: 4,
          label: '记事本类'
        },{
          value: 5,
          label: '装订器材'
        }, {
          value: 6,
          label: '文件夹类'
        }, {
          value: 7,
          label: '办公刀具'
        }, {
          value: 8,
          label: '财会用品'
        }],
        smallPrice:null,
        bigPrice:null,
        goodVisible:false,
        goodTableData:[],
        index:1,
        goodId1:"",
        goodId2:"",
        goodId1:"",
        goodPath1:"",
        goodPath2:"",
        goodPath3:"",
        activeId:"",
        activePath:"",
        activeVisible:false,
        activeName:"",
        activeTableData:[],
    }
  },
  mounted () {
   this.$axios.post('/recommend/search').then(resp => {
       this.goodId1=resp.data.data.goodId1
       this.goodId2=resp.data.data.goodId2
       this.goodId3=resp.data.data.goodId3
       this.goodPath1=resp.data.data.goodPath1
       this.goodPath2=resp.data.data.goodPath2
       this.goodPath3=resp.data.data.goodPath3
       this.activeId=resp.data.data.activeId
       this.activePath=resp.data.data.activePath
   })
  },
  methods:{
     saveSetting(){
        var goodIds=this.goodId1.toString()+","+this.goodId2.toString()+","+this.goodId3.toString()
        var activeId=this.activeId
            var data={data:{goodIds:goodIds,activeId:activeId}}
            this.$axios.post('/recommend/update', data).then(resp => {
              this.$message({
                  showClose:true,
                  message:"设置成功",
                  type:"success"
              })
            })
        },
    selectGood(index){
        this.index=index
        this.searchGood({data:{flag:0}});
        this.goodVisible=true
    },

     selectActive(){
        this.searchActive({data:{deleteFlag:0}});
        this.activeVisible=true
    },
   searchGood(sendData){
        this.$axios.post('/goods/search',sendData).then(resp => {
            var goodData = resp.data.data;
            this.goodPageTotal=goodData.length
            this.goodTableData=[]
            goodData.forEach((item,index)=>{
                if((this.goodPage-1)*this.goodPageNum-1<index&&index<this.goodPage*this.goodPageNum){
                    this.goodTableData.push(item)
                }
            })
        })
    },
    goodHandleSizeChange(val) {
         this.goodPageNum=val
         this.searchGood({data:{flag:0}});
         console.log(`每页 ${val} 条`);
      },
      goodHandleCurrentChange(val) {
          this.goodPage=val
          this.searchGood({data:{flag:0}});
          console.log(`当前页: ${val}`);
      },
      goodSearchButton(){
          var sendData={data:{
                                flag:0,
                                goodType:null,
                                goodName:this.goodName,
                                smallPrice:null,
                                bigPrice:null,
                        }}
          if(this.goodType.length!=0){
              sendData.data.goodType=this.goodType
          }
          if(this.smallPrice!=""){
              sendData.data.smallPrice=this.smallPrice
          }
          if(this.bigPrice!=""){
              sendData.data.bigPrice=this.bigPrice
          }
          this.searchGood(sendData);
      },
      clickGoodRow(data){
        if(this.index==1){
            this.goodId1=data.goodId
            this.goodPath1=data.goodPath
        }else if(this.index==2){
            this.goodId2=data.goodId
            this.goodPath2=data.goodPath
        }else if(this.index==3){
            this.goodId3=data.goodId
            this.goodPath3=data.goodPath
        }
        this.goodVisible=false
      },


      searchActive(sendData){
        this.$axios.post('/active/search',sendData).then(resp => {
            var activeData = resp.data.data;
            this.activePageTotal=activeData.length
            this.activeTableData=[]
            activeData.forEach((item,index)=>{
                if((this.activePage-1)*this.activePageNum-1<index&&index<this.activePage*this.activePageNum){
                    this.activeTableData.push(item)
                }
            })
        })
    },

    //分页
     activeHandleSizeChange(val) {
         this.activePageNum=val
         this.searchActive({data:{}});
         console.log(`每页 ${val} 条`);
      },
      activeHandleCurrentChange(val) {
          this.activePage=val
          this.searchActive({data:{}});
          console.log(`当前页: ${val}`);
      },

      activeSearchButton(){
          var sendData={data:{
                            activeName:this.activeName,
                            deleteFlag:0
                        }}
          this.searchActive(sendData);
      },

      clickActiveRow(data){
        this.activeId=data.activeId
        this.activePath=data.activePath
        this.activeVisible=false
      }

  },
  components: {sidebar}
}
</script>

<style scoped>
  .goodDiv{
      width:60%;
      height:200px;
      
      cursor: pointer;
  }
  .activeDiv{
      width:60%;
      height:200px;
      cursor: pointer;
  }
  .el-divider__text{
      background-color:#F0F8FF
  }

  
</style>