package com.example.demo_serverless;

import com.example.demo_serverless.funciones.FuncionesDynamoDbReservation;
import com.example.demo_serverless.model.Reservation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DemoServerlessApplication {

    public static void main(String[] args) {
//        System.out.println(System.getenv("AWS_ACCESS"));
//        System.out.println(System.getenv("AWS_SECRET"));
        FuncionesDynamoDbReservation funcionCRUDReservation = new FuncionesDynamoDbReservation();
        System.out.println(funcionCRUDReservation.listarReservations(null,null,false));
//        Reservation reservation = new Reservation();
//        reservation.setId("124");
//        reservation.setNombre("Miguel");
//        reservation.setCarrera("ISC");
//        reservation.setLaboratorio("Redes");
//        reservation.setFechaReserva("+2021-02-26T01:02:03Z");
//        System.out.println(funcionCRUDReservation.insertarReservationTable(reservation,null));
//        System.out.println(funcionCRUDReservation.listarReservations(null, null, false));
    }

}
