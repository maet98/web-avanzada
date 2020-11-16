package edu.pucmm.Practica3.DTO;

import edu.pucmm.Practica3.Entities.Valoration;

import java.util.ArrayList;
import java.util.List;

public class ValorationCreationDTO {
    private List<Valoration> valorations = new ArrayList<>();

    public ValorationCreationDTO() {
        valorations = new ArrayList<>();
    }

    public void addValoration(Valoration valoration) {
        this.valorations.add(valoration);
    }

    public List<Valoration> getValorations() {

        return valorations;
    }

    public void setValorations(List<Valoration> valorations) {
        this.valorations = valorations;
    }
}
