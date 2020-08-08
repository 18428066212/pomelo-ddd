package com.pomelo.ddd.example.biz.student.entity;


public class Student {

    public Student() {
    }

    public Student(String number, String name, int score) {
        this.number = number;
        this.name = name;
        this.score = score;
    }

    private String number;

    private String name;

    private int score;

    private boolean inClass;

    public boolean isInClass() {
        return inClass;
    }

    public void setInClass(boolean inClass) {
        this.inClass = inClass;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", inClass=" + inClass +
                '}';
    }
}
