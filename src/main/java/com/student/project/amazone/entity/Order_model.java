package com.student.project.amazone.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "order_model")
@Data
@EqualsAndHashCode(callSuper = true)
public class Order_model extends DateAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JsonProperty("orderItems")
    private List<orderItem_model> orderItems = new ArrayList<>();

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("city")
    private String city;

    @JsonProperty("wards")
    private String wards;

    @JsonProperty("district")
    private String district;

    @JsonProperty("note")
    private String note;

    @JsonProperty("address")
    private String address;

    @JsonProperty("totalAmount")
    private Long totalAmount;

    @JsonProperty("status")
    private int status;

    @OneToOne
    @JoinColumn(name = "FK_user_id")
    @JsonProperty("userId")
    private Users_model userId;

    public Order_model() {

    }
}
