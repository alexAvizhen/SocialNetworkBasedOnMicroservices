package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

@Getter
@Setter
public class Chat {
    private Integer id;
    private String chatName;
    private List<User> owners;
    private TreeSet<Message> messages;

    public Chat(Integer id, List<User> owners) {
        this.id = id;
        this.owners = owners;
        StringBuilder str = new StringBuilder();
        for (User owner : owners) {
            str.append(owner.getLogin());
            str.append(", ");
        }
        chatName = str.toString();
        messages = new TreeSet<>(new Comparator<Message>() {
            @Override
            public int compare(Message message, Message t1) {
                return message.getDate().compareTo(t1.getDate());
            }
        });
    }
}
