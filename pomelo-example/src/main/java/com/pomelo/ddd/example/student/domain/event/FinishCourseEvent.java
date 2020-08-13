package com.pomelo.ddd.example.student.domain.event;

import com.pomelo.ddd.example.student.domain.entity.Student;
import com.pomelo.ddd.example.student.domain.command.AttendYuWenKe;

public class FinishCourseEvent {


    private Student student;

    private AttendYuWenKe attendYuWenKe;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AttendYuWenKe getAttendYuWenKe() {
        return attendYuWenKe;
    }

    public void setAttendYuWenKe(AttendYuWenKe attendYuWenKe) {
        this.attendYuWenKe = attendYuWenKe;
    }
}
