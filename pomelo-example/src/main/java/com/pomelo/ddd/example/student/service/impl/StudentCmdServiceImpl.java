package com.pomelo.ddd.example.student.service.impl;

import com.pomelo.ddd.core.enums.EventEmitWay;
import com.pomelo.ddd.core.event.Launcher;
import com.pomelo.ddd.core.utils.PomeloUtil;
import com.pomelo.ddd.example.student.domain.student.StudentAggregate;
import com.pomelo.ddd.example.student.domain.student.command.AttendYuWenKe;
import com.pomelo.ddd.example.student.domain.student.entity.Student;
import com.pomelo.ddd.example.student.domain.student.event.FinishCourseEvent;
import com.pomelo.ddd.example.student.service.StudentCmdService;
import org.springframework.stereotype.Service;

/**
 * @author 何刚
 * @date 2020/8/11 17:14
 */
@Service
public class StudentCmdServiceImpl implements StudentCmdService {


    /**
     * 用用服务进行，服务编排
     * 服务编排，可以使用Disruptor
     *
     * @param attendYuWenKe 命令
     */
    @Override
    public void attend(AttendYuWenKe attendYuWenKe) {


        Student student = PomeloUtil.peel(StudentAggregate.class)
                .load(attendYuWenKe.getStudentNumber())
                .command(attendYuWenKe);

        FinishCourseEvent finishCourseEvent = new FinishCourseEvent();
        finishCourseEvent.setStudent(student);
        finishCourseEvent.setAttendYuWenKe(attendYuWenKe);

        Launcher.emit(finishCourseEvent, EventEmitWay.ASYNC);


    }


}
