package com.student.project.amazone.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class cartItem extends DateAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "product_FK_Item_id")
    @JsonProperty("productItem")
    private Product_model productItem;

    @JsonProperty("quantity")
    private int quantityItemNumber;

    @JsonProperty("productPrice")
    private Long productPrice;

    public cartItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public cartItem(cartItem item) {
        this.id = 0L;
        this.productItem = item.productItem;
        this.quantityItemNumber = item.quantityItemNumber;
        this.productPrice = item.productPrice;
    }

    public void update() {
        int quantity = this.quantityItemNumber + 1;
        this.quantityItemNumber = quantity;
        this.productPrice = productItem.getPrice() * quantity;
    }


}
