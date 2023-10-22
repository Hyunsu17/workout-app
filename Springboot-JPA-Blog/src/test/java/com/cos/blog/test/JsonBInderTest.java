package com.cos.blog.test;

import com.cos.blog.common.JsonBinder;
import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.cos.blog.model.WorkoutElement;
import com.cos.blog.model.WorkoutSet;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonBInderTest {

    String testJson = "{\n" +
            "  \"user\": {\n" +
            "    \"username\": \"test\"\n" +
            "  },\n" +
            "  \"routine\": {\n" +
            "    \"name\": \"'나만의 커스텀'\"\n" +
            "  },\n" +
            "  \"workoutElement\": [\n" +
            "    {\n" +
            "      \"workoutName\": \"rat pull down\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workoutName\": \"pull up\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"workoutName\": \"pull down\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"workoutSet\": [\n" +
            "    {\n" +
            "      \"elementName\": \"rat pull down\",\n" +
            "      \"reps\": \"1\",\n" +
            "      \"status\": \"true\",\n" +
            "      \"weight\": \"100\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"elementName\": \"rat pull down\",\n" +
            "      \"reps\": \"2\",\n" +
            "      \"status\": \"true\",\n" +
            "      \"weight\": \"100\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    @Autowired
    private UserService userService;

    @Test
    public void jsonTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(testJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode newNode = rootNode.get("workoutSet");
        List<WorkoutElement> bufferedWorkoutElements = new ArrayList<>();
        List<WorkoutElement> workoutElements = new ArrayList<>();
        bufferedWorkoutElements = JsonBinder.JsonListToModelList(rootNode, WorkoutElement.class);
        bufferedWorkoutElements.forEach((element) -> {
            workoutElements.add(WorkoutElement.builder()
                    .workoutName(element.getWorkoutName())
                    .routine(element.getRoutine())
                    .build());
        });

    }
}
