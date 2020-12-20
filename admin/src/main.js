import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false;

// 此处可以理解为将 axios 对象赋值给 $ajax 对象
// 又因为 Vue.prototype.xxx 表示的对象为全局对象，所以，可以在之后的组件中通过 this.$ajax 来使用 $ajax 对象
Vue.prototype.$ajax = axios;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
