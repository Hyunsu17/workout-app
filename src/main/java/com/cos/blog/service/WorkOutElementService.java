package com.cos.blog.service;

import com.cos.blog.model.Routine;
import com.cos.blog.model.WorkOutElement;
import com.cos.blog.repository.WorkOutElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkOutElementService {
    private final WorkOutElementRepository workOutElementRepository;

    public List<WorkOutElement> getWorkOutElementByRoutine(Routine routine){
        return workOutElementRepository.findByRoutine(routine);
    }

}
