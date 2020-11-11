package edu.pucmm.Practica6.Consumidor.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraphData {
    private List<Double> temperature;
    private List<Double> humidity;
    private List<Date> dates;

    public GraphData() {
        this.dates = new ArrayList<>();
        this.humidity = new ArrayList<>();
        this.temperature = new ArrayList<>();
    }

    public List<Double> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Double> temperature) {
        this.temperature = temperature;
    }

    public List<Double> getHumidity() {
        return humidity;
    }

    public void setHumidity(List<Double> humidity) {
        this.humidity = humidity;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
