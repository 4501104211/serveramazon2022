package com.student.project.amazone.Admin_controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.dto.FileDB;
import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.Catagory_service;
import com.student.project.amazone.service.FIle.FileDBService;
import com.student.project.amazone.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    private final Catagory_service catagoryService;
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

    private ResponseEntity getResponseEntity(@RequestParam("product") String product) throws JsonProcessingException {

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v2/product/files/download/")
                .path(fileDB.getId()).path("/db")
                .toUriString();

        Gson gson = new Gson();
        JsonObject o = JsonParser.parseString(product).getAsJsonObject();
        emp = gson.fromJson(o, Product_model.class);

        Product_model findProduct = serviceProduct.findById(emp.getId()).orElse(null);
        Catagory_model cata = catagoryService.findUserById(emp.getCatagory().getId());
        emp.setImageurl(fileDownloadUri);
        emp.setCatagory(cata);
        if (findProduct == null) {
            return ResponseEntity.ok().body(serviceProduct.save(emp));
        } else {
            emp.setCreateAt(findProduct.getCreateAt());
            return ResponseEntity.ok().body(serviceProduct.save(emp));
        }

    }

    @GetMapping("/files/download/{fileID}/db")
    public ResponseEntity downloadFromDB(@PathVariable String fileID) {
        FileDB document = serviceFile.getImage(fileID);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(document.getData());
    }

    @PostMapping("/save")
    public ResponseEntity create(@RequestParam("image") MultipartFile file, @RequestParam("product") String product) throws JsonProcessingException {
        try {
            fileDB = serviceFile.storeImageFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getResponseEntity(product);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestParam("image") MultipartFile file,
                                 @RequestParam("imageID") String id,
                                 @RequestParam("product") String product)
            throws JsonProcessingException {

        try {
            fileDB = serviceFile.updateImageFile(id,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getResponseEntity(product);
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
