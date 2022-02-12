<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row style="padding-top:15px">
                <el-col :span="18" style="text-align:left;padding-left:20px">
                    <el-row>
                        <el-col :span="24">
                            <span>订单时间:</span>
                            <el-date-picker
                            v-model="orderTime"
                            type="datetimerange"
                            :picker-options="pickerOptions"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            align="right"
                            style="width:385px">
                            </el-date-picker>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="6">
                    <el-button v-if="show" type="primary" icon="el-icon-pie-chart" @click="searchEcharts">饼图</el-button>
                    <el-button v-else type="primary" icon="el-icon-s-grid" @click="searchEcharts">表格</el-button> 
                    <el-button type="primary" icon="el-icon-search" @click="searchButton">搜索</el-button>
                </el-col>
            </el-row>
            <el-row>
                 <el-divider><i class="el-icon-search"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:480px;overflow:auto;overflow-x:auto;overflow-y:auto;" v-if="show">
                <el-table
                    :data="tableData"
                    style="width: 100%">
                    <el-table-column
                    prop="goodTypeName" width="170px">
                    </el-table-column>
                    <el-table-column
                    label="销量"
                    prop="saleNum" width="170px">
                    </el-table-column>
                    <el-table-column
                    label="销售额"
                    prop="saleMoney">
                    <template slot-scope="scope">
                        <span v-if="scope.row.saleMoney.toString().split('.').length==1">{{ scope.row.saleMoney}}.00元</span>
                        <span v-if="scope.row.saleMoney.toString().split('.').length==2&&scope.row.saleMoney.toString().split('.')[1].length==1">{{ scope.row.saleMoney }}0元</span>
                        <span v-if="scope.row.saleMoney.toString().split('.').length==2&&scope.row.saleMoney.toString().split('.')[1].length==2">{{ scope.row.saleMoney }}元</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="优惠金额"
                    prop="preferentialAmount">
                   <template slot-scope="scope">
                        <span v-if="scope.row.preferentialAmount.toString().split('.').length==1">{{ scope.row.preferentialAmount}}.00元</span>
                        <span v-if="scope.row.preferentialAmount.toString().split('.').length==2&&scope.row.preferentialAmount.toString().split('.')[1].length==1">{{ scope.row.preferentialAmount }}0元</span>
                        <span v-if="scope.row.preferentialAmount.toString().split('.').length==2&&scope.row.preferentialAmount.toString().split('.')[1].length==2">{{ scope.row.preferentialAmount }}元</span>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="实际销售额"
                    prop="realSaleMoney">
                    <template slot-scope="scope">
                        <span v-if="scope.row.realSaleMoney.toString().split('.').length==1">{{ scope.row.realSaleMoney}}.00元</span>
                        <span v-if="scope.row.realSaleMoney.toString().split('.').length==2&&scope.row.realSaleMoney.toString().split('.')[1].length==1">{{ scope.row.realSaleMoney }}0元</span>
                        <span v-if="scope.row.realSaleMoney.toString().split('.').length==2&&scope.row.realSaleMoney.toString().split('.')[1].length==2">{{ scope.row.realSaleMoney }}元</span>
                    </template>
                    </el-table-column>
                </el-table>
            </div>
             <div v-else style="background-color:white;height:380px;">
                 <div id="myChart" :style="{width: '750px', height: '480px'}"></div>
             </div>
        </sidebar>
  </div>
</template>

<script>
import sidebar from "@/components/menu"
export default {
  name: 'financialInfoView',
  data () {
    return {
      activeIndex:"9-1",
      show:true,
      orderTime:"",
      tableData:[],
      echartData:[],
       pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
    }
  },
  mounted () {
    this.searchFinancial({data:{}});
  },
  watch: {
      //观察option的变化
      echartData: {
        handler(newVal) {
            if(!this.show){
                if (newVal) {
                    this.drawLine(newVal)
                }
            }
        },
        deep: true //对象内部属性的监听，关键。
      }
    },
  methods:{
    searchFinancial(sendData){
        this.$axios.post('/financial/search',sendData).then(resp => {
            this.tableData=[]
            this.tableData.push(resp.data.data.officeEquipment)
            this.tableData.push(resp.data.data.officeConsumables)
            this.tableData.push(resp.data.data.officePaper)
            this.tableData.push(resp.data.data.penClass)
            this.tableData.push(resp.data.data.notepadClass)
            this.tableData.push(resp.data.data.bindingEquipment)
            this.tableData.push(resp.data.data.folderClass)
            this.tableData.push(resp.data.data.officeTools)
            this.tableData.push(resp.data.data.accountingSupplies)
            this.echartData=[
                {value: resp.data.data.officeEquipment.realSaleMoney, name: resp.data.data.officeEquipment.goodTypeName},
                {value: resp.data.data.officeConsumables.realSaleMoney, name: resp.data.data.officeConsumables.goodTypeName},
                {value: resp.data.data.officePaper.realSaleMoney, name: resp.data.data.officePaper.goodTypeName},
                {value: resp.data.data.penClass.realSaleMoney, name: resp.data.data.penClass.goodTypeName},
                {value: resp.data.data.notepadClass.realSaleMoney, name: resp.data.data.notepadClass.goodTypeName},
                {value: resp.data.data.bindingEquipment.realSaleMoney, name: resp.data.data.bindingEquipment.goodTypeName},
                {value: resp.data.data.folderClass.realSaleMoney, name: resp.data.data.folderClass.goodTypeName},
                {value: resp.data.data.officeTools.realSaleMoney, name: resp.data.data.officeTools.goodTypeName},
                {value: resp.data.data.accountingSupplies.realSaleMoney, name: resp.data.data.accountingSupplies.goodTypeName},  
            ]
        })
    },
      searchButton(){
          var sendData={
              data:{
                startTime:null,
                endTime:null,                
            }}
            if(this.orderTime!=null&&this.orderTime!=""){
                sendData.data.startTime=this.dateTransformation(this.orderTime[0])
                sendData.data.endTime=this.dateTransformation(this.orderTime[1])
            }
          this.searchFinancial(sendData);
      },
      dateTransformation(date){
          let y = date.getFullYear()
            let m = date.getMonth() + 1
            m = m < 10 ? ('0' + m) : m
            let d = date.getDate()
            d = d < 10 ? ('0' + d) : d
            let h =date.getHours()
            h = h < 10 ? ('0' + h) : h
            let M =date.getMinutes()
            M = M < 10 ? ('0' + M) : M
            let s =date.getSeconds()
            s = s < 10 ? ('0' + s) : s
            let dateTime= y + '-' + m + '-' + d + ' ' + h + ':' + M + ':' + s;
            return dateTime;
      },
      searchEcharts(){
        if(this.show){
            this.show=false
            this.$nextTick(() => {
                this.drawLine(this.echartData);
            });   
        }else{
            this.show=true
        }
      },
      drawLine(echartData){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        myChart.setOption({
            title: {
                text: '实际销售额',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
            },
            series: [
                {
                    name: '商品种类',
                    type: 'pie',
                    radius: '50%',
                    data:echartData,
                    center: ["70%", "50%"] ,
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        },true);
    }
  },
  components: {sidebar}
}
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .el-divider__text{
      background-color:#F0F8FF
  }

.avatar-uploader .el-upload {
    border: 1px solid black;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 178px;
  }
  .avatar {
    float:left;
    width: 178px;
    height: 178px;
    display: block;
  }

  .avatar_div {
    float:left;
    width: 178px;
    height: 178px;
    border: 1px solid lightgray;
  }

  >>>.el-upload{
    float:left;
    padding-left:10px ;
  }
  .box-dev {
    margin-top:5px;
    width: 90%;
    height: 200px;
    overflow:auto;
    overflow-x:auto;
    overflow-y:auto;
    margin-left:5%;
  }
  .box-dev >>>.el-divider__text{
      background-color:white
    }

  >>>.el-dialog__header{
      float:left
  }

  .integral{
      border: 1px solid red;
      color: red;
      width: 40px;
      margin-left:5px;
  }
</style>
