import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false;

// 此处可以理解为将 axios 对象赋值给 $ajax 对象
// 又因为 Vue.prototype.xxx 表示的对象为全局对象，所以，可以在之后的组件中通过 this.$ajax 来使用 $ajax 对象
Vue.prototype.$ajax = axios;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log("请求：", config);
  return config;
}, error => {});
axios.interceptors.response.use(function (response) {
  console.log("返回结果：", response);
  return response;
}, error => {});

// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

// 查看当前环境
console.log("环境：", process.env.NODE_ENV);

