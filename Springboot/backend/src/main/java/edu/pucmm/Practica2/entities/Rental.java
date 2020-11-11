package edu.pucmm.Practica2.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfRental;

    @ManyToOne
    @JoinColumn(name ="clientId")
    private Client client;

    private LocalDate expectedReturnDate;

    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MyOrder> orderList;

    public Rental() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(LocalDate dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<MyOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<MyOrder> orderList) {
        this.orderList = orderList;
    }

    public void addOrder(MyOrder order) {
        this.orderList.add(order);
    }

    public Rental( LocalDate dateOfRental, Client client, LocalDate expectedReturnDate, Boolean isActive) {
        this.orderList = new ArrayList<>();
        this.dateOfRental = dateOfRental;
        this.client = client;
        this.expectedReturnDate = expectedReturnDate;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", dateOfRental=" + dateOfRental +
                ", client=" + client +
                ", expectedReturnDate=" + expectedReturnDate +
                ", isActive=" + isActive +
                ", orderList=" + orderList.toString() +
                '}';
    }
}
