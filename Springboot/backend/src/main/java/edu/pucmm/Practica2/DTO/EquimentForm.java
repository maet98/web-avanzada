package edu.pucmm.Practica2.DTO;

import org.springframework.web.multipart.MultipartFile;

public class EquimentForm {
    private String name;
    private Integer quantity;
    private Float cost;
    private MultipartFile image;

    private boolean good;

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

    public MultipartFile getImage() {
        return image;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}