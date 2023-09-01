package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Table(name = "TB_WORKOUT_SET")
public class WorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SET_SQ")
    private int Id;

    @Column(name = "REPS")
    private int reps;

    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "SET_ST")
    private boolean status;

    @JsonIgnore
    @ManyToOne
    private WorkoutElement workoutElement;

    protected WorkoutSet() {}

    public boolean getStatus(){
        return this.status;
    }

}
