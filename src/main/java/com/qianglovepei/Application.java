package com.qianglovepei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // init environment for configuration center to support devops
        //
        SpringApplication.run(Application.class, args);
    }

}