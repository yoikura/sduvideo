export default {
  // 为当前模块开启命名空间
  namespaced: true,

  // 模块的 state 数据
  state: () => ({
    Info: null,
    isLogin: false,
  }),

  // 模块的 mutations 方法
  mutations: {
    addUser(state, e) {
      state.Info = e
    },
    removeUser(state){
      state.Info = null
    },
    changeLogin(state,e){
      state.isLogin = true
    },
    changeLogout(state,e){
      state.isLogin = false
    },
  },

  // 模块的 getters 属性
  getters: {},
}
