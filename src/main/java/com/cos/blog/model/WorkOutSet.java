package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Table(name = "TB_WORKOUT_SET")
public class WorkOutSet {

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

    @ManyToOne
    private WorkOutElement workOutElement;

    protected WorkOutSet() {
    }
}
