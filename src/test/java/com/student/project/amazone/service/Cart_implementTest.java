package com.student.project.amazone.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class Cart_implementTest {

    @Autowired
    Cart_service cartService;

    @Test
    void update() {
        String json = "{ 'quantityItemNumber':1 }";


        if (cartService.getByUserID(5l) == null) {
            System.out.println("null");
        } else {
            System.out.println("noy null");
        }
    }
}