import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Pasado from "../views/Pasado.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/past",
    name: "Past",
    component: Pasado
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
