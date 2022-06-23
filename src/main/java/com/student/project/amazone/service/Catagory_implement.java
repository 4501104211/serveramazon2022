package com.student.project.amazone.service;


import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.repo.Catagory_modelRespository;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class Catagory_implement implements Catagory_service{

    private final Catagory_modelRespository service;

    public Catagory_implement(Catagory_modelRespository service) {
        this.service = service;
    }
    @Override
    public Catagory_model saveCatagory(Catagory_model user){
            return service.save(user);
    }

    @Override
    public Catagory_model updateCategory(Catagory_model user) {
        return service.save(user);
    }

    @Override
    public List<Catagory_model> findAllCategory(){
        return service.findAll();
    }
    @Override
    public Catagory_model findUserById(Long id){
        if (service.findCatagoryById(id) != null) return service.findCatagoryById(id);
        throw new NotFoundException("Catagory not exists");
    }
    @Override
    public void deleteCatagory(Long id){
         service.deleteCatagoryById(id);
    }

    public Catagory_model findUserByName(String name){
        return service.findByName(name);
    }


}
