package com.pomelo.ddd.example.student.domain.event;

import com.pomelo.ddd.example.student.domain.entity.Student;
import com.pomelo.ddd.example.student.domain.command.AttendLanguage;

public class FinishCourseEvent {


    private Student student;

    private AttendLanguage attendLanguage;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AttendLanguage getAttendLanguage() {
        return attendLanguage;
    }

    public void setAttendLanguage(AttendLanguage attendLanguage) {
        this.attendLanguage = attendLanguage;
    }
}
