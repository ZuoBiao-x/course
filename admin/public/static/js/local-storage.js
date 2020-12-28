/*
* 组件(页面)间传输数据可以用h5原生的localStorage,sessionStorage ;
* 也可以用js全局变量;也可以用vuex store。但是后两者在页面刷新时会丢失数据，所以推存h5原生的。
*
* sessionStorage是会话缓存，浏览器窗口关闭，缓存就没了；
* localStoraae是本地缓存，浏览器关闭后，下次再打开还能读取到。
*
* localStorage和sessionStorage只能操作字符串，但是我们经常要放对象数据,所以封装一下
* */

LOCAL_KEY_REMEMBER_USER = "LOCAL_KEY_REMEMBER_USER"; // 用户点击记住我时，保存信息到LocalStorage

LocalStorage = {
    get: function (key) {
        let v = localStorage.getItem(key);
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        localStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        localStorage.removeItem(key);
    },
    clearAll: function () {
        localStorage.clear();
    }
};