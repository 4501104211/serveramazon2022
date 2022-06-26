package com.student.project.amazone.service;

import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.repo.Product_modelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ServiceProduct {

    @Autowired
    Product_modelRepository repositoryProduct;


    public List<Product_model> findAll() {
        List<Product_model> result = new ArrayList<>();
        repositoryProduct.findAll().forEach(result::add);
        return result;
    }

    public List<Product_model> findByName(String name) {
        List<Product_model> result = new ArrayList<>();
        repositoryProduct.findProduct_modelByNameContaining(name).forEach(result::add);
        return result;
    }

    public Optional<Product_model> findById(Long id) {
        return repositoryProduct.findById(id);
    }

    public List<Product_model> findByCateId(Long id) {
        List<Product_model> result = new ArrayList<>();
        repositoryProduct.findProductByCartId(id).forEach(result::add);
        return result;
    }

    public Product_model save(MultipartFile file, Product_model stock) {
        return repositoryProduct.save(stock);
    }


    public void deleteById(Long id) {
        repositoryProduct.deleteById(id);
    }

    public List<Product_model> findProduct_modelByOrderId() {
        List<Product_model> result = new ArrayList<>();
        repositoryProduct.findProduct_modelByOrderId().forEach(result::add);
        return result;
    }


}
