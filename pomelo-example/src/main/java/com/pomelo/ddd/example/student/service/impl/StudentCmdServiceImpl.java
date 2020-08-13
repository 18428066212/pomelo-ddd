package com.pomelo.ddd.example.student.service.impl;

import com.pomelo.ddd.core.enums.EventEmitWay;
import com.pomelo.ddd.core.event.Launcher;
import com.pomelo.ddd.core.utils.PomeloUtil;
import com.pomelo.ddd.example.student.domain.StudentAggregateRoot;
import com.pomelo.ddd.example.student.domain.command.AttendLanguage;
import com.pomelo.ddd.example.student.domain.entity.Student;
import com.pomelo.ddd.example.student.domain.event.FinishCourseEvent;
import com.pomelo.ddd.example.student.service.StudentCmdService;
import com.pomelo.ddd.example.student.service.StudentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 何刚
 * @date 2020/8/11 17:14
 */
@Service
public class StudentCmdServiceImpl implements StudentCmdService {


    @Autowired
    private StudentQueryService studentQueryService;

    /**
     * 命令服务进行，服务编排
     * 服务编排，可以使用Disruptor,支持多边形操作
     *
     * @param attendLanguage 命令
     */
    @Override
    public void attend(AttendLanguage attendLanguage) {


        Student queryStudent = studentQueryService.query(attendLanguage.getStudentNumber());
        if (queryStudent == null) {
            throw new RuntimeException("参数错误");
        }

        Student student = PomeloUtil.peel(StudentAggregateRoot.class)
                .load(attendLanguage.getStudentNumber())
                .command(attendLanguage);

        FinishCourseEvent finishCourseEvent = new FinishCourseEvent();
        finishCourseEvent.setStudent(student);
        finishCourseEvent.setAttendLanguage(attendLanguage);

        Launcher.emit(finishCourseEvent, EventEmitWay.ASYNC);


    }


}
