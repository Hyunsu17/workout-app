package com.cos.blog.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Entity
@Table(name = "TB_WORKOUT_HISTORY")
public class WorkOutHistory {

    protected WorkOutHistory() {
    }

    @Id
    private long historyId;

    @Column(name = "WORKOUT_DATE")
    private LocalDate workOutDate;

    @Column(name = "WORKOUT_TIME")
    private LocalTime workOutTime;

    @ManyToOne
    @JoinColumn(name = "USER_SQ")
    private User user;

}
