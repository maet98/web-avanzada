<template>
  <form @submit.prevent="login">
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
  </form>
</template>

<script>
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
        .post("/auth", body)
        .then(resp => {
          const token = resp.data;
          localStorage.setItem("token", token);
          this.$router.push("/");
        })
        .catch(err => console.log(err));
    }
  }
};
</script>
