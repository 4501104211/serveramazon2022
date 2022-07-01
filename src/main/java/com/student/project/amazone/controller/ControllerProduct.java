package com.student.project.amazone.controller;

import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.ServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.student.project.amazone.CLIENT_URL.CLIENT_1;

@RestController

@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_1)
public class
ControllerProduct {

    private final ServiceProduct serviceProduct;

    Map<Object, Object> RESPONE = new HashMap<>();
    Product_model DATA = new Product_model();
    List<Product_model> LIST_DATA = new ArrayList<>();

    @GetMapping("all")
    public ResponseEntity<List<Product_model>> findAllCategory() {
        LIST_DATA = serviceProduct.findAll();
        return new ResponseEntity<>(LIST_DATA, HttpStatus.OK);
    }

    @GetMapping("orderItem")
    public ResponseEntity<List<Product_model>> findProduct_modelByOrderId() {
        LIST_DATA = serviceProduct.findProduct_modelByOrderId();
        return new ResponseEntity<>(LIST_DATA, HttpStatus.OK);
    }


    @GetMapping("category/{id}")
    public ResponseEntity<List<Product_model>> findProductByCateId(@PathVariable("id") String id) {
        LIST_DATA = serviceProduct.findByCateId(Long.parseLong(id));
        return new ResponseEntity<>(LIST_DATA, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<Object, Object>> findProductById(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.OK;
        try {
            DATA = serviceProduct.findById(Long.parseLong(id)).get();
            RESPONE.put("data", DATA);
            RESPONE.put("isError", false);
            RESPONE.put("message", "Lấy thành công dữ liệu");
        } catch (Exception ex) {
            DATA = null;
            status = HttpStatus.NOT_FOUND;
            RESPONE.put("isError", true);
            RESPONE.put("message", "Không tìm thấy sản phẩm");
        }
        return ResponseEntity.status(status).body(RESPONE);
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
