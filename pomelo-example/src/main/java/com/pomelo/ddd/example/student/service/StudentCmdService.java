package com.pomelo.ddd.example.student.service;

import com.pomelo.ddd.example.student.domain.command.AttendLanguage;

/**
 * @author 何刚
 * @date 2020/8/11 17:14
 */
public interface StudentCmdService {


    void attend(AttendLanguage attendLanguage);

}
