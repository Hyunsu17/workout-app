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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RoutineApiController {

    private final RoutineService routineService;
    private final WorkoutElementService workoutElementService;
    private final UserService userService;
    private final WorkoutSetService workoutSetService;

    @PostMapping("/routine")
    public ResponseEntity<Object> saveRoutine(@RequestBody JsonNode saveObj) {
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


            routine =routineService.getOrCreateRoutineByUserAndName(user, bufferedRoutine.getName());

            bufferedWorkoutElements = JsonBinder.JsonListToModelList(saveObj, WorkoutElement.class);
            bufferedWorkoutElements.forEach((element) -> workoutElements.add(WorkoutElement.builder()
                    .workoutName(element.getWorkoutName())
                    .routine(routine)
                    .workoutSetList(new ArrayList<>())
                    .build()));

            JsonNode newNode = saveObj.get("workoutSet");
            for (JsonNode jsa : newNode) {
                String elementName = jsa.get("elementName").asText();
                int reps = jsa.get("reps").asInt();
                boolean status = jsa.get("status").asBoolean();
                int weight = jsa.get("weight").asInt();
                WorkoutElement workoutElement = WorkoutElement.builder().workoutName(elementName).build();
                bufferedWorkoutSets.add(WorkoutSet.builder().reps(reps).status(status).weight(weight).workoutElement(workoutElement).build());
            }

            bufferedWorkoutSets.forEach((element) -> {
                WorkoutElement temp = getElement(workoutElements, element.getWorkoutElement().getWorkoutName());
                WorkoutSet workoutSet = WorkoutSet.builder()
                        .workoutElement(temp)
                        .status(element.getStatus())
                        .reps(element.getReps())
                        .weight(element.getWeight())
                        .build();
                temp.getWorkoutSetList().add(workoutSet);
                workoutSets.add(workoutSet);
            });

            routine.clearWorkoutList();
            routine.addAllToElementList(workoutElements);
            routineService.saveRoutine(routine, workoutElements, workoutSets);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Successfully saved!");
    }

    @DeleteMapping("/routine")
    public ResponseEntity<Object> deleteRoutine(@RequestParam String userName,@RequestParam String routineName){
        try{
            User user = userService.회원찾기(userName);
            Routine targetRoutine = routineService.getRoutineByUserAndName(user, routineName);
            routineService.deleteRoutine(targetRoutine);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("there is no Routine named like"+ routineName);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Sorry...There is some Error");
        }
        return ResponseEntity.ok().body("Successfully Deleted");
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
