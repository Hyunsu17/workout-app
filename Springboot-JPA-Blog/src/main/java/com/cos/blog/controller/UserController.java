package com.cos.blog.controller;


import com.cos.blog.common.JsonBinder;
import com.cos.blog.model.*;
import com.cos.blog.service.RoutineService;
import com.cos.blog.service.UserService;
import com.cos.blog.service.WorkoutElementService;
import com.cos.blog.service.WorkoutSetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final WorkoutElementService workOutElementService;
    private final WorkoutSetService workOutSetService;

    @PostMapping("/test")
    public List<Routine> routineList(@RequestBody User user) {
        User foundUser = userService.회원찾기(user.getUsername());
        System.out.println(user.getUsername());
        return routineService.getRoutineByUser(foundUser);
    }

    @PostMapping("/test2")
    public User test2(@RequestBody User user) {

        return user;
    }





}
