package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project {
    private Integer id;
    private String name;
    private List<User> projectLeads;
    private List<User> teamMembers;

    public Project() {
    }
}
