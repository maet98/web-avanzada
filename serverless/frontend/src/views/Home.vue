<template>
    <div>
        <h3 class="text-center">Reservas de Laboratorio-EICT </h3>
        <Loader v-if="loading" :dialog="loading"/>
      <v-data-table
        :headers="headers"
        :items="reservations"
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
                        <v-datetime-picker label="Fecha de Reserva*" v-model="editedItem.fechaReserva" required> </v-datetime-picker>
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
            fechaReserva: null
        },
        defaultItem: {
            id: '',
            nombre: '',
            carrera: '',
            laboratorio: '',
            fechaReserva: null
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
            axios.get("https://7ll2wokw6b.execute-api.us-east-1.amazonaws.com/default/Reservation").then(
                ans => {
                    this.loading = false;
                    console.log(ans.data.data.reservations);
                    this.reservations = ans.data.data.reservations;
                }).catch( err =>{
                    console.log(err);
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
          axios.delete("https://7ll2wokw6b.execute-api.us-east-1.amazonaws.com/default/Reservation",{
              data: {
                  id: this.editedItem.id
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
        if (this.editedIndex > -1) {
          Object.assign(this.reservations[this.editedIndex], this.editedItem)
        } else {
          this.reservations.push(this.editedItem)
        }
          console.log(this.editedItem);
        this.close()
      },
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
