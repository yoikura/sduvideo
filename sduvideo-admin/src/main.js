import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import request from "@/utils/request";
import sduResquest from "@/utils/sduResquest";

// main.js全局注册
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)

Vue.config.productionTip = false

Vue.use(ElementUI, { size: "mini" });

Vue.prototype.request=request
Vue.prototype.$sduResquest=sduResquest

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
