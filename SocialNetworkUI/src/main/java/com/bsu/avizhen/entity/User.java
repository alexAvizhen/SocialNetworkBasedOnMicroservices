package com.bsu.avizhen.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
public class User {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String position;
    private Collection<User> friends;
    private String workStartDate;
    private String password;

    public User() {
        id = 0;
        login = "";
        password = "";
    }


    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
