package com.cos.blog.model;

import lombok.Builder;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "TB_WORKOUT_ROUTINE")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUTINE_SQ")
    private int ID;

    @Column(name = "ROUTINE_NM")
    private String name;

    @OneToOne
    private WorkOutHistory workOutHistory;

    protected Routine() {

    }
}
