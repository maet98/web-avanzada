package edu.pucmm.Practica2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SubFamily {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Family family;

    public SubFamily() {
    }

    public SubFamily( String name, Family family) {
        this.name = name;
        this.family = family;
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

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
