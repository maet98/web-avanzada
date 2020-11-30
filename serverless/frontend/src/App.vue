<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="./assets/pucmm.png"
          transition="scale-transition"
          width="40"
        />

      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Registro Pasados</span>
      </v-btn>
    </v-app-bar>

    <v-main>
        <Home />
    </v-main>
  </v-app>
</template>

<script>
import Home from "./views/Home";

export default {
    name: "App",
    components: {Home},
    data: () => ({
    //
    })
};
</script>
