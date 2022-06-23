package com.student.project.amazone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class AmazoneApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AmazoneApplication.class, args);
    }



    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }

}
