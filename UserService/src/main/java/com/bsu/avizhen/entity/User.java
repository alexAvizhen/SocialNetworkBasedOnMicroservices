package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class User implements Serializable{

    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String position;
    private Collection<User> friends = new ArrayList<>();
    private Project currentProject;
    private List<Project> previousProjects;
    private Office currentOffice;
    private List<Feedback> feedbacks;
    private Date workStartDate;
    private Map<String, String> parameters;

    public User() {
    }

    public User(Integer id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public User(String userName, String userPosition) {
        this.name = userName;
        this.position = userPosition;
    }

    public User(Integer id, String name, String surname, String email, String position, String login) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
        this.login = login;
    }
}
