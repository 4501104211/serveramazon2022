package com.student.project.amazone.controller;

import com.student.project.amazone.entity.A_City;
import com.student.project.amazone.service.VietNam_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.student.project.amazone.CLIENT_URL.CLIENT_1;

@RestController
@RequestMapping("/api/v1/citys")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_1)
public class VietNamController {
    @Autowired
    private final VietNam_service service;

    @GetMapping
    public ResponseEntity<List<A_City>>getAllCity(){
        return ResponseEntity.ok().body(service.citys_list());
    }
}
