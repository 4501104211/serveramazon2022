package com.student.project.amazone;

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
//            Users_model adminAccount = new Users_model();
//            adminAccount.setUsername("admin");
//            adminAccount.setName("admin");
//            adminAccount.setPassword("12345");
//            adminAccount.setAdmin(true);
//            adminAccount.setPhone("0335857134");
//            adminAccount.setAddress("ABC");
//
//            servicer.updateOrSave(adminAccount);
        };
    }

}
