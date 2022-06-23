package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Catagory_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Catagory_modelRespository extends JpaRepository<Catagory_model,Long> {
    Catagory_model findByName(String name);
    void deleteCatagoryById(Long id);

    @Query(value = "SELECT * FROM catagory_model WHERE id = ?1",
            nativeQuery = true)
    Catagory_model findCatagoryById(Long id);


}
