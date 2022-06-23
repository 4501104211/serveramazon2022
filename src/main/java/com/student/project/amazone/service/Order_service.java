package com.student.project.amazone.service;

import com.student.project.amazone.entity.ChartOption;
import com.student.project.amazone.entity.Order_model;


import java.util.List;

public interface Order_service {
     Order_model save(Order_model order_model);

     List<Order_model> all();

     List<Order_model> all(long userId);

     List<Order_model> getTop4OrderByUserId(long userId);


     Order_model oneByOrderId(Long orderId);

     ChartOption  getValueIn12Month(long userId);
}
