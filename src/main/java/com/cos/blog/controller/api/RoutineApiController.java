package com.cos.blog.controller.api;

import com.cos.blog.common.JsonBinder;
import com.cos.blog.common.JsonKeyName;
import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.model.WorkoutElement;
import com.cos.blog.model.WorkoutSet;
import com.cos.blog.service.RoutineService;
import com.cos.blog.service.UserService;
import com.cos.blog.service.WorkoutElementService;
import com.cos.blog.service.WorkoutSetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoutineApiController {

    private final RoutineService routineService;
    private final WorkoutElementService workoutElementService;
    private final UserService userService;
    private final WorkoutSetService workoutSetService;

    @PostMapping("/routine")
    public ResponseEntity<Object> createRoutine(@RequestBody JsonNode saveObj) {
        Routine bufferedRoutine, routine;
        User bufferedUser, user;
        List<WorkoutElement> bufferedWorkoutElements;
        List<WorkoutElement> workoutElements = new ArrayList<>();
        List<WorkoutSet> bufferedWorkoutSets = new ArrayList<>();
        List<WorkoutSet> workoutSets = new ArrayList<>();

        try {
            bufferedUser = JsonBinder.JsonToModel(saveObj, User.class);
            user = userService.회원찾기(bufferedUser.getUsername());

            bufferedRoutine = JsonBinder.JsonToModel(saveObj, Routine.class);
            routine = Routine.builder().user(user)
                    .name(bufferedRoutine.getName())
                    .build();

            bufferedWorkoutElements = JsonBinder.JsonListToModelList(saveObj, WorkoutElement.class);
            bufferedWorkoutElements.forEach((element) -> {
                workoutElements.add(WorkoutElement.builder()
                        .workoutName(element.getWorkoutName())
                        .routine(routine)
                        .workoutSetList(new ArrayList<>())
                        .build());
            });

            JsonNode newNode = saveObj.get("workoutSet");
            for (JsonNode jsa : newNode) {
                String elementName = jsa.get("elementName").asText();
                int reps = jsa.get("reps").asInt();
                boolean status = jsa.get("status").asBoolean();
                int weight = jsa.get("weight").asInt();
                WorkoutElement workoutElement = WorkoutElement.builder().workoutName(elementName).build();
                bufferedWorkoutSets.add(WorkoutSet.builder().reps(reps).status(status).weight(weight).workOutElement(workoutElement).build());
            }

            bufferedWorkoutSets.forEach((element) -> {
                WorkoutElement temp = getElement(workoutElements, element.getWorkOutElement().getWorkoutName());
                temp.getWorkoutSetList().add(element);
                workoutSets.add(WorkoutSet.builder()
                        .workOutElement(temp)
                        .status(element.getStatus())
                        .reps(element.getReps())
                        .weight(element.getWeight())
                        .build());
            });

            routine.setWorkoutElementList(workoutElements);
            routineService.saveRoutine(routine, workoutElements, workoutSets);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Successfully Created");
    }

    public ResponseEntity<Object> deleteRoutine(String name){
        routineService.
    }

    private WorkoutElement getElement(List<WorkoutElement> workoutElements, String value){
        for (WorkoutElement item:
             workoutElements) {
            if(item.getWorkoutName().equalsIgnoreCase(value)){
                return item;
            }
        }
        throw new RuntimeException("couldn't find proper WorkoutElement");
    }



}
