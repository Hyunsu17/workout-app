package com.cos.blog.controller.api;

import com.cos.blog.common.JsonBinder;
import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.model.WorkoutElement;
import com.cos.blog.model.WorkoutSet;
import com.cos.blog.service.RoutineService;
import com.cos.blog.service.UserService;
import com.cos.blog.service.WorkoutElementService;
import com.cos.blog.service.WorkoutSetService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutSetApiController {

    private final UserService userService;
    private final RoutineService routineService;
    private final WorkoutSetService workoutSetService;

    @PostMapping("/workoutSet")
    public List<List<WorkoutSet>> test4(@RequestBody JsonNode saveObj) {
        User user = userService.parsingJsonAndGetUser(saveObj);
        Routine specificRoutine = routineService.parsingJsonAndGetRoutineByUser(saveObj, user);
        return workoutSetService.findAllSetByRoutine(specificRoutine);
    }
}
