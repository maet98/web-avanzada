<template>
  <div class="card text-center">
    <div class="d-flex justify-content-center" v-if="loading">
      <div class="spinner-border" role="status">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else>
      <div class="card-header">
        <h2>{{ $t("ListRental") }} :</h2>
      </div>
      <div class="card-body">
        <div class="text-right" style="margin: 12px">
          <button
            v-on:click="showModal = true"
            class="btn btn-primary text-right"
          >
            {{ $t("NewRental") }}
          </button>
        </div>
        <Modal
          v-if="showModal"
          :add="add"
          :handleModal="changeModal"
          title="Client"
        >
          <RentalForm :update="update" :user="user" />
        </Modal>
        <table class="table ">
          <thead class="thead-dark">
            <tr>
              <th>{{ $t("Id") }}</th>
              <th>{{ $t("Client") }}</th>
              <th>{{ $t("Cost") }}</th>
              <th>{{ $t("Quantity") }}</th>
              <th>{{ $t("Photo") }}</th>
              <th>{{ $t("Actions") }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="equiment in equiments" :key="equiment.id">
              <td>{{ equiment.id }}</td>
              <td>{{ equiment.name }}</td>
              <td>{{ equiment.cost }}</td>
              <td>{{ equiment.quantity }}</td>
              <td><img :src="equiment.image" width="100" height="150" /></td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import RentalForm from "../components/RentalForm";
import Modal from "../components/Modal";

export default {
  components: {
    Modal,
    RentalForm
  }
};
</script>
