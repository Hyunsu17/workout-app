package com.cos.blog.service;

import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;

    public List<Routine> getRoutine(User user){
        return routineRepository.findAllByUser(user);
    }

    public Routine getSpecificRoutineByName(User user, String name){
        return routineRepository.findByUserAndName(user,name);
    }

}
