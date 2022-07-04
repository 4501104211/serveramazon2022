package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_model")
@Data
@EqualsAndHashCode(callSuper = true)
public class cartModel extends DateAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToMany(mappedBy = "parentId",cascade =  { CascadeType.MERGE,CascadeType.PERSIST }, orphanRemoval = true)
    @JsonProperty("cartItem")
    @JsonManagedReference
    private List<cartItem> cartItem = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "FK_user_id")
    @JsonProperty("userId")
    @JsonBackReference
    private Users_model userId;

    @JsonProperty("TotalPrice")
    private Long TotalPrice;

    public cartModel() {
    }

}
