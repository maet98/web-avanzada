import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import i18n from "./i18n";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import axios from "axios";
import store from "./store";

// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
Vue.config.productionTip = true;

const instance = axios.create({
  baseURL: "http://localhost:8080/api",
    timeout: 1000,
    headers: { 'Authorization' : localStorage.getItem("token") }
});

window.axios = instance;

if(localStorage.getItem("token") == null) {
    store.dispatch("toggle", false);
}

new Vue({
  router,
  i18n,
  store,
  render: h => h(App)
}).$mount("#app");
