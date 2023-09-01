package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @JsonIgnore
    @JoinColumn(name = "ROUTINE_SQ")
    private Routine routine;

    @JsonIgnore
    @OneToMany(mappedBy = "workOutElement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutSet> workoutSetList;
}
