package com.student.project.amazone.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_resetpass_model")
@Data
public class Users_resetPass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
}

