<template>
  <v-row justify="start">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Agregar Reserva
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline text-center">Registro Reserva</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-text-field
              label="Id*"
              v-model="reserva.id"
              required
            ></v-text-field>
            <v-text-field
              label="Nombre*"
              v-model="reserva.nombre"
            ></v-text-field>
            <v-text-field
              label="Carrera*"
              v-model="reserva.carrera"
              required
            ></v-text-field>
            <v-autocomplete
                  :items="['Redes', 'Comunicacion', 'Computacion']"
                    v-model="reserva.laboratorio"
                  label="Laboratorio"
                ></v-autocomplete>
                <v-datetime-picker label="Fecha de Reserva*" v-model="reserva.fechaReserva" required> </v-datetime-picker>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            text
            @click="salir"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="addReserva"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
    import axios from "axios"
  export default {
    data: () => ({
      dialog: false,
        reserva: {
            id: '',
            nombre: '',
            carrera: '',
            laboratorio: '',
            fechaReserva: null
        },
        datetime: null
    }),
      methods: {
          addReserva() {
              console.log(this.reserva);
              axios.post("https://7ll2wokw6b.execute-api.us-east-1.amazonaws.com/default/Reservation", this.reserva).then(ans => {
                  this.clear();
                  this.dialog = false;
                  console.log(ans);
              })
          },
          clear() {
              this.reserva = {
                    id: '',
                    nombre: '',
                    carrera: '',
                    laboratorio: '',
                    fechaReserva: null
                }
          },
          salir() {
              this.clear();
              this.dialog = false;
          }
      }
  }
</script>
