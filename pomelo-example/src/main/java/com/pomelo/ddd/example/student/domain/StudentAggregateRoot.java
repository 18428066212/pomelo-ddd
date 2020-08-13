package com.pomelo.ddd.example.student.domain;

import com.pomelo.ddd.core.annotation.AggregateRoot;
import com.pomelo.ddd.core.annotation.CommandHandler;
import com.pomelo.ddd.core.annotation.LoadMethod;
import com.pomelo.ddd.example.student.domain.command.AttendLanguage;
import com.pomelo.ddd.example.student.domain.entity.Student;
import com.pomelo.ddd.example.student.infrastructure.db.mysql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

@AggregateRoot
public class StudentAggregateRoot {


    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @LoadMethod
    public void load(String number) {
        this.student = studentRepository.queryByNumber(number);
    }


    @CommandHandler(AttendLanguage.class)
    public Student attendYuWenKe(AttendLanguage attendLanguage) {
        this.student.setInClass(true);
        studentRepository.addScore(this.student.getNumber(), attendLanguage.getScore());
        return this.student;
    }

}
