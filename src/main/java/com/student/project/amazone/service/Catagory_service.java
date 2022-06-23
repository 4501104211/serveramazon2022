package com.student.project.amazone.service;

import com.student.project.amazone.entity.Catagory_model;

import java.util.List;

public interface Catagory_service {
     Catagory_model saveCatagory(Catagory_model user);
     Catagory_model updateCategory(Catagory_model user);

     List<Catagory_model> findAllCategory();

     Catagory_model findUserById(Long id);

     void deleteCatagory(Long id);
}
