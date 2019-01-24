package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.CollationElementIterator;
import java.util.Collection;

@Getter
@Setter
public class Post {
    private Integer id;
    private String name;
    private String description;
    private Collection<Comment> comments;
}
