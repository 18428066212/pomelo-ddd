package com.pomelo.ddd.example;


import com.pomelo.ddd.example.biz.Main;
import com.pomelo.ddd.starter.annotation.PomeloBasePackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * TODO
 *
 * @author liangqiang
 * @date 2019/5/14 1:50 PM
 */

@PomeloBasePackage(basePackage = "com.pomelo.ddd.example")
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        ConfigurableApplicationContext run = springApplication.run();
        Main.mainForTest();
    }
}
