package com.student.project.amazone.service;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.repo.CartItemDtoRepository;
import com.student.project.amazone.repo.Cart_modelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class Cart_implement implements Cart_service {

    @Autowired
    private final Cart_modelRepository cart_modelRepository;
    @Autowired
    private final CartItemDtoRepository cartItemDtoRepository;

    public Cart_implement(Cart_modelRepository cart_modelRepository, CartItemDtoRepository cartItemDtoRepository) {
        this.cart_modelRepository = cart_modelRepository;
        this.cartItemDtoRepository = cartItemDtoRepository;
    }

    public boolean inCartItem(cartModel cartModel) {
        boolean itemExists = false;
        if (!cartModel.getCartItem().isEmpty()) {
            itemExists = true;
        }
        return itemExists;
    }

    @Override
    public cartModel cartByCartId(Long cartId) {
        return cart_modelRepository.getById(cartId);
    }

    @Override
    public cartModel cartByUserId(Long userId) {
        cartModel cartExist = cart_modelRepository.findCart_modelByUserId(userId);
        return cartExist;
    }


    public cartItem cartByProductId(Long productId) {
        cartItem cartExist = cartItemDtoRepository.findByProductId(productId);
        return cartExist;
    }

    @Override
    public cartModel save(cartModel newCart) {
        try {
            cartModel cartExist = cartByUserId(newCart.getUserId().getId());
            if (cartExist != null) {
                for (int i = 0; i < newCart.getCartItem().size(); i++) {
                    final cartItem element = newCart.getCartItem().get(i);
                    cartItem itemInCart = cartExist.getCartItem().stream()
                            .filter(x -> element.getProductItem().getId().equals(x.getProductItem().getId()))
                            .findAny()
                            .orElse(null);

                    if (itemInCart != null) {
                        itemInCart.setQuantityItemNumber(element.getQuantityItemNumber());
                        itemInCart.setProductPrice(element.getProductPrice());
                    } else {
                        cartExist.getCartItem().add(element);
                    }
                    Long totalAmount = cartExist.getCartItem().stream().map(cartItem::getProductPrice)
                            .reduce(0L, Long::sum);
                    cartExist.setTotalPrice(totalAmount);
                }
                return cart_modelRepository.save(cartExist);
            }
            Long totalAmount = newCart.getCartItem().stream().map(cartItem::getProductPrice)
                    .reduce(0L, Long::sum);
            newCart.setTotalPrice(totalAmount);
            return cart_modelRepository.save(newCart);
        } catch (Exception ex) {
            throw new IllegalStateException("Lỗi !! Không tìm thấy người dùng có ID là " + newCart.getUserId().getId());
        }
    }

    @Override
    public cartModel update(String itemId, Long userId, Map<Object, Object> fields) {
        try {
            cartModel cartExist = cartByUserId(userId);
            for (int i = 0; i < cartExist.getCartItem().size(); i++) {
                final cartItem element = cartExist.getCartItem().get(i);
                if (element.getProductItem().getId().equals(Long.valueOf(itemId))) {
                    fields.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(cartItem.class, (String) key);
                        field.setAccessible(true);
                        if (field.getName().equals("productPrice")) {
                            ReflectionUtils.setField(field, element, Long.parseLong(value.toString()));
                        } else {
                            ReflectionUtils.setField(field, element, value);
                        }
                    });
                }

                cartItemDtoRepository.save(element);
            }
            Long totalAmount = cartExist.getCartItem().stream().map(cartItem::getProductPrice)
                    .reduce(0L, Long::sum);
            cartExist.setTotalPrice(totalAmount);
            return cart_modelRepository.save(cartExist);
        } catch (Exception ex) {
            throw new IllegalStateException("Có lỗi xảy ra ,vui lòng thử lại sau !");
        }
    }

    @Override
    public void ItemDelete(long CartId, List<Long> itemId) {
        try {
            cartModel cartModel = cart_modelRepository.getById(CartId);
            if (inCartItem(cartModel)) {

                List<cartItem> deletedItems = cartModel.getCartItem().stream()
                        .filter(e -> itemId.contains(e.getProductItem().getId()))
                        .collect(Collectors.toList());


                List<cartItem> copyData = cartModel.getCartItem();
                copyData.removeAll(deletedItems);
                if (copyData.size() == 0) {
                    cartModel.setTotalPrice(0L);
                } else {
                    Long totalAmount = cartModel.getCartItem().stream()
                            .filter(e -> !itemId.contains(e.getId()))
                            .map(cartItem::getProductPrice)
                            .reduce(0L, Long::sum);
                    cartModel.setTotalPrice(totalAmount);
                }

                cartModel.getCartItem().removeAll(deletedItems);
            }
        }catch(Exception ex){
            throw new IllegalStateException(ex.getMessage());
        }
    }

    @Override
    public List<cartModel> cartList() {
        List<cartModel> cart = cart_modelRepository.findAll();
        return cart;
    }
}
