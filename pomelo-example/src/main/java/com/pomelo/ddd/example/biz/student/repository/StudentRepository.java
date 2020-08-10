package com.pomelo.ddd.example.biz.student.repository;

import com.pomelo.ddd.example.biz.student.entity.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StudentRepository {


    public static Map<String, Student> studentMap = new HashMap<>();

    static {
        studentMap.put("1", new Student("1", "pomelo_1", 10));
        studentMap.put("2", new Student("2", "pomelo_2", 10));
    }

    public Student queryByNumber(String number) {
        return studentMap.get(number);
    }

    public void addScore(String number, int score) {

        Student student = studentMap.get(number);
        student.setScore(student.getScore() + score);

    }

}
