package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Office {
    private Integer id;
    private String country;
    private String city;
    private String address;
//    private List<User> employees;

    public Office() {
    }
}
