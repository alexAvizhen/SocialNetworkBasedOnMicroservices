package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Message {
    private Integer id;
    private User owner;
    private String message;
    private Date date;
    private boolean isRead = false;
    private List<Hashtag> hashtags;

    public Message(Integer id, String message, Date date, User owner) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.owner = owner;
    }
}

