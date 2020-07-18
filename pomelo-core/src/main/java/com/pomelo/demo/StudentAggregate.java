package com.pomelo.demo;

import com.pomelo.ddd.annotation.Aggregate;
import com.pomelo.ddd.annotation.Command;
import com.pomelo.ddd.annotation.LoadMethod;
import com.pomelo.ddd.enums.EventEmitWay;
import com.pomelo.ddd.event.Launcher;


@Aggregate
public class StudentAggregate {

    private Student student;

    @LoadMethod
    public void load(String number) {
        this.student = new StudentRepository().queryByNumber(number);
    }


    @Command(AttendYuWenKe.class)
    public Student attendYuWenKe(AttendYuWenKe attendYuWenKe) {

        if (this.student.isInClass()) {
            throw new RuntimeException("正在上课，不能参与新的课程");
        }
        this.student.setInClass(true);

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.student.setInClass(false);

        FinishCourseEvent finishCourseEvent = new FinishCourseEvent();
        finishCourseEvent.setStudent(this.student);
        finishCourseEvent.setAttendYuWenKe(attendYuWenKe);

        Launcher.emit(finishCourseEvent, EventEmitWay.SYNC);

        return this.student;
    }

    public Student getStudent() {
        return this.student;
    }


}
