package com.pomelo.ddd.example;

import com.pomelo.ddd.core.annotation.EventHandler;

public class StudentEventHandler {

    @EventHandler(FinishCourseEvent.class)
    public void handleFinishCourseEvent(FinishCourseEvent finishCourseEvent) {
        new StudentRepository().addScore(finishCourseEvent.getStudent().getNumber(), finishCourseEvent.getAttendYuWenKe().getScore());
    }

}
