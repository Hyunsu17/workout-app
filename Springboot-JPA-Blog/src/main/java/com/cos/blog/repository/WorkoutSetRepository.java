package com.cos.blog.repository;

import com.cos.blog.model.WorkoutElement;
import com.cos.blog.model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Integer> {

    List<WorkoutSet> findAllByWorkoutElement(WorkoutElement workOutElement);
}
