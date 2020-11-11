package edu.pucmm.Practica5.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rol> roles;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }
}
