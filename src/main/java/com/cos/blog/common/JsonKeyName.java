package com.cos.blog.common;

public enum JsonKeyName {
    USER("user"),
    ROUTINE("routine"),
    WORKOUT("workoutList"),
    WORKOUT_NAME("workoutName"),
    SET("workoutSetData"),
    REP("reps"),
    STATUS("status"),
    WEIGHT("weight"),
    WORKOUTELEMENT("workoutElement")
    ;

    final private String name;
    public String getName(){
        return name;
    }

    JsonKeyName(String name) {
        this.name = name;
    }
}
