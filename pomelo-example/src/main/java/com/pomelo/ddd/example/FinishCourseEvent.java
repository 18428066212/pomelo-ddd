package com.pomelo.ddd.example;

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
