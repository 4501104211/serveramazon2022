package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Banner_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Banner_modelRepository extends JpaRepository<Banner_model, Long> {

}