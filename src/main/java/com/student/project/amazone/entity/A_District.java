package com.student.project.amazone.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "devvn_quanhuyen")
@Data
public class A_District{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "maqh ", nullable = false)
    private Long maqh ;
    private String name;
    private String type;


    @ManyToOne
    @JoinColumn(name = "matp")
    @JsonBackReference
    private A_City matp;

    @OneToMany(mappedBy = "maqh", cascade = ALL)
    @JsonProperty("phuongxa")
    @JsonManagedReference
    private List<A_Wards> wards_list;
}
