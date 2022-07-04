package com.student.project.amazone.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.repo.CartItemDtoRepository;
import com.student.project.amazone.repo.Cart_modelRepository;
import com.sun.jersey.api.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return cart_modelRepository.findById(cartId).get();
    }

    @Override
    public cartModel cartByUserId(Long userId) {
        cartModel cartExist = cart_modelRepository.findCartByUserId(userId);
        return cartExist;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public cartModel getByUserID(Long userId) {
        Users_model user = new Users_model();
        user.setId(userId);
        cartModel cartExist = cart_modelRepository.findCartByUserId(userId);
        if (cartExist != null) {
            return cartExist;
        } else {
            throw new NotFoundException("Không tìm thấy giỏ hàng");
        }
    }

    @Override
    public cartModel saveAfterAddFromClient(cartItem _newitem, Long userId) {
        cartModel parentCart = cartByUserId(userId);
        cartItem getItem = parentCart.getCartItem().stream().filter(item ->
                item.getProductItem().getId().equals(_newitem.getProductItem().getId())
        ).findAny().orElse(null);
        _newitem.setParentId(parentCart);
        if (getItem != null) {
            getItem.update(0);
            cartItemDtoRepository.save(getItem);
        } else {
            parentCart.getCartItem().add(_newitem);
        }

        Long totalAmount = parentCart.getCartItem().stream().map(cartItem::getProductPrice)
                .reduce(0L, Long::sum);
        parentCart.setTotalPrice(totalAmount);

        cartModel tests = cart_modelRepository.save(parentCart);
        return tests;
    }

    @Override
    public cartModel saveAfterRegister(Users_model userId) {
        cartModel newCart = new cartModel();
        newCart.setUserId(userId);
        return cart_modelRepository.save(newCart);
    }


    @Override
    public cartModel update(Map<Object, Object> fields) {
        try {

            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(fields);
            cartItem itemRequestUpdate = gson.fromJson(jsonElement, cartItem.class);

            cartModel parentCart = cartByCartId(itemRequestUpdate.getParentId().getId());

            parentCart.getCartItem().stream().filter(item ->
                    item.getId().equals(itemRequestUpdate.getId())
            ).forEach(item -> {
                item.update(itemRequestUpdate.getQuantityItemNumber());
            });
            Long totalAmount = parentCart.getCartItem().stream().map(cartItem::getProductPrice)
                    .reduce(0L, Long::sum);
            parentCart.setTotalPrice(totalAmount);
            return cart_modelRepository.save(parentCart);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Có lỗi xảy ra ,vui lòng thử lại sau !");
        }
    }

    @Override
    public void ItemDelete(long CartId, List<Long> itemId) {
        try {
            cartModel cartModel = cart_modelRepository.getById(CartId);
            if (inCartItem(cartModel)) {

                List<cartItem> deletedItems = cartModel.getCartItem().stream()
                        .filter(e -> itemId.contains(e.getId()))
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
                cartItemDtoRepository.deleteAll(deletedItems);

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
