package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private Integer id;
    private User owner;
    private String description;
}
