package com.student.project.amazone.controller;

import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.student.project.amazone.CLIENT_URL.CLIENT_1;

@RestController

@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_1)
public class
ControllerProduct {

    @Autowired
    private final ServiceProduct serviceProduct;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("all")
    public ResponseEntity<List<Product_model>> findAllCategory() {
        List<Product_model> cata = serviceProduct.findAll();
        return new ResponseEntity<>(cata, HttpStatus.OK);
    }

    @GetMapping("orderItem")
    public ResponseEntity<List<Product_model>> findProduct_modelByOrderId() {
        List<Product_model> cata = serviceProduct.findProduct_modelByOrderId();
        return new ResponseEntity<>(cata, HttpStatus.OK);
    }


    @GetMapping("category/{id}")
    public ResponseEntity<List<Product_model>> findProductByCateId(@PathVariable("id") String id) {
        List<Product_model> cata = serviceProduct.findByCateId(Long.parseLong(id));
        return new ResponseEntity<>(cata, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product_model> findProductById(@PathVariable("id") String id) {
        Product_model cata = serviceProduct.findById(Long.parseLong(id)).get();
        return new ResponseEntity<>(cata, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<Product_model>> findByName(@RequestParam("name") String name) {
        List<Product_model> cata = serviceProduct.findByName(name);
        return new ResponseEntity<>(cata, HttpStatus.OK);


    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        serviceProduct.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//	@GetMapping("/{id}")
//	public ResponseEntity<List<Product_model>> findByCateId(@PathVariable(value = "id") Long id) {
//
//			return ResponseEntity.ok().body(serviceProduct.findProductByCartId(id)));
//
//
//
//	}


}
