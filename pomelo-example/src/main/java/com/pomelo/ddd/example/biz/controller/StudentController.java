package com.pomelo.ddd.example.biz.controller;

import com.pomelo.ddd.core.Pomelo;
import com.pomelo.ddd.core.utils.PomeloUtil;
import com.pomelo.ddd.example.biz.student.StudentAggregate;
import com.pomelo.ddd.example.biz.student.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 何刚
 * @date 2020/8/8 14:35
 */
@RequestMapping("/student")
@RestController
public class StudentController {


    @GetMapping("/{number}")
    public Student query(@PathVariable("number") String number) {
        Pomelo<StudentAggregate> pomelo = PomeloUtil.peel(StudentAggregate.class);

        StudentAggregate studentAggregate =
                pomelo.load(number)
                        .stop();

        return studentAggregate.getStudent();
    }


}
