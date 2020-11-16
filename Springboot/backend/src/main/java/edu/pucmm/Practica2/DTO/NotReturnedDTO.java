package edu.pucmm.Practica2.DTO;

public class NotReturnedDTO {
    private Long id;
    private Integer quantity;
    private String name;
    private String client;
    private Long days;

    public NotReturnedDTO(Long id, Integer quantity, String name, String client, Long days) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.client = client;
        this.days = days;
    }

    public NotReturnedDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}
