import Vue from "vue";
import VueRouter from "vue-router";
import Equiment from "../views/Equiment.vue";
import Client from "../views/Client.vue";
import Rental from "../views/Rental.vue";
import Login from "../views/Login.vue";
import store from "../store/index";
import RentalForm from "../components/RentalForm.vue";
import ReturnForm from "../components/ReturnForm.vue";
import ClientInfo from "../components/ClientInfo.vue";
import NotReturned from "../components/NotReturned.vue";

Vue.use(VueRouter);

const isAuthenticated = (to, from, next) => {
  if (store.getters.isAuthenticated) {
    next();
  } else {
      next({
          name: "Login"
      });
  }
};

const ifNotAuthenticated = (_, __, next) => {
  if (!store.getters.isAuthenticated) {
    next();
  } else {
      next({
          name: "Equiment"
      })
  }
};

const routes = [
  {
    path: "/equiment",
    name: "Equiment",
    component: Equiment,
    beforeEnter: isAuthenticated
  },

  {
    path: "/client",
    name: "Client",
    component: Client,
    beforeEnter: isAuthenticated
  },

  {
    path: "/rental",
    name: "Rental",
    component: Rental,
    beforeEnter: isAuthenticated
  },
  {
    path: "/Login",
    name: "Login",
    component: Login,
    beforeEnter: ifNotAuthenticated
  },
    {
        path: "/rentalForm",
        name: "rentalForm",
        component: RentalForm,
        beforeEnter: isAuthenticated
    },
    {
        path: "/return/:id",
        name: "returnForm",
        component: ReturnForm,
        props: true,
        beforeEnter: isAuthenticated
    },
    {
        path: "/client/:id",
        name: "ClientInfo",
        component: ClientInfo,
        props: true,
        beforeEnter: isAuthenticated
    },
    {
        path: "/rented",
        name: "Rented",
        component: NotReturned,
        beforeEnter: isAuthenticated
    }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
