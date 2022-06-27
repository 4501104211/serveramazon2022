package com.student.project.amazone.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.student.project.amazone.dto.FileDB;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product_model")
@Data

public class Product_model extends DateAbstract{

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;

    private String description;


    private String imageurl;

    private Long price;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "cata_product")
    private Catagory_model catagory;

    @ManyToOne(cascade = PERSIST)
    @JsonIgnore
    @JoinColumn(name = "order_product")
    private orderItem_model orderItemModel;


    public Product_model() {

    }
}
