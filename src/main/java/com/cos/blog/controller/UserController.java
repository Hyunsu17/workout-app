package com.cos.blog.controller;



import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/test")
    public List<Routine> routineList(@RequestBody User user)
    {
        return routineService.getRoutine(user);
    }
    @PostMapping("/test2")
    public User test2(@RequestBody User user)
    {
        return user;
    }

}
