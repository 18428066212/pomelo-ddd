package com.pomelo.ddd.example.biz;

import com.pomelo.ddd.core.utils.PomeloUtil;
import com.pomelo.ddd.core.utils.EventHandleThreadPool;
import com.pomelo.ddd.example.biz.student.StudentAggregate;
import com.pomelo.ddd.example.biz.student.command.AttendYuWenKe;
import com.pomelo.ddd.example.biz.student.entity.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void mainForTest() {

        Student student = PomeloUtil.peel(StudentAggregate.class)
                .load("1")
                .command(
                        AttendYuWenKe
                                .builder()
                                .chapter("Chapter_1")
                                .score(2)
                                .build()
                );

        logger.info("over");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(student);

        EventHandleThreadPool.shutdown();
    }
}
