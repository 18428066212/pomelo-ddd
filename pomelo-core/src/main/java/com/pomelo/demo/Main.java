package com.pomelo.demo;

import com.pomelo.ddd.Pomelo;
import com.pomelo.ddd.utils.Scanner;


public class Main {


    public static void main(String[] args) {

        Scanner.scan("com.pomelo.demo");

        Pomelo<StudentAggregate> pomelo = new Pomelo<>(StudentAggregate.class);

        AttendYuWenKe attendYuWenKe = new AttendYuWenKe();
        attendYuWenKe.setChapter("Chapter_1");
        attendYuWenKe.setScore(2);

        Student student = pomelo.load("1").command(attendYuWenKe);

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(student);


    }
}
