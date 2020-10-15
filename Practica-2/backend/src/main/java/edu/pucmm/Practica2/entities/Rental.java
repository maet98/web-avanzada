package edu.pucmm.Practica2.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Rental {
    @Id
    @GeneratedValue
    private Long id;

    private Date dateOfRental;

    @ManyToOne
    @JoinColumn(name ="clientId")
    private Client client;

    private Date expectedReturnDate;

    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orderList;

    public Rental() {

    }

    public Rental(Long id, Client client, Date expectedReturnDate) {
        this.id = id;
        this.isActive = true;
        this.dateOfRental = new Date();
        this.client = client;
        this.expectedReturnDate = expectedReturnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(Date dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public List<Order> getOrdersList() {
        return orderList;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
