import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false;

// 此处可以理解为将 axios 对象赋值给 $ajax 对象
// 又因为 Vue.prototype.xxx 表示的对象为全局对象
// 所以，可以在之后的组件中通过 this.$ajax 来使用 $ajax 对象
Vue.prototype.$ajax = axios;

// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log("请求：", config);
    let token = Tool.getLoginUser().token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
  return config;
}, error => {});
axios.interceptors.response.use(function (response) {
  console.log("返回结果：", response);
  return response;
}, error => {
  console.log("返回拦截：", error.response);
  let response = error.response;
  const status = response.status;
  if (status === 401) {
    // 判断状态码是401 跳转到登录
    console.log("未登录，跳到登录页面");
    Tool.setLoginUser(null);
    router.push('/login');
  }
  return {
    data: {
      success: false,
      message: "请重新登录"
    }
  };
});

// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    return item.meta.loginRequire
  })) {
    let loginUser = Tool.getLoginUser();
    if (Tool.isEmpty(loginUser)) {
      next('/login');
    } else {
      next();
    }
  } else {
    next();
  }
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

// 查看当前环境
console.log("环境：", process.env.NODE_ENV);

