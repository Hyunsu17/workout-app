package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Table(name="TB_WORKOUT_SET_HISTORY")
public class WorkOutSetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WORKOUT_SET_HISTORY_SQ")
    private int id;

    @Column(name = "WEIGHT")
    private float weight;

    @Column(name = "REPS")
    private int reps;

    @ManyToOne
    @JoinColumn(name = "WORKOUT_ELEMENT_HISTORY_SQ")
    private WorkOutElementHistory workOutElementHistory;

    public WorkOutSetHistory() {

    }
}
