<template>
  <div>
    <headermenu>
      <el-row style="margin-top:30px" type="flex">
        <el-col :span="4" style="background-color:LightBlue">
            <el-image @click="jumpMain" style="width:100px;height:100px;margin-top:10px;cursor:pointer" src="http://localhost:8090/static/img/logo.png" />
            <el-image style="height:70px" src="../../static/img/webIntroduction.png"></el-image>
        </el-col>
        <el-col :span="16" style="background-color:lightyellow">
            <div v-html='tinymceHtml'></div>
        </el-col>
        <el-col :span="4" style="background-color:LightBlue">
            
        </el-col>
      </el-row>
    </headermenu>
  </div>
</template>

<script>
import headermenu from "@/components/menu2"
export default {
  components: {headermenu},
  name: "introduction",
  data() {
    return {
     tinymceHtml:""
        }
  },
  mounted() {
    this.$axios.post('/introduce/search').then(resp => {
        this.tinymceHtml=resp.data.data.introductionContent;
    })
  },
  methods:{
      jumpMain(){
          this.$router.push({name:"main"})
      }
  }
}
</script>

<style scoped>

</style>