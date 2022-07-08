package com.student.project.amazone.controller;


import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.service.Cart_service;
import com.student.project.amazone.service.Users_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.student.project.amazone.CLIENT_URL.CLIENT_1;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_1)
public class Users_controller {

    private final Users_service service;
    private final Cart_service cartService;

    Map<Object, Object> respone = new HashMap<>();


    @GetMapping
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok().body("Login success");
    }


    @GetMapping("isAdmin")
    public ResponseEntity<Boolean> checkIsAdmin(@RequestParam String username) {
        Users_model usersModel = service.findUserByName(username);
        if (usersModel != null) {
            if (usersModel.isAdmin()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @PatchMapping("banUser")
    public ResponseEntity<Boolean> bannedUser(@RequestParam String username) {
        Users_model usersModel = service.findUserByName(username);
        if (usersModel != null) {
            usersModel.setBanned(true);
            if (service.updateOrSave(usersModel).isBanned()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("isDeleted")
    public ResponseEntity<Boolean> checkIsDeleted(@RequestParam String username) {
        Users_model usersModel = service.findUserByName(username);
        if (usersModel != null) {
            if (usersModel.isDeleted()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> loginUsers(@RequestBody Users_model user) {
        HttpStatus status = HttpStatus.OK;
        System.out.println("Ok");
        try {
            Users_model userDto = service.isLoggedIn(user);
            respone.put("user", userDto);
            respone.put("message", "Đăng nhập thành công, xin chào " + userDto.getUsername());
        } catch (NullPointerException ex) {
            respone.put("message", ex.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @PostMapping("save")
    public ResponseEntity<Map<Object, Object>> saveUser(@RequestBody Users_model user) {
        return getMapResponseEntity(user, "post");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<Object, Object>> UpdateUser(@PathVariable String id, @RequestBody Users_model user) {
        return getMapResponseEntity(user, "put");
    }

    private ResponseEntity<Map<Object, Object>> getMapResponseEntity(@RequestBody Users_model user, String type) {
        HttpStatus status = HttpStatus.OK;
        try {
            if (type == "put") {
                Users_model findUserByName = service.findUserById(user.getId());
                user.setCreateAt(findUserByName.getCreateAt());
                Users_model responeUser = service.updateOrSave(user);
                respone.put("user", responeUser);
                respone.put("message", "Cập nhật thành công, xin chào " + responeUser.getUsername());
            } else {
                cartModel cart = new cartModel();
                user.setCart(cart);
                Users_model responeUser = service.registerUser(user);
//                cartService.saveAfterRegister(service.registerUser(user));
                respone.put("message", "Đăng ky thành công, xin chào " + responeUser.getUsername());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            respone.put("message", ex.getMessage());
            status = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(status).body(respone);
    }
}
