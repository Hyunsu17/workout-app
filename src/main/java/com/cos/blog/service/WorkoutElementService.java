package com.cos.blog.service;

import com.cos.blog.model.Routine;
import com.cos.blog.model.WorkoutElement;
import com.cos.blog.repository.WorkoutElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutElementService {
    private final WorkoutElementRepository workOutElementRepository;

    public List<WorkoutElement> getWorkOutElementByRoutine(Routine routine){
        return workOutElementRepository.findByRoutine(routine);
    }

}
