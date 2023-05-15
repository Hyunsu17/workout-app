package com.cos.blog.repository;

import com.cos.blog.model.WorkOutElement;
import com.cos.blog.model.WorkOutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkOutSetRepository extends JpaRepository<WorkOutSet, Integer> {

    List<WorkOutSet> findAllByWorkOutElement(WorkOutElement workOutElement);
}
