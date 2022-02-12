import Vue from 'vue'
import Router from 'vue-router'
import main from '@/page/main'
import login from '@/page/login'
import register from '@/page/register'
import pay from '@/page/pay'
import write from '@/page/backstage/introduction/write'
import userInfoView from '@/page/backstage/user/userInfoView'
import userInfoAdd from '@/page/backstage/user/userInfoAdd'
import goodInfoView from '@/page/backstage/good/goodInfoView'
import goodInfoAdd from '@/page/backstage/good/goodInfoAdd'
import reviewsInfoView from '@/page/backstage/reviews/reviewsInfoView'
import orderInfoView from '@/page/backstage/order/orderInfoView'
import activeInfoView from '@/page/backstage/active/activeInfoView'
import activeInfoAdd from '@/page/backstage/active/activeInfoAdd'
import financialInfoView from '@/page/backstage/financial/financialInfoView'
import integralInfoView from '@/page/backstage/integral/integralInfoView'
import basicDataInfoView from '@/page/backstage/basicData/basicDataInfoView'
import adminLogin from '@/page/backstage/adminLogin'
import introduction from '@/page/introduction'
import recommendSet from '@/page/backstage/recommend/recommendSet'
import goodDetail from '@/page/goodDetail'
import orderConfirm from '@/page/orderConfirm'
import shopping from '@/page/shopping'
import personalCenter from '@/page/personalCenter'
import myOrder from '@/page/myOrder'
import activeDetail from '@/page/activeDetail'
import message from '@/page/message'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/main',
      name: 'main',
      component: main
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/pay',
      name: 'pay',
      component: pay
    },
    {
      path: '/backstage/introduction/write',
      name: 'write',
      component: write
    },
    {
      path: '/backstage/user/userInfoView',
      name: 'userInfoView',
      component: userInfoView
    },
    {
      path: '/backstage/user/userInfoAdd',
      name: 'userInfoAdd',
      component: userInfoAdd
    },
    {
      path: '/backstage/good/goodInfoView',
      name: 'goodInfoView',
      component: goodInfoView
    },
    {
      path: '/backstage/good/goodInfoAdd',
      name: 'goodInfoAdd',
      component: goodInfoAdd
    },
    {
      path: '/backstage/reviews/reviewsInfoView',
      name: 'reviewsInfoView',
      component: reviewsInfoView
    },
    {
      path: '/backstage/order/orderInfoView',
      name: 'orderInfoView',
      component: orderInfoView
    },
    {
      path: '/backstage/active/activeInfoView',
      name: 'activeInfoView',
      component: activeInfoView
    },
    {
      path: '/backstage/active/activeInfoAdd',
      name: 'activeInfoAdd',
      component: activeInfoAdd
    },
    {
      path: '/backstage/financial/financialInfoView',
      name: 'financialInfoView',
      component: financialInfoView
    },
    {
      path: '/backstage/integral/integralInfoView',
      name: 'integralInfoView',
      component: integralInfoView
    },
    {
      path: '/backstage/basicData/basicDataInfoView',
      name: 'basicDataInfoView',
      component: basicDataInfoView
    },
    {
      path: '/backstage/adminLogin',
      name: 'adminLogin',
      component: adminLogin
    },
    {
      path: '/backstage/adminLogin',
      name: 'adminLogin',
      component: adminLogin
    },
    {
      path: '/user/introduction',
      name: 'introduction',
      component: introduction
    },
    {
      path: '/recommend/recommendSet',
      name: 'recommendSet',
      component: recommendSet
    },
    {
      path: '/user/goodDetail',
      name: 'goodDetail',
      component: goodDetail
    },
    {
      path: '/user/orderConfirm',
      name: 'orderConfirm',
      component: orderConfirm
    },
    {
      path: '/user/shopping',
      name: 'shopping',
      component: shopping
    },
    {
      path: '/user/personalCenter',
      name: 'personalCenter',
      component: personalCenter
    },
    {
      path: '/user/myOrder',
      name: 'myOrder',
      component: myOrder
    },
    {
      path: '/user/activeDetail',
      name: 'activeDetail',
      component: activeDetail
    },
    {
      path: '/user/message',
      name: 'message',
      component: message
    }
  ]
})
