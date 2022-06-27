package com.student.project.amazone.service;


import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;

import java.util.List;
import java.util.Map;

public interface Cart_service {
    cartModel cartByCartId(Long cartId);
    cartModel cartByUserId(Long userId);

    public cartModel getByUserID(Long userId);

    cartModel save(cartItem newitem,Long userId);

    cartModel update(String itemId, Long userId, Map<Object, Object> fields);

    void ItemDelete(long CartId, List<Long> itemId);
    List<cartModel> cartList();

}
