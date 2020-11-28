package edu.pucmm.model;

import javax.persistence.*;

import org.vaadin.stefan.fullcalendar.Entry;

import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean send;

    @ManyToOne
    @JoinColumn(name = "gerenteId")
    private Gerente gerente;

    public Event(String title, LocalDateTime start, LocalDateTime end) {
        this.send = false;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public Event() {
        this.send = false;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setSend() {
        this.send = true;
    }
}
