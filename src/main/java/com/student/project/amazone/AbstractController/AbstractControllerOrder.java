package com.student.project.amazone.AbstractController;

import com.student.project.amazone.dto.Order_modelInfo;
import com.student.project.amazone.entity.ChartOption;
import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.service.Order_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public abstract class AbstractControllerOrder {
    @Autowired
    private Order_service service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Order_model>> getOrder() {
        return null;
    }

    @RequestMapping(value = "/profile/chartTotal", method = RequestMethod.GET)
    public ResponseEntity<ChartOption> getValueIn12Month(@RequestParam String userId) {
        return null;
    }

    @RequestMapping(value = "/profile/top5Order", method = RequestMethod.GET)
    public ResponseEntity<List<Order_model>> gettop5Order(@RequestParam String userId) {
        return null;
    }

    @RequestMapping(value = "", params = "userId", method = RequestMethod.GET)
    public ResponseEntity<List<Order_model>> getOrder(@RequestParam(name = "userId", required = false) Optional<Long> userId) {
//        System.out.println(userId.get());
        return null;
    }

    @RequestMapping(value = "/status", params = "change", method = RequestMethod.PATCH)
    public ResponseEntity<Order_model> updateStatus(
            @RequestParam(name = "change", required = false) Optional<Integer> change,
            @RequestParam(name = "orderId", required = false) Optional<Long> orderId
    ) {
        Order_model orderModel = service.oneByOrderId(orderId.get());
        orderModel.setStatus(change.get());

        return ResponseEntity.ok().body(service.save(orderModel));
    }

}
