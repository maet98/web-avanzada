import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
      isAuthenticated: true
  },
  mutations: {
      toggle (state, payload) {
          state.isAuthenticated = payload;
      }
  },
  actions: {
      toggle(state, payload) {
          if(!payload) {
              localStorage.removeItem("token");
          }
          state.commit('toggle',payload);
      }
  },
  modules: {},
    getters: {
        isAuthenticated: state => state.isAuthenticated,
    }
});
