package com.pomelo.ddd.example.student.controller;

import com.pomelo.ddd.example.student.domain.student.command.AttendYuWenKe;
import com.pomelo.ddd.example.student.domain.student.entity.Student;
import com.pomelo.ddd.example.student.service.StudentCmdService;
import com.pomelo.ddd.example.student.service.StudentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 何刚
 * @date 2020/8/8 14:35
 */
@RequestMapping("/student")
@RestController
public class StudentController {


    @Autowired
    private StudentCmdService studentCmdService;


    @Autowired
    private StudentQueryService studentQueryService;

    @GetMapping("/{number}")
    public Student query(@PathVariable("number") String number) {
        return studentQueryService.query(number);
    }

    @PostMapping("/{number}/attendYuWen")
    public void attendYuWen(@PathVariable("number") String number, @RequestBody AttendYuWenKe attendYuWenKe) {

        attendYuWenKe.setStudentNumber(number);
        studentCmdService.attend(attendYuWenKe);

    }


}