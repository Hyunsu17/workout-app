package com.cos.blog.repository;

import com.cos.blog.model.Routine;
import com.cos.blog.model.WorkOutElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkOutElementRepository extends JpaRepository<WorkOutElement, Integer> {
    List<WorkOutElement> findByRoutine(Routine routine);
}
