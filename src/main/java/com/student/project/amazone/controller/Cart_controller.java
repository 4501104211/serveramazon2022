package com.student.project.amazone.controller;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.service.Cart_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.student.project.amazone.CLIENT_URL.CLIENT_1;
import static java.lang.Long.valueOf;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = CLIENT_1)
@RequiredArgsConstructor
public class Cart_controller {

    private final Cart_service service;

    Map<Object, Object> respone = new HashMap<>();
    cartModel cartData = new cartModel();

    @GetMapping
    public ResponseEntity<Map<Object, Object>> userMap(@RequestParam String userID) {
        HttpStatus status = HttpStatus.OK;
        try {
            cartData = service.getByUserID(Long.parseLong(userID));
            respone.put("cartData", cartData);
            respone.put("isError", false);
            respone.put("uniqueItemInCart", cartData.getCartItem().size());
            respone.put("message", "Lấy thành công dữ liệu");
        } catch (Exception ex) {
            status = HttpStatus.NOT_FOUND;
            ex.printStackTrace();
            respone.put("isError", true);
            respone.put("message", "Giỏ hàng trống không, hehe mua đi rồi thấy :v");
        }
        return ResponseEntity.status(status).body(respone);
    }

    @GetMapping("/All")
    public ResponseEntity<List<cartModel>> map() {
        return ResponseEntity.ok().body(service.cartList());
    }


    @GetMapping("/mini")
    public ResponseEntity<Map<Object, Object>> mapMini(@RequestParam String userID) {
        cartModel cartModel = service.cartByUserId(Long.valueOf(userID));

        respone.put("uniqueItemInCart", cartModel.getCartItem().size());
        return ResponseEntity.ok().body(respone);
    }


    @PostMapping
    public ResponseEntity<Map<Object, Object>> add(@RequestBody cartItem item, @RequestParam String userID) {

        String message = "Thêm thành công vào giỏ hàng";


        HttpStatus status = HttpStatus.OK;
        try {
            cartData = service.saveAfterAddFromClient(item, Long.parseLong(userID));
            respone.put("uniqueItemInCart", cartData.getCartItem().size());
            respone.put("cartData", cartData);
            respone.put("message", message);
        } catch (Exception ex) {
            ex.printStackTrace();
            respone.put("message", ex.getMessage());
            status = HttpStatus.REQUEST_TIMEOUT;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @PatchMapping
    public ResponseEntity<Map<Object, Object>> patch(@RequestBody Map<Object, Object> fields) {
        String message = "Cập nhật thành công";
        HttpStatus status = HttpStatus.OK;
        try {
            cartData = service.update(fields);
            respone.put("uniqueItemInCart", cartData.getCartItem().size());
            respone.put("cartData", cartData);
            respone.put("message", message);
        } catch (Exception ex) {
            respone.put("message", ex.getMessage());
            status = HttpStatus.REQUEST_TIMEOUT;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @DeleteMapping
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String CartID, @RequestBody List<Long> Itemid) {
        String message = "Xóa thành công";
        HttpStatus status = HttpStatus.OK;
        try {
            cartData = service.cartByCartId(Long.parseLong(CartID));

            if (cartData.getCartItem().size() != 0) {
                service.ItemDelete(valueOf(CartID), Itemid);
            }
            respone.put("cartData", cartData);
            respone.put("uniqueItemInCart", cartData.getCartItem().size());
            respone.put("message", message);
        } catch (Exception ex) {
            message = ex.getMessage();
            respone.put("message", message);
            status = HttpStatus.REQUEST_TIMEOUT;
        }
        return ResponseEntity.status(status).body(respone);


    }
}
