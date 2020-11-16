package edu.pucmm.Practica3.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Questionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "questionary", fetch = FetchType.LAZY)
    private List<Valoration> valorations;

    public Questionary() {
    }

    public Questionary(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public List<Valoration> getValorations() {
        return valorations;
    }

    public void setValorations(List<Valoration> valorations) {
        this.valorations = valorations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
