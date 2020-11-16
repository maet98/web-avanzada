<template>
  <div class="card text-center">
    <div class="d-flex justify-content-center" v-if="loading">
      <div class="spinner-border" role="status">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else>
      <div class="card-header">
        <h2>{{ $t("ListEquiment") }} :</h2>
      </div>
      <div class="card-body">
        <div class="text-right" style="margin: 12px">
          <button
            v-on:click="showModal = true"
            class="btn btn-primary text-right"
          >
            {{ $t("NewEquiment") }}
          </button>
        </div>
        <Modal
          v-if="showModal"
          :add="add"
          :handleModal="changeModal"
          title="Equiment"
        >
          <EquimentForm :update="update" :equiment="equiment" />
        </Modal>
        <table class="table ">
          <thead class="thead-dark">
            <tr>
              <th>{{ $t("Id") }}</th>
              <th>{{ $t("Name") }}</th>
              <th>{{ $t("Cost") }}</th>
              <th>{{ $t("Quantity") }}</th>
              <th>{{ $t("Photo") }}</th>
              <th>{{ $t("Actions") }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(equiment, index) in equiments" :key="equiment.id">
              <td>{{ equiment.id }}</td>
              <td>{{ equiment.name }}</td>
              <td>{{ equiment.cost }}</td>
              <td>{{ equiment.quantity }}</td>
              <td><img :src="equiment.image" width="100" height="150" /></td>
              <td><button @click="enableUpdate(index)" class="btn btn-warning">Update </button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import EquimentForm from "../components/EquimentForm";
import Modal from "../components/Modal";

export default {
  name: "EquimentList",
  components: {
    EquimentForm,
      Modal
  },
  data() {
    return {
      equiments: null,
      showModal: false,
      update: false,
      loading: false,
      equiment: {
        name: "",
        image: null,
        cost: 0.0,
        quantity: 0
      }
    };
  },
  mounted() {
        this.getEquiment();
        this.clean();
  },
  methods: {
    getEquiment() {
      window.axios
        .get("/equiment")
        .then(response => {
          this.equiments = response.data;
          console.log(response.data);
        })
        .catch(() => {});
    },
    clean() {
      this.equiment = {
        name: "",
        image: null,
        cost: 0,
        quantity: 0
      };
        this.update = false;
    },
      add() {
          this.loading = true;
          var post = new FormData();
          post.append("name", this.equiment.name);
          post.append("cost", this.equiment.cost);
          post.append("quantity", this.equiment.quantity);
          if(this.equiment.image != null) {
              post.append("image",this.equiment.image);
          }
          const config = { headers: { 'Content-Type': 'multipart/form-data' } };
          console.log(this.equiment.image);

          if(!this.update){
              window.axios
                .post("/equiment", post, config)
                .then(res => {
                  this.showModal = false;
                  this.loading = false;
                  this.clean();
                  console.log(res.data);
                    this.getEquiment();
                })
                .catch(err => {
                  this.loading = false;
                  console.log(err);
                });
          } else {
              post.append("id",this.equiment.id);
              window.axios
                .put("/equiment/" + this.equiment.id, post, config)
                .then(res => {
                  this.showModal = false;
                  this.loading = false;
                  this.clean();
                  console.log(res.data);
                  this.getEquiment();  
                })
                .catch(err => {
                  this.loading = false;
                  console.log(err);
                });
          }
      },
      enableUpdate(selected) {
          this.equiment = this.equiments[selected];
          this.update = true;
          this.showModal = true;
      },
      changeModal(value) {
          this.showModal = value
      }
  }
};
</script>
