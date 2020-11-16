package edu.pucmm.Practica2.entities;

import javax.persistence.*;

@Entity
public class Equiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;
    private Float cost;

    @Column(length = 10000000)
    private String image;

    public Equiment( String name, Integer quantity, Float cost, String image) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.image = image;
    }

    public Equiment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
