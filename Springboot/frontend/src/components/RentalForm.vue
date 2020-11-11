<template>
    <div clas="Form card">
        <form @submit.prevent="add">
            <h1 class="card-header text-center">Rental Form</h1>
          <div>
              <div class="card-body container">

                <div class="container">
                    <div class="row">
                    
                        <div class="col-sm form-group">
                          <label for="dateOfRental">{{ $t("DateOfRental") }}:</label><br />
                          <b-form-datepicker v-model="rental.dateOfRental" id="dateOfRental" required></b-form-datepicker>
                        </div>


                        <div class="col-sm form-group">
                          <label for="expectedDate">{{ $t("ExpectedReturnDate") }}:</label><br />
                          <b-form-datepicker v-model="rental.expectedReturnDate" id="expectedDate" required></b-form-datepicker>
                        </div>

                        <div class="col-lg form-group">
                          <label for="client">{{ $t("Client") }}:</label>
                          <select class="form-control" id="client" v-model="rental.client">
                            <option
                              v-for="client in clients"
                              :key="client.cedula"
                              :value="client.cedula"
                            >
                              {{ client.firstName + " " + client.lastName }}
                            </option>
                          </select>
                        </div>

                    </div>
                </div>


                <b-form-group label="Pick the equiments:">
                  <b-form-checkbox-group id="equiments" v-model="rental.selected" name="equiments">
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
                          v-model="rental.quantity[equiment.id]"
                          type="number"
                          :readonly="rental.selected.indexOf(equiment.id) == -1"
                          :max="equiment.quantity"
                        ></b-form-input>
                      </b-form-group>
                    </b-form-checkbox>
                  </b-form-checkbox-group>
                </b-form-group>
              </div>
          </div>
          <button class="btn btn-success btn-lg btn-block">Save</button>
        </form>
    </div>
</template>

<script>

export default {
    name: "RentalForm",
  data() {
    return {
      clients: null,
      equiments: null,
        rental: {
            client: 0,
            expectedReturnDate: null,
            dateOfRental: null,
            selected: [],
            quantity: {}
        }
    };
  },
  mounted() {
    this.getAllUsers();
    this.getEquiments();
  },
  methods: {
      add() {
          if(this.validation())
          {
              let selected = [];
              for( let i = 0;i < this.rental.selected.length; i++ ){
                  let actual = this.rental.selected[i];
                  selected.push({"id": actual, "quantity": this.rental.quantity[actual]});
              }
              var body = {
                  "clientId": this.rental.client,
                  "orderList": selected,
                  "dateOfRental": this.rental.dateOfRental,
                  "expectedReturnDate": this.rental.expectedReturnDate
              }
              window.axios.post("/rental",body)
                  .then(ans => {
                    this.$bvToast.toast(`Salvado`, { title: 'Retorno Realizado',
                          autoHideDelay: 5000,
                          appendToast: true,
                          variant: 'success'
                    })
                      this.$router.push("Rental");
                      console.log(ans.data)})
                .catch(err => {
                    this.$bvToast.toast(`Hubo un error con el servidor`, { title: 'Error',
                          autoHideDelay: 5000,
                          appendToast: true,
                            variant: 'danger'
                    })

                    console.log(err)});
          }
          else {
              this.$bvToast.toast("No se puedo ingresar la informacion como esta ingresada", {
                  title: "Mal ingresada",
                  variant: 'danger',
                  solid: true
              })
          }
      },
    getAllUsers() {
      window.axios
        .get("/client")
        .then(response => {
          this.clients = response.data;
          console.log(this.clients);
        })
        .catch(err => console.log(err));
    },
    getEquiments() {
      window.axios
        .get("/equiment")
        .then(response => {
          this.equiments = response.data;
          console.log(this.equiments);
        })
        .catch(err => console.log(err));
    },

    validation() {
        return this.validation_date1() && this.validation_date2() && this.validation_selected();
    },
    validation_date1() {
        return this.rental.dateOfRental != null;
    },
    validation_date2() {
        if(this.rental.dateOfRental != null && this.rental.expectedReturnDate != null) {
            var date1 = new Date(this.rental.dateOfRental);
            var date2 = new Date(this.rental.expectedReturnDate);
            return date1 < date2;
        } else {
            return false;
        }
    },
    validation_selected() {
        return this.rental.selected.length > 0;
    }
  },
};
</script>

<style>
    .Form {
        width: 70%;
    }
</style>
