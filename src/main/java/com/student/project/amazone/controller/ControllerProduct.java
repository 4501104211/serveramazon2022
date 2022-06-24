package com.student.project.amazone.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://customerui.herokuapp.com")
public class
ControllerProduct {


    private final ServiceProduct serviceProduct;

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

    @PostMapping("save")
    public ResponseEntity create(@RequestParam(name = "image", required = false) Optional<MultipartFile> file, @RequestParam("product") String product) {
        ObjectMapper objectMapper = new ObjectMapper();
        Product_model emp = null;
        try {
            emp = objectMapper.readValue(product, Product_model.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String imageName = "product.jpg";

        if (file != null) {
            imageName = fileStorageService.storeFile(file.get());
        }
        emp.setImageurl(imageName);
        return ResponseEntity.ok(serviceProduct.save(emp));
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

    @GetMapping("/search")
    public ResponseEntity<List<Product_model>> findByName(@RequestParam("name") String name) {
        List<Product_model> cata = serviceProduct.findByName(name);
        return new ResponseEntity<>(cata, HttpStatus.OK);


    }

    @PutMapping("/update")
    public ResponseEntity<Product_model> updateCategory(@RequestBody Product_model cata) {
        Product_model updateCategory = serviceProduct.save(cata);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
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
