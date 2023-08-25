package com.cos.blog.controller.api;

import com.cos.blog.common.JsonKeyName;
import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.service.RoutineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RoutineApiController {

    private final RoutineService routineService;

    @PostMapping("/routine")
    public ResponseEntity<Object> createRoutine(@RequestBody JsonNode saveObj) {
        return null;
    }






}
