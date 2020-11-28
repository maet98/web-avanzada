import * as React from 'react';
import pucmm from './pucmm.png';
import './App.css';
import styles from "./App.module.css";
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
    const [open,setOpen] = React.useState(false);
    const columns: ColDef[] = [
      { field: 'id', headerName: 'ID', width: 140 },
      { field: 'nombre', headerName: 'Nombre', width: 130 },
      { field: 'Laboratorio', headerName: 'Laboratorio', width: 130 },
      { field: 'fecha', headerName: 'Fecha', width: 130 }
    ];

    const rows = [
        {
            id: 1011111,
            nombre: "estudiante 1",
            Laboratorio: "REDES",
            fecha: ""
        },
        {
            id: 1123123,
            nombre: "Estudiante 2",
            Laboratorio: "Computacion",
            fecha: ""
        },
        {
            id: 12312323,
            nombre: "Estudiante 3",
            Laboratorio: "Computacion",
            fecha: ""
        }
    ];

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
                </Modal>
            </Grid>
            <Grid item style={{ height: 400, width: '100%'}}>
                <DataGrid rows={rows} columns={columns} pageSize={5} checkboxSelection />
            </Grid>
        </Grid>
    </div>
  );
}

export default App;
