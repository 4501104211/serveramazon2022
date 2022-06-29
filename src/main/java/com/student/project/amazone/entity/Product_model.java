package com.student.project.amazone.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;
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

    @ManyToOne(cascade = REFRESH)
    @JoinColumn(name = "cata_product")
    private Catagory_model catagory;

    @ManyToOne(cascade = REMOVE)
    @JsonIgnore
    @JoinColumn(name = "order_product")
    private orderItem_model orderItemModel;



    public Product_model() {

    }
}
