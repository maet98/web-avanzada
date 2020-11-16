<template>
    <div>
        <div>
          <b-card
            :title="getName"
            :img-src="client.image"
            img-alt="Photo"
            img-top
            tag="article"
            style="max-width: 20rem;"
            class="mb-2 mx-auto"
          >
            <b-card-text>
                Email: {{ client.email }} 
            </b-card-text>
          </b-card>
        </div>
        <div class="accordion" role="tablist" v-if="rentals.length > 0">
            <b-card no-body class="mb-1" v-for="rental in rentals" :key="rental.id">
              <b-card-header header-tag="header" class="p-1" role="tab">
                  <b-button block v-b-toggle="'accordion-' + rental.id" variant="info"> Rental: {{ rental.id }} </b-button>
              </b-card-header>
              <b-collapse :id="'accordion-'+ rental.id" accordion="my-accordion" role="tabpanel">
                <b-card-body>
                    <b-card-text>Day of Rental: {{ rental.dateOfRental }} </b-card-text>
                    <b-card-text>Expected date of return: {{ rental.expectedReturnDate }} </b-card-text>
                    <b-card-text v-if="!rental.active">Received</b-card-text>
                    <b-card-text v-else>Not Received</b-card-text>
                    <b-table striped hover :items="rental.orderList"></b-table>
                </b-card-body>
              </b-collapse>
            </b-card>
        </div>
        <div v-else>
            <h1> There hasn't been rentals from {{ client.firstName + " " + client.lastName }} </h1>
        </div>
    </div>
</template>

<script>
export default {
  name: "ClientInfo",
  props: {
      Id: String
    },
    data() {
        return {
            rentals: [],
            client: null
        }
    },
    mounted() {
        this.getRentals();
        this.getClient();
    },
    methods: {
        getRentals: function() {
            window.axios.get("client/" + this.Id).then(ans => {
                this.client = ans.data
                console.log(this.client)
            }).catch(err => console.log(err));
        },
        getClient: function() {
            window.axios.get("rental/client/" + this.Id).then(ans => {
                this.rentals = ans.data
                this.parseData();
            }).catch(err => console.log(err));
        },
        parseData: function() {
            console.log(this.rentals.orderList);
            for(let i = 0;i < this.rentals.length;i++) {
                for(let j = 0;j < this.rentals[i].orderList.length;j++) {
                    var actual = this.rentals[i].orderList[j];
                    this.rentals[i].orderList[j].equiment = actual.equiment.name;
                }
            }
        }
    },
    computed: {
        getName() {
            if(this.client != null) {
                return this.client.firstName + " " + this.client.lastName;
            }
            return "";
        }
    }
};
</script>
