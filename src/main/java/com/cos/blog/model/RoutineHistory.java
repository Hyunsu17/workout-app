package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Table(name="TB_ROUTINE_HISTORY")
public class RoutineHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROUTINE_HISTORY_SQ")
    private int id;

    @Column(name="ROUTINE_HISTORY_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "WORKOUT_HISTORY_SQ")
    private WorkOutHistory workOutHistory;

    public RoutineHistory() {

    }
}
