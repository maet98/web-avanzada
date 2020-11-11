package edu.pucmm.Practica5.Entities;

import javax.persistence.*;

@Entity
public class Valoration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="questionId")
    private Question question;

    private Integer value;

    @ManyToOne
    @JoinColumn(name="questionaryId")
    private Questionary questionary;

    @Column(length = 500)
    private String comment;

    public Valoration() {
    }

    public Valoration(Question question, Integer value, String comment) {
        this.comment = comment;
        this.question = question;
        this.value = value;
    }

    public Long getId() {
        return Id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Questionary getQuestionary() {
        return questionary;
    }

    public void setQuestionary(Questionary questionary) {
        this.questionary = questionary;
    }
}
