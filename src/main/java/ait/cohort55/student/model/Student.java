package ait.cohort55.student.model;

import lombok.Getter;

import java.util.*;

public class Student {
    private long id;
    @Getter
    private String name;
    @Getter
    private String password;
    private Map<String, Integer> scores;

    public Student(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        scores = new HashMap<>();
    }
    public boolean addScore(String exam, int score) {
        return scores.put(exam, score) == null;
    }

}
