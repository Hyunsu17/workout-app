package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Table(name="TB_WORKOUT_ELEMENT_HISTORY")
public class WorkOutElementHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WORKOUT_ELEMENT_HISTORY_SQ")
    private int id;

    @Column(name = "WORKOUT_HISTORY_NM")
    private String name;

    @Column(name = "WORKOUT_HISTORY_PART")
    private String part;

    @JoinColumn(name = "ROUTINE_HISTORY_SQ")
    @ManyToOne
    private RoutineHistory routineHistory;

    public WorkOutElementHistory() {

    }
}
