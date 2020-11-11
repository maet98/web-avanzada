package edu.pucmm.Practica2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    private String cedula;

    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 10000000)
    private String image;

    public Client() {
    }

    public Client(String cedula, String firstName, String lastName, String email, String image) {
        this.cedula = cedula;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
