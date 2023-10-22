package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "TB_HISTORY_WORKOUT")
public class HistoryOfWorkout {

    protected HistoryOfWorkout() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HISTORY_WORKOUT_SQ")
    private long historyId;

    @Column(name = "WORKOUT_DATE")
    private LocalDate workOutDate;

    @Column(name = "WORKOUT_TIME")
    private LocalTime workOutTime;

    @ManyToOne
    @JoinColumn(name = "USER_SQ")
    private User user;

    @OneToMany(mappedBy = "historyOfWorkout", cascade = CascadeType.ALL)
    private List<HistoryOfWorkoutElement> historyOfWorkoutElementList;

}
