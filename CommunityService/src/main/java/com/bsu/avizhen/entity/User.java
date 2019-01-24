package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private String surname;
    private String login;
    private Collection<Event> userEvents;
    private Collection<Community> userCommunities;
}
