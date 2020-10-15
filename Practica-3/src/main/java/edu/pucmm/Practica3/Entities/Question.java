package edu.pucmm.Practica3.Entities;

import javax.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String value;

    private Boolean comment;

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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
