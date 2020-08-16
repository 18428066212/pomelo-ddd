package com.pomelo.ddd.example.student.controller;

import com.pomelo.ddd.example.student.domain.command.AttendLanguage;
import com.pomelo.ddd.example.student.domain.entity.Student;
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

    @PostMapping("/{number}/attendLanguage")
    public void attendLanguage(@PathVariable("number") String number, @RequestBody AttendLanguage attendLanguage) {

        attendLanguage.setStudentNumber(number);
        studentCmdService.attend(attendLanguage);

    }


}
