package com.pomelo.ddd.example.student.domain.student;

import com.pomelo.ddd.core.annotation.Aggregate;
import com.pomelo.ddd.core.annotation.CommandHandler;
import com.pomelo.ddd.core.annotation.LoadMethod;
import com.pomelo.ddd.example.student.domain.student.command.AttendYuWenKe;
import com.pomelo.ddd.example.student.domain.student.entity.Student;
import com.pomelo.ddd.example.student.infrastructure.db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Aggregate
public class StudentAggregate {


    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @LoadMethod
    public void load(String number) {
        this.student = studentRepository.queryByNumber(number);
    }


    @CommandHandler(AttendYuWenKe.class)
    public Student attendYuWenKe(AttendYuWenKe attendYuWenKe) {
        this.student.setInClass(true);
        return this.student;
    }

    public Student getStudent() {
        return this.student;
    }


}
