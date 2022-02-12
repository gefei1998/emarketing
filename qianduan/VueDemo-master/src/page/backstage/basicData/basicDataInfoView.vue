<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <el-row>
                 <el-divider><i class="el-icon-setting"></i></el-divider>
            </el-row>
            <div style="background-color:white;height:480px;overflow:auto;overflow-x:auto;overflow-y:auto;">
                <el-card class="box-card" v-for="(item,index) in tableData" :key="index">
                    <div slot="header" class="clearfix">
                        <span style="float:left">{{item.dictionaryDesc}}:</span>
                        <el-button style="float: right; padding: 3px 0" @click="update(index)" type="text">修改</el-button>
                    </div>
                    <div>
                        <el-form style="padding-top:5px" :model="item" status-icon :rules="rules" ref="ruleForm" label-width="100px">
                            <el-row>
                                <el-col :span="24">
                                    <el-form-item label="字典名">
                                        <el-input readonly="readonly" v-model="item.dictionaryName" style="width:90%"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                    <el-form-item label="字典值" prop="dictionaryValue">
                                        <el-input v-model="item.dictionaryValue" style="width:90%"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form>
                    </div>
                </el-card>
            </div>
            <el-row>
                <el-divider><i class="el-icon-setting"></i></el-divider>
            </el-row>
        </sidebar>
  </div>
</template>

<script>
import sidebar from "@/components/menu"
export default {
  name: 'integralInfoView',
  data () {
    return {
      activeIndex:"10-1",
      tableData: [],
      rules:{
        dictionaryValue: [{ required: true, message: '字典值不能为空', trigger: 'blur' }]
      }
    }
  },
  mounted () {
    this.searchDictionary();
  },
  methods:{
    searchDictionary(){
        this.$axios.post('/dictionary/search').then(resp => {
            this.tableData=resp.data.data
        })
    },
    update(index){
      this.$refs['ruleForm'][index].validate((valid) => {
        if (valid) {
            var data={data:this.tableData[index]}
            this.$axios.post('/dictionary/update', data).then(resp => {
              this.$message({
                  showClose:true,
                  message:"修改成功",
                  type:"success"
              })
              this.searchDictionary();
            })
        }
      })
   },
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
</style>