package com.cos.blog.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Entity
public class WorkOutHistory {

    @Id
    private long historyId;
    @Column(name = "WORKOUT_DATE")
    private LocalDate workOutDate;
    @Column(name = "WORKOUT_TIME")
    private LocalTime workOutTime;
    @ManyToOne
    @JoinColumn(name = "USER_SQ")
    private User user;

    protected WorkOutHistory() {
    }


}
