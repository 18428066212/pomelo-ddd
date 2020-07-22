package com.pomelo.ddd.example;

import com.pomelo.ddd.core.utils.PomeloUtil;
import com.pomelo.ddd.core.utils.Scanner;
import com.pomelo.ddd.core.utils.ThreadPoolUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    static {
        Scanner.scan("com.pomelo.ddd");
    }


    public static void main(String[] args) {


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

        ThreadPoolUtil.shutdown();

    }
}
