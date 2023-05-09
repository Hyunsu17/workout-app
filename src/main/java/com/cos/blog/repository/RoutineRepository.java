package com.cos.blog.repository;

import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {
    public List<Routine> findAllByUser(User user);
}
