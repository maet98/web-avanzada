<template>
    <v-row>
        <v-col
          cols="12"
          sm="6"
        >
          <v-date-picker
            v-model="dates"
            range
          ></v-date-picker>
        </v-col>
        <v-col
          cols="12"
          sm="6"
        >
          <v-text-field
            v-model="dateRangeText"
            label="Date range"
            prepend-icon="mdi-calendar"
            readonly
          ></v-text-field>
        </v-col>
            <v-col>
                <v-data-table
                    :items="show"
                    :headers="headers"
                    :items-per-page="5"
                    >
                    <template v-slot:item.fechaReserva="{ item }">
                       <span>{{item.fechaReserva.toLocaleString()}}</span>
                     </template>
                </v-data-table>
            </v-col>
      </v-row>
</template>

<script>
import axios from "axios"

let baseUrl = "https://7ll2wokw6b.execute-api.us-east-1.amazonaws.com/default/Reservation";

export default {
    name: "Past",
    data() {
        return {
            dates: [],
            past: [],
            headers: [
              { text: 'Id', value: 'id' },
              { text: 'Nombre', value: 'nombre', sortable: true },
              { text: 'Carrera', value: 'carrera' },
              { text: 'Laboratorio', value: 'laboratorio' },
              { text: 'Cantidad', value: 'cantidad' },
              { text: 'Fecha Reserva', value: 'fechaReserva' },
            ],
            show: []

        }
    },
    mounted() {
        this.getAll();
    },
    methods: {
        getAll() {
            axios.get(baseUrl+"?pasado=true").then(
                ans => {
                    this.past = ans.data.data.reservations;
                    for(let i =0;i < this.past.length ;i++){
                        this.past[i].fechaReserva = new Date(this.past[i].fechaReserva)
                    }
                this.show = this.past;
            })
        },
        selectedDate() {
            let ans = [];
            let inicio = new Date(this.dates[0])
            let fin = new Date(this.dates[1])
            if(fin < inicio) {
                let temp = inicio
                inicio = fin;
                fin = temp;
            }
            for(let i = 0;i < this.past.length;i++) {
                if(inicio <= this.past[i].fechaReserva && fin >= this.past[i].fechaReserva) {
                    ans.push(this.past[i]);
                }
            }
            this.show = ans;
        }
    },
    computed: {
        dateRangeText () {
            return this.dates.join(' ~ ')
          }
    },
    watch: {
        dates: function(val) {
            if(this.dates.length == 2) {
                this.selectedDate();
            } else  {
                this.show = this.past
            }
        }
    }
}

</script>
