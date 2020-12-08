<template>
    <div>
        <h3 class="text-center">Reservas de Laboratorio-EICT </h3>
        <Loader v-if="loading" :dialog="loading"/>
      <v-data-table
        :headers="headers"
        :items="reservations"
        :item-key="reservationId"
        class="elevation-1"
      >
        <template v-slot:top>
          <v-toolbar
            flat
          >
            <v-dialog
              v-model="dialog"
              max-width="500px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  v-bind="attrs"
                  v-on="on"
                >
                  Registrar Reserva
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-text-field
                      label="Id*"
                      v-model="editedItem.id"
                      required
                    ></v-text-field>
                    <v-autocomplete
                        label="Cantidad*"
                        :items="Array.from({length:7}, (_,i) => i+1)"
                        v-model="editedItem.cantidad"
                        required
                        />
                    <v-text-field
                      label="Nombre*"
                      v-model="editedItem.nombre"
                    ></v-text-field>
                    <v-text-field
                      label="Carrera*"
                      v-model="editedItem.carrera"
                      required
                    ></v-text-field>
                    <v-autocomplete
                          :items="['Redes', 'Comunicacion', 'Computacion']"
                            v-model="editedItem.laboratorio"
                          label="Laboratorio"
                        ></v-autocomplete>
                        <v-datetime-picker label="Fecha de Reserva*" v-model="editedItem.fechaReserva" timeFormat="HH:mm" required> </v-datetime-picker>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="close"
                  >
                    Cancel
                  </v-btn>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="save"
                  >
                    Save
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-dialog v-model="dialogDelete" max-width="500px">
              <v-card>
                <v-card-title class="headline">Are you sure you want to delete this item?</v-card-title>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                  <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.fechaReserva="{ item }">
           <span>{{item.fechaReserva.toLocaleString()}}</span>
         </template>
        <template v-slot:item.actions="{ item }">
          <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
          >
            mdi-pencil
          </v-icon>
          <v-icon
            small
            @click="deleteItem(item)"
          >
            mdi-delete
          </v-icon>
        </template>
        <template v-slot:no-data>
            <h3>No hay Reservas</h3>
          <v-btn
            color="primary"
            @click="initialize"
          >
            Refresh
          </v-btn>
        </template>
      </v-data-table>
    </div>
</template>

<script>
import axios from "axios";
import Loader from "../components/Loader";

var URL = "https://7ll2wokw6b.execute-api.us-east-1.amazonaws.com/default/Reservation";

export default {
  name: "Home",
    components: {Loader},
    data: () => ({
        dialog: false,
        loading: false,
        dialogDelete: false,
        headers: [
          { text: 'Id', value: 'id' },
          { text: 'Nombre', value: 'nombre', sortable: true },
          { text: 'Carrera', value: 'carrera' },
          { text: 'Laboratorio', value: 'laboratorio' },
          { text: 'Cantidad', value: 'cantidad' },
          { text: 'Fecha Reserva', value: 'fechaReserva' },
          { text: 'Actions', value: 'actions', sortable: false },
        ],
        reservations: [],
        editedIndex: -1,
        editedItem: {
            id: '',
            nombre: '',
            carrera: '',
            laboratorio: '',
            cantidad: 1,
            fechaReserva: new Date()
        },
        defaultItem: {
            id: '',
            nombre: '',
            cantidad: 1,
            carrera: '',
            laboratorio: '',
            fechaReserva: new Date()
        }
    }),
    mounted() {
        this.getAll();
    },
    methods: {
        initialize() {
            this.getAll();
        },
        getAll() {
            this.loading = true;
            axios.get(URL + "?pasado=false").then(
                ans => {
                    this.loading = false;
                    console.log(ans.data.data.reservations);
                    this.reservations = ans.data.data.reservations;
                    for(let i =0;i < this.reservations.length ;i++){
                        this.reservations[i].fechaReserva = new Date(this.reservations[i].fechaReserva)
                    }
                }).catch( err =>{
                    console.log(err);
                    alert("Hubo un problema");
                    this.loading = false;
                })
        },
       editItem (item) {
            this.editedIndex = this.reservations.indexOf(item)
            this.editedItem = item
            console.log(this.editedItem);
            this.dialog = true
        },
      deleteItem (item) {
        this.editedIndex = this.reservations.indexOf(item)
        this.editedItem = item;
        this.dialogDelete = true
      },
      deleteItemConfirm () {
        this.loading = true;
          axios.delete(URL,{
              data: {
                  reservationId: this.editedItem.reservationId
              }
          }).then(ans => {
            this.loading = false;
            this.reservations.splice(this.editedIndex, 1)
            this.closeDelete()
        }).catch( err => {
            console.log(err);
            this.loading = false;
        })
      },
      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },
      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
                this.editedItem = this.defaultItem;
                this.editedIndex = -1
        })
      },

      save () {
          let verificar = this.verificar();
          if(verificar === "" ) {
              this.editedItem.fechaReserva.setMinutes = 0;
              this.editedItem.fechaReserva = this.editedItem.fechaReserva.toISOString().split('.')[0]+"Z";
              this.loading = true;
            if (this.editedIndex > -1) {
                axios.put(URL,this.editedItem)
                    .then(ans => {
                        this.loading = false;
                        this.editedItem.fechaReserva = new Date(this.editedItem.fechaReserva)
                        Object.assign(this.reservations[this.editedIndex], this.editedItem)
                        console.log(ans.data);
                        this.close();
                    })
                    .catch(err =>{
                        this.loading = false;
                        this.close();

                    });
            } else {
                axios.post(URL,this.editedItem)
                    .then(ans => {
                        this.editedItem.fechaReserva = new Date(this.editedItem.fechaReserva);
                        this.reservations.push(this.editedItem)
                        this.loading = false;
                        console.log(ans.data);
                        this.close()
                    })
                    .catch(err =>{
                        this.loading = false;
                        this.close()
                    });
            }
              console.log(this.editedItem);
          } else {
              alert(verificar);
          }
        },
        verificar() {
            var date = this.editedItem.fechaReserva;
            let hour = date.getHours();
            if(hour >= 8 && hour <= 21) {
                for(let i = 0;i < this.reservations.length;i++) {
                    if(this.editedIndex == i) continue;
                    if(this.reservations[i].fechaReserva.getTime() === date.getTime()) {
                        return "Ya hay una reservacion a esa hora";
                    }
                }
                return "";
            } else {
                return "Esta fuera de las horas disponibles";
            }
        }
    },
    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'Registrar Reserva' : 'Editar Reserva'
        }
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },
};
</script>
