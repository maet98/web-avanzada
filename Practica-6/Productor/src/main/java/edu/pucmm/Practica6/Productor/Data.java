package edu.pucmm.Practica6.Productor;


import java.util.Date;
import java.util.Random;

public class Data {
    private Date FechaGeneracion;
    private Double Temperatura;
    private Double Humedad;
    private Integer IdDispositivo;

    public Data(Integer id ) {
        this.FechaGeneracion = new Date();
        Random random = new Random();
        this.Humedad = random.nextInt(100) + random.nextDouble()* 10;
        this.Temperatura = random.nextInt(100) + random.nextDouble()* 10;
        this.IdDispositivo = id;
        this.Humedad = Math.floor(this.Humedad * 100) / 100;
        this.Temperatura = Math.floor(this.Temperatura * 100) / 100;
    }

    @Override
    public String toString() {
        return "Data{" +
                "FechaGeneracion=" + FechaGeneracion +
                ", Temperatura=" + Temperatura +
                ", Humedad=" + Humedad +
                ", IdDispositivo=" + IdDispositivo +
                '}';
    }
}
