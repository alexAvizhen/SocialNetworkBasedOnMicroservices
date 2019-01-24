package com.bsu.avizhen.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feedback {
    private Integer id;
    private String name;
    private String description;
    private int mark;
    private User fromUser;
    private User toUser;

    public Feedback() {
    }
}
