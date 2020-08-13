package com.pomelo.ddd.example.student.domain.event.handler;

import com.pomelo.ddd.core.annotation.EventHandler;
import com.pomelo.ddd.example.student.domain.event.FinishCourseEvent;
import com.pomelo.ddd.example.student.infrastructure.db.mysql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEventHandler {

    @Autowired
    private StudentRepository studentRepository;

    @EventHandler(FinishCourseEvent.class)
    public void handleFinishCourseEvent(FinishCourseEvent finishCourseEvent) {
        studentRepository.addScore(finishCourseEvent.getStudent().getNumber(), finishCourseEvent.getAttendYuWenKe().getScore());
    }

}
