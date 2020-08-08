package com.pomelo.ddd.example;


import com.pomelo.ddd.starter.annotation.PomeloBasePackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@PomeloBasePackage(basePackage = "com.pomelo.ddd.example")
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        ConfigurableApplicationContext run = springApplication.run();
    }
}
