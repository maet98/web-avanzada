package edu.pucmm.Practica2.entities;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Equiment equiment;

    private Integer Quantity;

    @ManyToOne
    private Rental rental;

    public Orders(Equiment equiment, Integer quantity, Rental rental) {
        this.equiment = equiment;
        Quantity = quantity;
        this.rental = rental;
    }

    public Orders() {

    }

    public Long getId() {
        return id;
    }

    public Equiment getEquiment() {
        return equiment;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Rental getRental() {
        return rental;
    }

}
