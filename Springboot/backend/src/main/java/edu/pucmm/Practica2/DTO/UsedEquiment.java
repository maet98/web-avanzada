package edu.pucmm.Practica2.DTO;

public class UsedEquiment {
    private Integer quantity;
    private String name;
    private Long id;

    public UsedEquiment(Integer quantity, String name, Long id) {
        this.quantity = quantity;
        this.name = name;
        this.id = id;
    }

    public UsedEquiment() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = this.quantity + quantity;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
