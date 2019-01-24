package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class Community {
    private Integer id;
    private String name;
    private String description;
    private Collection<Post> content;
    private Collection<User> admins;
    private Collection<User> participants;
}
