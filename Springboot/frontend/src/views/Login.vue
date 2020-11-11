<template>
  <form @submit.prevent="login" class="center">
        <b-card
            style="width: 30rem;"
          header="Login"
          header-tag="header"
        >
        <b-card-text>
            <div class="form-group">
              <label for="email">Email address</label>
              <input
                type="text"
                class="form-control"
                id="email"
                v-model="email"
                aria-describedby="emailHelp"
              />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input
                type="password"
                class="form-control"
                v-model="password"
                id="password"
              />
            </div>
            <div class="form-group form-check">
              <input
                type="checkbox"
                v-model="logged"
                class="form-check-input"
                id="exampleCheck1"
              />
              <label class="form-check-label" for="exampleCheck1">Keep Logged in</label>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </b-card-text>
        </b-card>
  </form>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      password: "",
      email: "",
      logged: false
    };
  },
  methods: {
    login: function() {
      const body = {
        email: this.email,
        password: this.password
      };
      window.axios
        .post("/user/signin", body)
        .then(resp => {
            const token = resp.data;
            localStorage.setItem("token", token);
            this.$store.dispatch("toggle", true);
            const instance = axios.create({
              baseURL: "http://localhost:8080/api",
                timeout: 1000,
                headers: { 'Authorization' : token }
            });

            window.axios = instance;

            this.$router.push("/");
        })
        .catch(err => console.log(err));
    }
  }
};
</script>

<style>
.center {
    display:flex;
    align-items: center;
    justify-content: center;
}

body {
    background-color: #D3D3D3;
}
</style>
