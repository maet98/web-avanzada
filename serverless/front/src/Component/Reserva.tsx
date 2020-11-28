import React from "react";
import styles from "./Reserva.module.css";
import { Reservation } from "../Models/Reservation";
import { useForm} from "react-hook-form";

import {
    FormLabel,
    Select,
    TextField,
    FormGroup,
    MenuItem,
    Divider,
    Button,
    Grid
} from "@material-ui/core"
import DateFnsUtils from "@date-io/date-fns";
import {DateTimePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";

interface props {
    setOpen: Function;
}


export const Reserva = ({setOpen}:props) => {
    const [date,setDate] = React.useState<Date | null>(new Date("2018-01-01T00:00:00.000Z"));
    const { register, handleSubmit, watch } = useForm<Reservation>();

    console.log(watch("nombre"))

    const laboratorios = ["Redes","Computacion"]

    const onSubmit = (data: Reservation) => {
        console.log("data", data);  
    };

    return (
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <form className={styles.paper} onSubmit={handleSubmit(onSubmit)}>
                    <h3> Registro Reserva </h3>
                    <Divider />
                    <FormGroup className={styles.space}>
                        <input
                            id="id" 
                            name="id"
                            ref={register}/>
                    </FormGroup>
                    
                    <FormGroup className={styles.space}>
                        <input 
                            name="nombre"
                            ref={register}
                            id="nombre" 
                            />
                    </FormGroup>

                    <FormGroup className={styles.space}>
                        <input 
                            name="carrera"
                            id="carrera"
                            ref={register}
                            />
                    </FormGroup>

                    <FormGroup className={styles.space}>
                        <FormLabel > Laboratorio</FormLabel>
                        <Select id="laboratorio" name="Laboratorio" ref={register}>
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>
                            {
                                laboratorios.map((value:string) => {
                                    return (
                                        <MenuItem value={value}> { value } </MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </FormGroup>
                    <FormGroup className={styles.space}>
                        <DateTimePicker 
                        label="Hora" 
                        variant="inline" 
                        value={date} 
                        onChange={setDate}/>
                    </FormGroup>
                    <Grid container justify="space-evenly" className={styles.space}>
                        <Button variant="contained" color="primary" >
                            Ok
                        </Button>
                        <Button variant="contained" color="secondary" onClick={() => setOpen(false)} >
                            Cancel
                        </Button>
                    </Grid>
                </form>
            </MuiPickersUtilsProvider>
    );
}

