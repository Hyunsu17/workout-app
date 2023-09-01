package com.cos.blog.service;

import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.model.WorkoutElement;
import com.cos.blog.model.WorkoutSet;
import com.cos.blog.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final WorkoutElementService workoutElementService;
    private  final WorkoutSetService workoutSetService;

    public List<Routine> getRoutineByUser(User user) {
        return routineRepository.findAllByUser(user);
    }

    public Routine getRoutineByUserAndName(User user, String name) {
        Routine routine = routineRepository.findByUserAndName(user, name).orElseThrow(()-> new EntityNotFoundException());
        return routine;
    }

    @Transactional
    public void saveRoutine(Routine routine, List<WorkoutElement> workoutElements, List<WorkoutSet> workoutSets) {
        routineRepository.save(routine);
        workoutElementService.saveMultipleElements(workoutElements);
        workoutSetService.saveMultipleSet(workoutSets);
    }
    public void deleteRoutine(Routine routine){

        routine.getWorkoutElementList().forEach(exercise -> exercise.getWorkoutSetList().clear());
        routine.getWorkoutElementList().clear();

        routineRepository.delete(routine);
    }

}
