<template>
  <div>
        <sidebar :activeIndex="activeIndex">
            <div class='tinymce'>
                <el-row>
                    <el-col :span="12">
                        请输入简介
                    </el-col>
                    <el-col :span="12">
                        预览效果图
                    </el-col>
                </el-row>   
                <el-row>
                    <el-col :span="12">
                        <editor id='tinymce' v-model='tinymceHtml' :init='init'></editor>
                        <div style="float:right;margin-top:8px;padding-bottom:4px">
                             <el-button size="small" @click="release" type="primary">发布</el-button>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="view" v-html='tinymceHtml'></div>
                    </el-col>
                </el-row>         
            </div>
        </sidebar>
  </div>
</template>

<script>
import tinymce from 'tinymce/tinymce'
import 'tinymce/themes/silver/theme'
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/plugins/image'
import 'tinymce/plugins/link'
import 'tinymce/plugins/code'
import 'tinymce/plugins/table'
import 'tinymce/plugins/lists'
import 'tinymce/plugins/contextmenu'
import 'tinymce/plugins/wordcount'
import 'tinymce/plugins/colorpicker'
import 'tinymce/plugins/textcolor'
import 'tinymce/icons/default'
import sidebar from "@/components/menu"
export default {
    props: {
        value: {
            type: String,
            default: ''
        },
        // 基本路径，默认为空根目录，如果你的项目发布后的地址为目录形式，
        // 即abc.com/tinymce，baseUrl需要配置成tinymce，不然发布后资源会找不到
        baseUrl: {
            type: String,
            default: ''
        },
        disabled: {
            type: Boolean,
            default: false
        },
        plugins: {
            type: [String, Array],
            default: 'image link'
        },
        toolbar: {
            type: [String, Array],
            default: 'undo redo |  formatselect | bold italic forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | lists image media table | removeformat'
        }
    },
  name: 'write',
  data () {
    return {
      activeIndex:"2-1",
      tinymceHtml: "",
      init: {
        language_url: '/static/tinymce/zh_CN.js',
        language: 'zh_CN',
        skin_url: '/static/tinymce/skins/ui/oxide',
        height: 520,
        branding: false, // 是否禁用“Powered by TinyMCE”
        menubar: true, // 顶部菜单栏显示
        plugins: this.plugins,
        toolbar: this.toolbar,
        images_upload_url: "http://localhost:8080/eMarketing/api/upload/uploadFile"
      }
    }
  },
  mounted () {
    this.$axios.post('/introduce/search').then(resp => {
        this.tinymceHtml=resp.data.data.introductionContent;
    })
    tinymce.init({})
  },
  methods:{
    release(){
        var sendData={data:{introductionContent:this.tinymceHtml}}
        this.$axios.post('/introduce/update',sendData).then(resp => {
            this.$message({
                showClose:true,
                message:"发布成功",
                type:"success"
            })
        }) 
    }
  },
  components: {Editor,sidebar}
}
</script>

<style scoped>
.view{
    border: solid 1px lightgray;
    height: 520px;
    background-color: white;
    overflow:auto; 
    overflow-x:auto;
    overflow-y:auto;
}
</style>