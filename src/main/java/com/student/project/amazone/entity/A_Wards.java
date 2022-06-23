package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "devvn_xaphuongthitran")
@Data
public class A_Wards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "xaid ", nullable = false)
    private Long xaid ;
    private String name;
    private String type;
    @ManyToOne
    @JoinColumn(name = "maqh")
    @JsonBackReference
    private A_District maqh;
}
