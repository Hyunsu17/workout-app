package com.cos.blog.controller;


import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.model.WorkOutElement;
import com.cos.blog.model.WorkOutSet;
import com.cos.blog.service.RoutineService;
import com.cos.blog.service.UserService;
import com.cos.blog.service.WorkOutElementService;
import com.cos.blog.service.WorkOutSetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//인즈이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 /이면 index.jsp만 허용
//static 이하에 있는 /js/**, /css/**, /image/** 허용

@RequiredArgsConstructor
@RestController
public class UserController {

    private final RoutineService routineService;
    private final UserService userService;
    private final WorkOutElementService workOutElementService;

    private final WorkOutSetService workOutSetService;

    @PostMapping("/test")
    public List<Routine> routineList(@RequestBody User user) {
        System.out.println("*************************************" + user.getUsername());
        User findedUser = userService.회원찾기(user.getUsername());
        for (Routine ro : routineService.getRoutine(findedUser)) {
            System.out.println(ro.getName());
        }
        return routineService.getRoutine(findedUser);
    }

    @PostMapping("/test2")
    public User test2(@RequestBody User user) {

        return user;
    }

    @PostMapping("/test3")
    public List<WorkOutElement> test3(@RequestBody JsonNode saveObj) {
        ObjectMapper mapper = new ObjectMapper();
        User user;
        Routine routine;
        try {
            user = mapper.treeToValue(saveObj.get("user"), User.class);
            routine = mapper.treeToValue(saveObj.get("routine"), Routine.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        User foundUser = userService.회원찾기(user.getUsername());
        Routine specificRoutine = routineService.getSpecificRoutineByName(foundUser, routine.getName());
        return workOutElementService.getWorkOutElementByRoutine(specificRoutine);
    }

    @PostMapping("/test4")
    public List<List<WorkOutSet>> test4(@RequestBody JsonNode saveObj) {
        ObjectMapper mapper = new ObjectMapper();
        User user;
        Routine routine;
        try {
            user = mapper.treeToValue(saveObj.get("user"), User.class);
            routine = mapper.treeToValue(saveObj.get("routine"), Routine.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        User foundUser = userService.회원찾기(user.getUsername());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@routine: "+routine.getName()+"@@@@@@@@@@@@@@@@@@@");
        Routine specificRoutine = routineService.getSpecificRoutineByName(foundUser, routine.getName());
        System.out.println(specificRoutine.toString());
        return workOutSetService.findAllSetByRoutine(specificRoutine);
    }

}
