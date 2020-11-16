package edu.pucmm.Practica2.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equimentId")
    private Equiment equiment;

    private Integer quantity;
    private Integer OriginalQuantity;

    @ManyToOne
    @JsonIgnore
    private Rental rental;

    public MyOrder(Equiment equiment,Integer Original, Integer quantity, Rental rental) {
        this.equiment = equiment;
        this.quantity = quantity;
        this.rental = rental;
        this.OriginalQuantity = Original;
    }

    public MyOrder() {

    }

    public Integer getOriginalQuantity() {
        return OriginalQuantity;
    }

    public void setOriginalQuantity(Integer originalQuantity) {
        OriginalQuantity = originalQuantity;
    }

    public Long getId() {
        return id;
    }

    public Equiment getEquiment() {
        return equiment;
    }

    public void setEquiment(Equiment equiment) {
        this.equiment = equiment;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public String toString() {
        return "MyOrder{" +
                "id=" + id +
                ", equiment=" + equiment +
                ", quantity=" + quantity +
                ", rental=" + rental +
                '}';
    }
}
