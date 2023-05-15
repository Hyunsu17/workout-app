package com.cos.blog.service;

import com.cos.blog.model.Routine;
import com.cos.blog.model.WorkOutElement;
import com.cos.blog.model.WorkOutSet;
import com.cos.blog.repository.WorkOutElementRepository;
import com.cos.blog.repository.WorkOutSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkOutSetService {

    private final WorkOutSetRepository workOutSetRepository;

    private final WorkOutElementRepository workOutElementRepository;

    public List<WorkOutSet> findAllSetByWorkOutElement(WorkOutElement workOutElement) {
        return workOutSetRepository.findAllByWorkOutElement(workOutElement);
    }
    public List<List<WorkOutSet>> findAllSetByRoutine(Routine routine){
        List<WorkOutElement> workOutElementsList = workOutElementRepository.findByRoutine(routine);
        List<List<WorkOutSet>> retList = new ArrayList<>();
        for(WorkOutElement idxWkElm: workOutElementsList){
            findAllSetByWorkOutElement(idxWkElm);
        }
    }
}
