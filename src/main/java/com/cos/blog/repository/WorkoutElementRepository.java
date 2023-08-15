package com.cos.blog.repository;

import com.cos.blog.model.Routine;
import com.cos.blog.model.WorkoutElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutElementRepository extends JpaRepository<WorkoutElement, Integer> {
    List<WorkoutElement> findByRoutine(Routine routine);
}
