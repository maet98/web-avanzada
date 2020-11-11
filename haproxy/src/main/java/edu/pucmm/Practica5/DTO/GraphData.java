package edu.pucmm.Practica5.DTO;

public class GraphData {
    private Long id;
    private Integer value;
    private Long quantity;

    public GraphData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public GraphData(Long id, Integer value, Long quantity) {
        this.id = id;
        this.value = value;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "GraphData{" +
                "id=" + this.id +
                ", value=" + this.value +
                ", quantity=" + this.quantity +
                '}';
    }
}
