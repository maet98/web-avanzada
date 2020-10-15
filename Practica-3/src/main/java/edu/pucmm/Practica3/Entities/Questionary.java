package edu.pucmm.Practica3.Entities;

import javassist.compiler.ast.Pair;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Valoration {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Question question;

    private Integer value;
}
