package edu.pucmm.Practica2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Family {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Family() {
    }

    public Family(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
