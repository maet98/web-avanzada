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

    @ManyToOne(optional = false)
    private User client;

    private Date expectedReturnDate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Orders> ordersList;

    public Rental() {

    }

    public Rental(Long id, Date dateOfRental, User client, Date expectedReturnDate) {
        this.id = id;
        this.dateOfRental = dateOfRental;
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }
}
