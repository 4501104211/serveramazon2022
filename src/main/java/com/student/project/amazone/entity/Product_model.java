package com.student.project.amazone.entity;


import com.student.project.amazone.dto.FileDB;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product_model")
@Data

public class Product_model {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;

    private String description;

    private FileDB imageurl;
    private Long price;
    @Column(name = "created_at")
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(value = TemporalType.DATE)
    private Date updatedAt;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();

    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updatedAt = new Date();
    }

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "cata_product")
    private Catagory_model catagory;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "order_product")
    private orderItem_model orderItemModel;

    public Product_model(String name, Long price, Catagory_model catagory) {
        this.name = name;
        this.price = price;
        this.catagory = catagory;
    }

    public Product_model() {

    }
}
