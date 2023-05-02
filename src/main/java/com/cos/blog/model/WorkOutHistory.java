package com.cos.blog.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Entity
public class WorkOutHistory {

    protected WorkOutHistory(){}

    @Id
    private long historyId;

    private LocalDate workOutDate;

    private LocalTime workOutTime;


}
