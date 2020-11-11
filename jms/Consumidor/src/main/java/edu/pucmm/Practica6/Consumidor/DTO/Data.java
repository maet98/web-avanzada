package edu.pucmm.Practica6.Consumidor.DTO;

import java.util.Date;

public class Data {
    private Date FechaGeneracion;
    private Float Temperatura;
    private Float Humedad;
    private Integer IdDispositivo;

    public Data(Date fechaGeneracion, Float temperatura, Float humedad, Integer idDispositivo) {
        FechaGeneracion = fechaGeneracion;
        Temperatura = temperatura;
        Humedad = humedad;
        IdDispositivo = idDispositivo;
    }

    public Date getFechaGeneracion() {
        return FechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        FechaGeneracion = fechaGeneracion;
    }

    public Float getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(Float temperatura) {
        Temperatura = temperatura;
    }

    public Float getHumedad() {
        return Humedad;
    }

    public void setHumedad(Float humedad) {
        Humedad = humedad;
    }

    public Integer getIdDispositivo() {
        return IdDispositivo;
    }

    public void setIdDispositivo(Integer idDispositivo) {
        IdDispositivo = idDispositivo;
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
