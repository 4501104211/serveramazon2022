package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.lang.String;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "users_model")
@Data
public class Users_model extends DateAbstract {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String name;
    private String username;
    private String password;
    private String address;
    private String phone;
    private boolean isAdmin;
    private boolean isBanned;
    private boolean isDeleted;

    @Data
    public static class userDto {
        private long id;
        private String username;
        private String name;
        private String address;
        private String phone;
        @JsonProperty("isAdmin")
        private boolean isAdmin;

        public userDto(Users_model usersModel) {
            this.id = usersModel.id;
            this.username = usersModel.username;
            this.name = usersModel.name;
            this.address = usersModel.address;
            this.phone = usersModel.phone;
            this.isAdmin = usersModel.isAdmin;
        }
    }
}
