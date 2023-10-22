package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Table(name="TB_HISTORY_WORKOUT_ELEMENT")
public class HistoryOfWorkoutElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ELEMNT_SQ")
    private int ID;

    @Column(name = "WORKOUT_NM")
    private String name;

    @ManyToOne
    @JoinColumn(name = "HISTORY_WORKOUT_SQ")
    private HistoryOfWorkout historyOfWorkout;

    @OneToMany(mappedBy = "historyOfWorkoutElement", cascade = CascadeType.ALL)
    private List<HistoryOfWorkoutSet> historyOfWorkoutSetList;


    protected HistoryOfWorkoutElement() {}
}
