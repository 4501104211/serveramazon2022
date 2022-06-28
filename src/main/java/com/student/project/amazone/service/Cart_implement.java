package com.student.project.amazone.service;

import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.repo.CartItemDtoRepository;
import com.student.project.amazone.repo.Cart_modelRepository;
import com.sun.jersey.api.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class Cart_implement implements Cart_service {
    private final Cart_modelRepository cart_modelRepository;
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

    @Override
    public cartModel getByUserID(Long userId) {
        Users_model user = new Users_model();
        user.setId(userId);
        cartModel cartExist = cart_modelRepository.findCart_modelByUserId(userId);
        if (cartExist != null) {
            return cartExist;
        } else {
            throw new NotFoundException("Không tìm thấy giỏ hàng");
        }
    }

    @Override
    public cartModel save(cartItem _newitem, Long userId) {
        cartModel parentCart = cartByUserId(userId);

        if (parentCart != null) {
            cartItem cartitem = parentCart.getCartItem().stream()
                    .filter(item ->
                            _newitem.getProductItem().getId().equals(item.getProductItem().getId())
                    )
                    .findAny().orElse(null);
            if (cartitem == null) {
                parentCart.getCartItem().add(_newitem);
            } else {
                cartitem.update();
                cartItemDtoRepository.save(cartitem);
            }
        } else {
            parentCart = new cartModel();
            Users_model user = new Users_model();
            user.setId(userId);

            parentCart.getCartItem().add(_newitem);
            parentCart.setUserId(user);
        }
        Long totalAmount = parentCart.getCartItem().stream().map(cartItem::getProductPrice)
                .reduce(0L, Long::sum);
        parentCart.setTotalPrice(totalAmount);
        return cart_modelRepository.save(parentCart);
    }

    @Override
    public cartModel update(String itemId, Long userId, Map<Object, Object> fields) {
        try {
            cartModel cartExist = cartByUserId(userId);
            for (int i = 0; i < cartExist.getCartItem().size(); i++) {
                final cartItem element = cartExist.getCartItem().get(i);
                if (element.getId().equals(Long.valueOf(itemId))) {
                    fields.forEach((key, value) -> {
                        Field field = ReflectionUtils.findField(cartItem.class, (String) key);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, element, value);
                    });
                }
                Long productPrice = element.getProductItem().getPrice() * element.getQuantityItemNumber();
                element.setProductPrice(productPrice);
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
        } catch (Exception ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }

    @Override
    public List<cartModel> cartList() {
        List<cartModel> cart = cart_modelRepository.findAll();
        return cart;
    }
}
