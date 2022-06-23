package com.student.project.amazone.service;


import com.student.project.amazone.entity.Users_model;

import java.util.List;

public interface Users_service {
    Users_model isLoggedIn(Users_model usersModel);
    Users_model isLoggedInAdmin(Users_model usersModel);

    Users_model updateOrSave(Users_model usersModel);
    Users_model registerUser(Users_model usersModel);
    List<Users_model> findAllUsers();
    Users_model findUserById(Long id);
    Users_model findUserByName(String name);
}
