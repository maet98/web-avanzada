<template>
  <div id="app">
    <div id="nav" v-if="authenticated">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/equiment">{{ $t("Equiment") }}</router-link> |
      <router-link to="/client">{{ $t("Client") }}</router-link> |
      <router-link to="/rental">{{ $t("Rental") }}</router-link>
      <I18nChanger />
      <button class="btn btn-danger" @click="logout">Log out</button>
    </div>
    <router-view />
  </div>
</template>

<script>
import I18nChanger from "./components/I18nChanger.vue";
import axios from "axios";

export default {
  data() {
    return {
      authenticated: false
    };
  },
  components: {
    I18nChanger
  },
  mounted() {
    axios.get("http://localhost:8080/lang").then(response => {
      this.$i18n.locale = response.data;
    });
    if (localStorage.getItem("token") != null) {
      this.authenticated = true;
    }
  },
  methods: {
    logout: function() {
      localStorage.removeItem("token");
      this.authenticated = false;
      this.$store.push("/login");
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
