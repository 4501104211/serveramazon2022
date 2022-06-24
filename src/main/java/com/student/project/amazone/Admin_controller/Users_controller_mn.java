package com.student.project.amazone.Admin_controller;


import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.service.Users_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.student.project.amazone.CLIENT_URL.CLIENT_2;
@RestController
@RequestMapping("/api/v2/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_2)
public class Users_controller_mn {

    private final Users_service service;

    Map<Object, Object> respone = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Users_model>> getUsers() {
        return ResponseEntity.ok().body(service.findAllUsers());
    }

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> loginUsers(@RequestBody Users_model user) {
        HttpStatus status = HttpStatus.OK;
        try {
            Users_model.userDto userDto = new Users_model.userDto(service.isLoggedInAdmin(user));
            respone.put("user", userDto);
            respone.put("message", "Đăng nhập thành công, xin chào " + userDto.getUsername());
        } catch (Exception ex) {
            respone.put("message", ex.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(respone);
    }


}
