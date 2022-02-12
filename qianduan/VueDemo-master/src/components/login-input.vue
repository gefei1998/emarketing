<template>
  <div class="login-input">
    <div class="content-right">
      <div class="input-head">Blog登录</div>
      <el-input type="tex t" placeholder="输入账号" v-model="account" maxlength="30"></el-input>
      <el-input type="password" placeholder="输入密码" v-model="password"  style="margin-top: 10px;"></el-input>
      <el-switch v-model="isRemember"  active-text="记住账号"  active-color="#13ce66" inactive-color="#ff4949" style="margin-top: 10px;width: 100%;"></el-switch>
      <el-button type="primary" class="login-submit">登录</el-button>
    </div>
    <div ref="chart" style="width: 100%; height: 376px"></div>
  </div>
</template>

<script>

import Qs from 'qs'

export default {
  name: 'login-input',

  data () {
    return {
      account: '',
      password: '',
      isRemember: false
    }
  },
  created:function(){
    this.getEchartData();
    this.api()
    // var data = Qs.stringify({});
    // this.$axios
    // .post('/user/select', '')
    // .then(successResponse => {
    //   console.log(successResponse.data.data.mobile)
    //   // this.responseResult = JSON.stringify(successResponse.data)
    //   // if (successResponse.data.code === 200) {
    //   //   console.log(successResponse.data.message)
    //   //   this.$notify({
    //   //     title: '成功',
    //   //     message: successResponse.data.message,
    //   //     type: 'success'
    //   //   });
    //   // }else{
    //   //   this.$notify({
    //   //     title:"失败",
    //   //     message:successResponse.data.message,
    //   //     type:'error'
    //   //   })
    //   // }
    // })
    // .catch(failResponse => {})
  },
  methods: {
    getEchartData() {
      const chart = this.$refs.chart;
      if (chart) {
        const myChart = this.$echarts.init(chart);
        const option = {
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
        data: ['直达', '营销广告', '搜索引擎', '邮件营销', '联盟广告', '视频广告', '百度', '谷歌', '必应', '其他']
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            selectedMode: 'single',
            radius: [0, '30%'],
            label: {
                position: 'inner',
                fontSize: 14,
            },
            labelLine: {
                show: false
            },
            data: [
                {value: 1548, name: '搜索引擎'},
                {value: 775, name: '直达'},
                {value: 679, name: '营销广告', selected: true}
            ]
        },
        {
            name: '访问来源',
            type: 'pie',
            radius: ['45%', '60%'],
            labelLine: {
                length: 30,
            },
            label: {
                formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                backgroundColor: '#F6F8FC',
                borderColor: '#8C8D8E',
                borderWidth: 1,
                borderRadius: 4,
                
                rich: {
                    a: {
                        color: '#6E7079',
                        lineHeight: 22,
                        align: 'center'
                    },
                    hr: {
                        borderColor: '#8C8D8E',
                        width: '100%',
                        borderWidth: 1,
                        height: 0
                    },
                    b: {
                        color: '#4C5058',
                        fontSize: 14,
                        fontWeight: 'bold',
                        lineHeight: 33
                    },
                    per: {
                        color: '#fff',
                        backgroundColor: '#4C5058',
                        padding: [3, 4],
                        borderRadius: 4
                    }
                }
            },
            data: [
                {value: 1048, name: '百度'},
                {value: 335, name: '直达'},
                {value: 310, name: '邮件营销'},
                {value: 251, name: '谷歌'},
                {value: 234, name: '联盟广告'},
                {value: 147, name: '必应'},
                {value: 135, name: '视频广告'},
                {value: 102, name: '其他'}
            ]
        }
    ]
};
        myChart.setOption(option);
        window.addEventListener("resize", function () {
          myChart.resize();
        });
      }
      this.$on("hook:destroyed", () => {
        window.removeEventListener("resize", function () {
          myChart.resize();
        });
      });
    },
    api(){
      var data={data:{}}
      this.$axios
    .post('/user/detail', data)
    .then(successResponse => {
      console.log(successResponse.data.data)
      // this.responseResult = JSON.stringify(successResponse.data)
      // if (successResponse.data.code === 200) {
      //   console.log(successResponse.data.message)
      //   this.$notify({
      //     title: '成功',
      //     message: successResponse.data.message,
      //     type: 'success'
      //   });
      // }else{
      //   this.$notify({
      //     title:"失败",
      //     message:successResponse.data.message,
      //     type:'error'
      //   })
      // }
    })
    .catch(failResponse => {})
    }
  }
}
</script>

<style scoped>
  .login-input{
    width: 100%;
    height: 100%;
  }
  .login-input .content-right{
    width:320px;
    height: 100%;
  }
  .login-input .content-right .input-head{
    font-size: 18px;
    height: 60px;
    line-height: 60px;
    text-align: center;
  }
  .login-input .content-right .login-submit{
    width: 100%;
    margin-top: 10px;
  }
</style>
