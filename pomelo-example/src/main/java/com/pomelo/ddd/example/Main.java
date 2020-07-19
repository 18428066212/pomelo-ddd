package com.pomelo.ddd.example;

import com.pomelo.ddd.core.Pomelo;
import com.pomelo.ddd.core.utils.Scanner;
import com.pomelo.ddd.core.utils.ThreadPoolUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Scanner.scan("com.pomelo.ddd");

        Pomelo<StudentAggregate> pomelo = new Pomelo<>(StudentAggregate.class);

        AttendYuWenKe attendYuWenKe = new AttendYuWenKe();
        attendYuWenKe.setChapter("Chapter_1");
        attendYuWenKe.setScore(2);

        Student student = pomelo.load("1").send(attendYuWenKe);

        logger.info("over");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(student);

        ThreadPoolUtil.shutdown();

    }
}
