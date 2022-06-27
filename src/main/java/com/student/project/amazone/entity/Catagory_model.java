package com.student.project.amazone.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "catagory_model")
@Data
public class Catagory_model extends DateAbstract{
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
}
