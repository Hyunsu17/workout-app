package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Table(name="TB_WORKOUT_ELEMENT")
public class WorkOutElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORKOUT_SQ")
    private int ID;

    @Column(name = "WORKOUT_NM")
    private String name;

    @Column(name = "WORKOUT_PART")
    private String part;

    @ManyToOne
    @JoinColumn(name = "ROUTINE_SQ")
    private Routine routine;


    protected WorkOutElement() {}
}
