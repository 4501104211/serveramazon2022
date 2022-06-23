package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "devvn_tinhthanhpho")
@Data
public class A_City  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "matp  ", nullable = false)
    private Long matp;
    private String name;
    private String type;
    private String slug;

    @OneToMany(mappedBy = "matp", cascade = ALL)
        @JsonProperty("quanhuyen")
    @JsonManagedReference
    private List<A_District> district_list;
}
