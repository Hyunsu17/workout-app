package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Table(name = "TB_HISTORY_WORKOUT_SET")
public class HistoryOfWorkoutSet {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SET_SQ")
    private int Id;

    @Column(name = "REPS")
    private int reps;

    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "SET_ST")
    private boolean status;

    @ManyToOne
    private WorkoutElement workOutElement;

    protected HistoryOfWorkoutSet() {
    }
}
