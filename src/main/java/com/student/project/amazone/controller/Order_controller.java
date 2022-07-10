package com.student.project.amazone.controller;


import com.student.project.amazone.AbstractController.AbstractControllerOrder;
import com.student.project.amazone.entity.ChartOption;
import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.service.Order_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.student.project.amazone.CLIENT_URL.CLIENT_1;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_1)

public class Order_controller extends AbstractControllerOrder {
    @Autowired
    private final Order_service service;

    @PostMapping
    public ResponseEntity<Order_model> saveOrder(@RequestBody Order_model requestOrder) {
        requestOrder.setStatus(1);
        Long totalAmount = requestOrder.getOrderItems().stream().map(ob -> ob.getProductPrice())
                .reduce(0L, (a, b) -> a + b);
        requestOrder.setTotalAmount(totalAmount);
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


    @Override
    public ResponseEntity<ChartOption> getValueIn12Month(String userId) {
        ChartOption chartOption = service.getValueIn12Month(Long.parseLong(userId));
        return ResponseEntity.ok().body(chartOption);
    }


    @Override
    public ResponseEntity<List<Order_model>> gettop5Order(String userId) {
        return ResponseEntity.ok().body(service.getTop4OrderByUserId(Long.parseLong(userId)));
    }
}
