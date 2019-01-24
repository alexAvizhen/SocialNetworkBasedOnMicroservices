package com.bsu.avizhen.dto;

import com.bsu.avizhen.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private Integer id;
    private String name;
    private List<User> projectLeads;

    public ProjectDto() {
    }
}
