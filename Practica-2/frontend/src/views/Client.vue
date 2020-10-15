<template>
  <div class="card text-center">
    <div class="d-flex justify-content-center" v-if="loading">
      <div class="spinner-border" role="status">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else>
      <div class="card-header">
        <h2>{{ $t("ListClient") }} :</h2>
      </div>
      <div class="card-body">
        <div class="text-right" style="margin: 12px">
          <button
            v-on:click="showModal = true"
            class="btn btn-primary text-right"
          >
            {{ $t("NewClient") }}
          </button>
        </div>
        <Modal
          v-if="showModal"
          :add="add"
          :handleModal="changeModal"
          title="Client"
        >
          <ClientForm :update="update" :client="client" />
        </Modal>
        <table class="table ">
          <thead class="thead-dark">
            <tr>
              <th>ID</th>
              <th>{{ $t("firstName") }}</th>
              <th>{{ $t("LastName") }}</th>
              <th>{{ $t("Email") }}</th>
              <th>{{ $t("Photo") }}</th>
              <th>{{ $t("Actions") }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="client in clients" :key="client.id">
              <td>{{ client.cedula }}</td>
              <td>{{ client.firstName }}</td>
              <td>{{ client.lastName }}</td>
              <td>{{ client.email }}</td>
              <td><img :src="client.image" width="100" height="150" /></td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ClientForm from "../components/ClientForm";
import Modal from "../components/Modal";

export default {
  components: {
    ClientForm,
    Modal
  },
  name: "Client",
  data() {
    return {
      update: false,
      clients: null,
      showModal: false,
      loading: false,
      client: {
        cedula: "",
        firstName: "",
        lastName: "",
        email: "",
        image: null
      }
    };
  },
  mounted() {
    this.getUsers();
      window.axios.get("/lang").then(res => alert(res.data));
  },
  methods: {
    getUsers: function() {
      this.loading = true;
        alert(localStorage.getItem("token"));
      axios
            .get("http://localhost:8080/api/client",{
                headers:{
                    "Authorization" : localStorage.getItem("token")
                }
            })
        .then(response => {
          this.loading = false;
          this.clients = response.data;
            alert(response.data.headers['Authorization']);
        })
        .catch(err => {
          this.loading = false;
          console.log(err);
            console.log("error");
        });
    },
    add() {
      this.loading = true;
      var post = new FormData();
      post.append("firstName", this.client.firstName);
      post.append("lastName", this.client.lastName);
      post.append("cedula", this.client.cedula);
      post.append("email", this.client.email);
      post.append("image", this.client.image);
      window.axios
        .post("/client", post, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(res => {
          this.showModal = false;
          this.loading = false;
          this.clients.add(res.data);
          console.log(res.data);
        })
        .catch(err => {
          this.showModal = false;
          this.loading = false;
          console.log(err);
        });
    },
    clean() {
      this.client = {
        cedula: "",
        firstName: "",
        lastName: "",
        email: "",
        image: null
      };
    },
    changeModal: function(value) {
      this.showModal = value;
    }
  }
};
</script>
