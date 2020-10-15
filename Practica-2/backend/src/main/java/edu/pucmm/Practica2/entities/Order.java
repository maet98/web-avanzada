package edu.pucmm.Practica2.entities;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equimentId")
    private Equiment equiment;

    private Integer Quantity;

    @ManyToOne
    @JoinColumn(name ="rentalId")
    private Rental rental;

    public Order(Equiment equiment, Integer quantity, Rental rental) {
        this.equiment = equiment;
        Quantity = quantity;
        this.rental = rental;
    }

    public Order() {

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

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
