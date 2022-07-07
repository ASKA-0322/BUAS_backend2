package com.buas_team.buas_backend2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.buas_team.buas_backend2.mapper")
public class BuasBackend2Application {

    public static void main(String[] args) {
        SpringApplication.run(BuasBackend2Application.class, args);
    }

}
