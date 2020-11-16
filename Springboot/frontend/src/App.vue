<template>
  <div id="app">
    <div id="nav" v-if="authenticated() == true">
      <router-link to="/equiment">{{ $t("Equiment") }}</router-link> |
      <router-link to="/client">{{ $t("Client") }}</router-link> |
      <router-link to="/rental">{{ $t("Rental") }}</router-link> |
      <router-link to="/rented">{{ $t("NotReturned") }}</router-link>
      <I18nChanger />
      <button class="logout btn btn-danger" @click="logout">{{ $t("LogOut") }}</button>
    </div>
    <router-view />
  </div>
</template>

<script>
import I18nChanger from "./components/I18nChanger.vue";

export default {
  components: {
    I18nChanger
  },
  mounted() {
    window.axios.get("http://localhost:8080/api/lang").then(response => {
      this.$i18n.locale = response.data;
    });
    if (localStorage.getItem("token") == null) {
      this.$store.dispatch("toggle", false);
    }
  },
  methods: {
    logout: function() {
        this.$store.dispatch("toggle", false);
        this.$router.push({name: "Login"});

      },
      authenticated() {
          return this.$store.getters.isAuthenticated;
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

.logout {
    position: absolute;
    top: 0px;
    right: 0px;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
