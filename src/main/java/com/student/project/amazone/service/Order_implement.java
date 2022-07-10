package com.student.project.amazone.service;


import com.student.project.amazone.dto.Order_modelInfo;
import com.student.project.amazone.entity.ChartOption;
import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.repo.Order_modelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_implement implements Order_service {
    @Autowired
    private final Order_modelRepository modelRepository;


    public Order_implement(Order_modelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    @Override
    public Order_model save(Order_model order_model) {
        return modelRepository.save(order_model);
    }

    @Override
    public List<Order_model> all() {
        return modelRepository.findAll();
    }

    @Override
    public List<Order_model> all(long userId) {
        return modelRepository.findByUserId(userId);
    }

    @Override
    public List<Order_model> getTop4OrderByUserId(long userId) {
        return modelRepository.findTop3LastOrderByUserId(userId);
    }

    @Override
    public Order_model oneByOrderId(Long orderId) {
        if (modelRepository.findById(orderId).isPresent()) {
            return modelRepository.findById(orderId).get();
        }
        throw new IllegalStateException("Not found ID");
    }

    @Override
    public ChartOption getValueIn12Month(long userId) {
        List<Order_modelInfo> results = modelRepository.getValueIn12Month(userId);
        final ChartOption[] chartOption = {null};
        results.forEach(item -> chartOption[0] = new ChartOption(item.getJan(), item.getMar(), item.getMay(), item.getJul(), item.getSep(), item.getNov(), item.getFeb(), item.getApr(), item.getJun(), item.getAug(), item.getOct(), item.getDec()));
        return chartOption[0];
    }
}
