package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="TB_WORKOUT_ELEMENT")
public class WorkoutElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORKOUT_SQ")
    private int ID;

    @JsonProperty("workoutName")
    @Column(name = "WORKOUT_NM")
    private String workoutName;

    @ManyToOne
    @JoinColumn(name = "ROUTINE_SQ")
    private Routine routine;
}
