<template>
  <div v-html="htmlBody">
    <div v-if="show">
        {{htmlBody}}
    </div>
  </div>
</template>

<script>
export default {
  name: "pay",
  data() {
    return {
        htmlBody:"",
        show:false,
        sendData:{data:{}}
    }
  },
  created() {
    this.sendData.data=this.$route.query
    this.$axios.post('/ailpay', this.sendData).then(resp => {
        this.htmlBody = resp.data.data.htmlBody
    })
  },
  watch:{
      htmlBody:{
          deep:true,
          immeediate:true,
          handler:function(value) {
            if(value!=""){
                this.show=true
                this.$nextTick(function() { 
                    document.forms[0].submit()
                }); 
            }
          }
      },
      
  }
};
</script>
