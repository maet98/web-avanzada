package com.example.serverless.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Reservation")
public class Reservation {

    @DynamoDBHashKey(attributeName="id")
    private int id;
    @DynamoDBAttribute(attributeName = "nombre")
    private String nombre;
    @DynamoDBAttribute(attributeName = "carrera")
    private String carrera;
    @DynamoDBAttribute(attributeName = "laboratorio")
    private String laboratorio;

    public Reservation(){

    }

    public Reservation(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Reservation(int id, String nombre, String carrera, String laboratorio) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.laboratorio = laboratorio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", carrera='" + carrera + '\'' +
                ", laboratorio='" + laboratorio + '\'' +
                '}';
    }
}
