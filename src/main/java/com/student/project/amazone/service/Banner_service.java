package com.student.project.amazone.service;

import com.student.project.amazone.entity.Banner_model;

import java.util.List;

public interface Banner_service {
    List<Banner_model> getList();
    Banner_model SaveOrUpdate(Banner_model banner);

     void delete_file(String fileName);
}
