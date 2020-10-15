<template>
  <div>
    <div class="form-group">
      <label for="expectedDate">{{ $t("ExpectedReturnDate") }}:</label><br />
      <b-calendar id="expectedDate"></b-calendar>
    </div>
    <div class="form-group">
      <label for="client">{{ $t("Client") }}:</label>
      <select class="form-control" id="client">
        <option
          v-for="client in clients"
          :key="client.cedula"
          value="client.id"
        >
          {{ client.firstName + " " + client.lastName }}
        </option>
      </select>
    </div>

    <b-form-group label="Pick the equiments:">
      <b-form-checkbox-group id="equiments" v-model="selected" name="equiments">
        <b-form-checkbox
          v-for="equiment in equiments"
          :key="equiment.id"
          :value="equiment.id"
        >
          <b-form-group
            id="input-group-1"
            :label="equiment.name"
            label-for="equiment.id"
          >
            <b-form-input
              id="equiment.id"
              v-model="quantity[equiment.id]"
              type="number"
              :readonly="selected.indexOf(equiment.id) == -1"
              :max="equiment.quantity"
            ></b-form-input>
          </b-form-group>
        </b-form-checkbox>
      </b-form-checkbox-group>
    </b-form-group>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      clients: null,
      equiments: null,
      selected: [],
      quantity: {}
    };
  },
  mounted() {
    this.getAllUsers();
    this.getEquiments();
  },
  methods: {
    getAllUsers() {
      axios
        .get("http://localhost:8080/api/client")
        .then(response => {
          this.clients = response.data;
          console.log(this.clients);
        })
        .catch(err => console.log(err));
    },
    getEquiments() {
      axios
        .get("http://localhost:8080/api/equiment")
        .then(response => {
          this.equiments = response.data;
          console.log(this.equiments);
        })
        .catch(err => console.log(err));
    },
    add() {}
  }
};
</script>
