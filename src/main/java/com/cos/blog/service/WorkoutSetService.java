package com.cos.blog.service;

import com.cos.blog.model.Routine;
import com.cos.blog.model.WorkoutElement;
import com.cos.blog.model.WorkoutSet;
import com.cos.blog.repository.WorkoutElementRepository;
import com.cos.blog.repository.WorkoutSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutSetService {

    private final WorkoutSetRepository workOutSetRepository;

    private final WorkoutElementRepository workOutElementRepository;

    private List<WorkoutSet> findAllSetByWorkOutElement(WorkoutElement workOutElement) {
        return workOutSetRepository.findAllByWorkOutElement(workOutElement);
    }
    public List<List<WorkoutSet>> findAllSetByRoutine(Routine routine){
        List<WorkoutElement> workoutElementsList = workOutElementRepository.findByRoutine(routine);
        List<List<WorkoutSet>> retList = new ArrayList<>();
        for(WorkoutElement idxWkElm: workoutElementsList){
            List<WorkoutSet> workoutSetList = findAllSetByWorkOutElement(idxWkElm);
            retList.add(workoutSetList);
        }
        return retList;
    }

    public void saveMultipleSet(List<WorkoutSet> workoutSets){
        workOutSetRepository.saveAll(workoutSets);
    }
}
