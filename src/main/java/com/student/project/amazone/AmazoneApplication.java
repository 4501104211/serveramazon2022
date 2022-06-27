package com.student.project.amazone;

import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.service.Users_service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class AmazoneApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AmazoneApplication.class, args);
    }



    @Bean
    public CommandLineRunner commandLineRunner(Users_service servicer) {
        return args -> {
        };
    }

}
