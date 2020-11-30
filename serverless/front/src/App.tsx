import * as React from 'react';
import pucmm from './pucmm.png';
import './App.css';
import axios from "axios";
import styles from "./App.module.css";
import {Reservation} from "./Models/Reservation";
import { Reserva} from "./Component/Reserva";
import {
    Grid,
    Button,
    Modal
} from "@material-ui/core";

import {
    DataGrid,
    ColDef
} from "@material-ui/data-grid";

function App() {
    const [open,setOpen] = React.useState<boolean>(false);
    const [rows,setRows] = React.useState<Reservation[] | null>(null);
    const columns: ColDef[] = [
      { field: 'id', headerName: 'ID', width: 140 },
      { field: 'nombre', headerName: 'Nombre', width: 130 },
      { field: 'Laboratorio', headerName: 'Laboratorio', width: 130 },
      { field: 'fecha', headerName: 'Fecha', width: 130 }
    ];

    React.useEffect(() => {
        axios.get("https://7ll2wokw6b.execute-api.us-east-1.amazonaws.com/default/Reservation")
            .then(ans => {
                var reservations: Reservation[] = ans.data.data.reservations;
                console.log(reservations);
                setRows(reservations);
            })
            .catch(err => {
                console.log(err);
            });
    },[]);

    

  return (
    <div className="App">
        <Grid container direction="column" justify="space-evenly">
            <Grid container justify="space-around" direction="row">
                <Grid item >
                    <img src={pucmm} className={styles.logo} />
                </Grid>
                <Grid item >
                    <a href="#">Registros Pasados</a>
                </Grid>
            </Grid>
            <Grid item>
                <h1>Reservas de Laboratorio-EICT</h1>
            </Grid>
            <Grid container>
                <Button variant="contained" color="primary" onClick={() => setOpen(true)}>
                    Agregar Reserva
                </Button>
                <Modal open={open} onClose={() => setOpen(false)} >
                    <Reserva setOpen={setOpen} />
                    <DataGrid rows={rows} columns={columns} pageSize={5} checkboxSelection />
                </Modal>
            </Grid>
            <Grid item style={{ height: 400, width: '100%'}}>
                <h3>No hay ningun Laboratorio Reservado</h3>:
            </Grid>
        </Grid>
    </div>
  );
}

export default App;

//
