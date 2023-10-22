package com.cos.blog.controller.api;

import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.model.WorkoutElement;
import com.cos.blog.service.RoutineService;
import com.cos.blog.service.UserService;
import com.cos.blog.service.WorkoutElementService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutElementApiController {

    private final UserService userService;
    private final RoutineService routineService;
    private final WorkoutElementService workoutElementService;

    @PostMapping("/workoutElement")
    public List<WorkoutElement> test3(@RequestBody JsonNode saveObj) {
        User user = userService.parsingJsonAndGetUser(saveObj);
        Routine specificRoutine =  routineService.parsingJsonAndGetRoutineByUser(saveObj,user);
        return workoutElementService.getWorkOutElementByRoutine(specificRoutine);
    }
}
