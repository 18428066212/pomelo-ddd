package com.pomelo.ddd.example.biz.student.event.handler;

import com.pomelo.ddd.core.annotation.EventHandler;
import com.pomelo.ddd.example.biz.student.event.FinishCourseEvent;
import com.pomelo.ddd.example.biz.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentEventHandler {

    @EventHandler(FinishCourseEvent.class)
    public void handleFinishCourseEvent(FinishCourseEvent finishCourseEvent) {
        new StudentRepository().addScore(finishCourseEvent.getStudent().getNumber(), finishCourseEvent.getAttendYuWenKe().getScore());
    }

}