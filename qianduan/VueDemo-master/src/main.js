// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import  VueQuillEditor from 'vue-quill-editor'
import router from './router'
import store from './vuex/store' 
import VueCookies from 'vue-cookies'

import 'element-ui/lib/theme-chalk/index.css'
import 'swiper/dist/css/swiper.css'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/bootstrap/dist/js/bootstrap.min.js';

// 引用公共js
import utils from './common/utils.js'
//引入echarts图
import echarts from 'echarts'
import moment from 'moment'; //导入模块
moment.locale('zh-cn'); //设置语言 或 moment.lang('zh-cn');


Vue.prototype.$moment = moment;//赋值使用
Vue.prototype.$echarts = echarts

// 引用axios，并设置基础URL为后端服务api地址
var axios = require('axios')
axios.defaults.baseURL = "http://localhost:8080/eMarketing/api"
//设置全局，每次ajax请求携带cookies
// axios.defaults.withCredentials = true
// 将API方法绑定到全局
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.prototype.utils = utils

Vue.use(ElementUI)
Vue.use(VueQuillEditor)
Vue.use(VueCookies)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

