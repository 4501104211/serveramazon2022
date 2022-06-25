package com.student.project.amazone.Admin_controller;

import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.service.Catagory_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.student.project.amazone.CLIENT_URL.CLIENT_2;
@RestController
@RequestMapping("/api/v2/catagory/")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_2)
public class Catagory_controller_mn {
    @Autowired
    private final Catagory_service service;

    @GetMapping("all")
    public ResponseEntity<List<Catagory_model>> getAllCatagory() {

        List<Catagory_model> cata = service.findAllCategory();
        return new ResponseEntity<>(cata, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Catagory_model> saveProduct(@RequestBody Catagory_model catagory_model) {
        return ResponseEntity.ok().body(service.saveCatagory(catagory_model));
    }
    @GetMapping("find/{id}")
    public ResponseEntity<Catagory_model> getCategory(@PathVariable("id") Long id) {
        Catagory_model cata = service.findUserById(id);
        return new ResponseEntity<>(cata, HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity<Catagory_model> updateCategory(@RequestBody Catagory_model cata) {
        Catagory_model updateCategory = service.updateCategory(cata);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        service.deleteCatagory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
