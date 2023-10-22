package com.cos.blog.repository;

import com.cos.blog.model.HistoryOfWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryOfWorkoutRepository extends JpaRepository<HistoryOfWorkout,Long> {
}
