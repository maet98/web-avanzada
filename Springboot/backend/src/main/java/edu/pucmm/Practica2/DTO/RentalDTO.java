package edu.pucmm.Practica2.DTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RentalDTO {
    private String clientId;
    private LocalDate expectedReturnDate ;
    private LocalDate dateOfRental;
    private List<OrderDTO> orderList;

    public RentalDTO(String clientId, LocalDate expectedReturnDate, LocalDate dateOfRental, List<OrderDTO> orderList) {
        this.clientId = clientId;
        this.expectedReturnDate = expectedReturnDate;
        this.dateOfRental = dateOfRental;
        this.orderList = orderList;
    }

    public RentalDTO() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(LocalDate dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }
}
