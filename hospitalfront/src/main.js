import Vue from 'vue'
import App from './App'
import router from './router'
import store from  './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
import './utils/filter_utils.js'
import Vuex from 'vuex'
import htmlToPdf from './utils/htmlToPdf'
Vue.use(htmlToPdf)
Vue.use(Vuex);

Vue.use(ElementUI)
Vue.config.productionTip = false
window.bus = new Vue();
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: {App}
})
