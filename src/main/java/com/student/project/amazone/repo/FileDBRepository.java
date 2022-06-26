package com.student.project.amazone.repo;

import com.student.project.amazone.dto.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDBRepository extends JpaRepository<FileDB, String> {
}