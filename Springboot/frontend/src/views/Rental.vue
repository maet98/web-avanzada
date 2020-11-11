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
            v-on:click="changeToForm()"
            class="btn btn-primary text-right"
          >
            {{ $t("NewRental") }}
          </button>
        </div>
        <table class="table ">
          <thead class="thead-dark">
            <tr>
              <th>{{ $t("Id") }}</th>
              <th>{{ $t("Client") }}</th>
              <th>{{ $t("DateOfRental") }}</th>
              <th>{{ $t("ExpectedReturnDate") }}</th>
              <th>{{ $t("Actions") }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="rental in rentals" :key="rental.id">
              <td>{{ rental.id }}</td>
              <td> {{ formatName(rental.client) }} </td>
              <td>{{ getFormattedDate(rental.dateOfRental) }}</td>
              <td>{{ getFormattedDate(rental.expectedReturnDate) }}</td>
              <td>
                  <router-link :to="{ name: 'returnForm', params: { id : rental.id }}">
                    <v-btn text color="btn btn-secondary">
                        Return
                    </v-btn>
                    </router-link>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    data() {
        return {
            rentals: []
        }
    },
    mounted() {
        this.getRentals();
    },
    methods: {
        getRentals: function() {
            window.axios.get("/rental",{},{timeout: 2})
                .then(resp => {
                    this.rentals = resp.data;
                    console.log(this.rentals);
                }).catch(
                    err => {
                        console.log(err);
                    });
        },
        changeToForm: function() {
            this.$router.push("rentalForm");
        },
        formatName: function(client) {
            return client.firstName + " " + client.lastName;
        },
        getFormattedDate: function(stringDate) {
            var date = new Date(stringDate)
          var year = date.getFullYear();

          var month = (1 + date.getMonth()).toString();
          month = month.length > 1 ? month : '0' + month;

          var day = date.getDate().toString();
          day = day.length > 1 ? day : '0' + day;
          
          return month + '/' + day + '/' + year;
        }
    }
        
};
</script>
