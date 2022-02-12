import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //这里放全局参数
    userId:null,
    userName:null,
    adminName:null,
    activeId:null
  },
  mutations: {
    //这里是set方法
    setUserId(state,userId){
        state.userId = userId
    },

    setUserName(state,userName){
        state.userName = userName
    },
    setAdminName(state,userName){
        state.adminName = userName
    },
    setActiveId(state,activeId){
        state.activeId = activeId
    },
  },
    //这里是get方法     
  getters: {
    getUserId: state => state.userId,
    getUserName: state => state.userName,
    getAdminName: state => state.adminName,
    getActiveId: state => state.activeId,
  },
  actions: {},
  modules: {}
})