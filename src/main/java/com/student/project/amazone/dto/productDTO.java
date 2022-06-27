package com.student.project.amazone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.entity.orderItem_model;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Data
public class productDTO {
    private Long id;
    private String name;
    private String description;
    private Long price;
    @JsonProperty("categoryID")
    private Long categoryID;

    public Product_model convertToProduct(){
        Product_model project = new Product_model();
        project.setId(this.getId());
        project.setName(this.getName());
        project.setDescription(this.getDescription());
        project.setPrice(this.getPrice());
        return project;
    }
}
