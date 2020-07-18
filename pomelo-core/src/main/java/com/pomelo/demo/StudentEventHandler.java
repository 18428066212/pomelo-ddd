package com.pomelo.demo;

import com.pomelo.ddd.annotation.EventHandler;

public class StudentEventHandler {

    @EventHandler(FinishCourseEvent.class)
    public void handleFinishCourseEvent(FinishCourseEvent finishCourseEvent) {
        new StudentRepository().addScore(finishCourseEvent.getStudent().getNumber(), finishCourseEvent.getAttendYuWenKe().getScore());
    }

}
