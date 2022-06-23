package com.student.project.amazone.Admin_controller;


import com.student.project.amazone.AbstractController.AbstractControllerOrder;
import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.service.Order_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9111")
public class Order_controller_mn extends AbstractControllerOrder {
    private final Order_service service;

    @PostMapping
    public ResponseEntity<Order_model> saveOrder(@RequestBody Order_model requestOrder) {
        return ResponseEntity.ok().body(service.save(requestOrder));
    }


    @Override
    public ResponseEntity<List<Order_model>> getOrder() {
        return ResponseEntity.ok().body(service.all());
    }

    @Override
    public ResponseEntity<List<Order_model>> getOrder(Optional<Long> userId) {
        return ResponseEntity.ok().body(service.all(userId.get()));
    }

    @Override
    public ResponseEntity<Order_model> updateStatus(Optional<Integer> change, Optional<Long> orderId) {
        return super.updateStatus(change, orderId);
    }

}
