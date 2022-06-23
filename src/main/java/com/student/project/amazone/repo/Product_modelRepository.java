package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Product_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Product_modelRepository extends JpaRepository<Product_model, Long> {


    @Query("FROM Product_model c where c.catagory.id = :catagory")
    List<Product_model> findProductByCartId(@Param("catagory") Long id);


    List<Product_model> findProduct_modelByNameContaining(@Param("name") String name);

    @Query("FROM Product_model c  where count(c.orderItemModel.productItem)>=2 ")
   List<Product_model> findProduct_modelByOrderId();

}