package com.pomelo.ddd.example.biz.student.command;

import com.pomelo.ddd.core.annotation.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 何刚
 * @date 2020/8/8 14:38
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Command
public class Query {

    private String number;

}
