package com.student.project.amazone.Admin_controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.dto.FileDB;
import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.FIle.FileDBService;
import com.student.project.amazone.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.student.project.amazone.CLIENT_URL.CLIENT_2;

@RestController
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_2)
public class
ControllerProduct_mn {


    private final ServiceProduct serviceProduct;
    private final FileDBService serviceFile;

    private final FileStorageService fileStorageService;

    FileDB fileDB = new FileDB();
    ObjectMapper objectMapper = new ObjectMapper();
    Product_model emp = new Product_model();
    @GetMapping("/all")
    public ResponseEntity<List<Product_model>> findAllProduct() {
        List<Product_model> product = serviceProduct.findAll();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity create(@RequestParam("image") MultipartFile file, @RequestParam("product") String product) throws JsonProcessingException {
        try {
            fileDB = serviceFile.storeImageFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        emp = objectMapper.readValue(product, Product_model.class);
        System.out.println(emp.getCatagory());
        Catagory_model cata = new Catagory_model();

        cata.setId(emp.getCatagory().getId());
        emp.setImageurl(fileDB);
        emp.setCatagory(cata);
        return ResponseEntity.ok(serviceProduct.save(emp));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestParam("image") MultipartFile file, @RequestParam("product") String product) throws JsonProcessingException {
        try {
            fileDB = serviceFile.storeImageFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        emp = objectMapper.readValue(product, Product_model.class);
        System.out.println(emp.getCatagory());
        Catagory_model cata = new Catagory_model();

        cata.setId(emp.getCatagory().getId());
        emp.setImageurl(fileDB);
        emp.setCatagory(cata);
        return ResponseEntity.ok(serviceProduct.save(emp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product_model> findById(@PathVariable Long id) {
        Optional<Product_model> product = serviceProduct.findById(id);
        if (!product.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(product.get());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        serviceProduct.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
