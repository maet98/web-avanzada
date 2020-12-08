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
          @click="link"
        text
      >
      <span class="mr-2">{{text}}</span>
      </v-btn>
    </v-app-bar>

    <v-main>
        <router-view />
    </v-main>
  </v-app>
</template>

<script>

export default {
    name: "App",
    data: () => ({
        text: 'Registro Pasado'
    }),
    mounted() {
        console.log(this.$router.currentRoute);
    },
    methods: {
        link() {
            if(this.$router.currentRoute.name == "Past"){
                this.text = "Registro Pasado"
                this.$router.push({name:"Home"});
            } else {
                this.text = "Volver"
                this.$router.push({name:"Past"});
            }
        }
    },
};
</script>
