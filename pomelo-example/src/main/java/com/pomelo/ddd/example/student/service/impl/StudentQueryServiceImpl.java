package com.pomelo.ddd.example.student.service.impl;

import com.pomelo.ddd.example.student.domain.student.entity.Student;
import com.pomelo.ddd.example.student.infrastructure.db.repository.StudentRepository;
import com.pomelo.ddd.example.student.service.StudentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 何刚
 * @date 2020/8/11 17:14
 */
@Service
public class StudentQueryServiceImpl implements StudentQueryService {


    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student query(String number) {

        return studentRepository.queryByNumber(number);



    }
}
