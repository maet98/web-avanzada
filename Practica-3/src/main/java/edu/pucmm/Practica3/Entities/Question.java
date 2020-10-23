package edu.pucmm.Practica3.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String value;

    private Boolean comment;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Valoration> valorations;

    public Question() {
    }

    public Question(String value, Boolean comment) {
        this.value = value;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Boolean getComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Valoration> getValorations() {
        return valorations;
    }

    public void setValorations(List<Valoration> valorations) {
        this.valorations = valorations;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
