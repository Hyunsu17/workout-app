package com.cos.blog.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "TB_WORKOUT_ROUTINE")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUTINE_SQ")
    private int ID;

    @Column(name = "ROUTINE_NM", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "USER_SQ")
    private User user;

    @OneToMany(mappedBy = "routine" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutElement> workoutElementList;


    protected Routine() {

    }
}
