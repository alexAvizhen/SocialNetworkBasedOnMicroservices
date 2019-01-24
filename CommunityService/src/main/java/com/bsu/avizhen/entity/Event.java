package com.bsu.avizhen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class Event {
    private Integer id;
    private String name;
    private String description;
    private EventInfo eventInfo;
    private Collection<Post> content;
    private Collection<String> relatedLinks;
    private Collection<User> organizers;
    private Collection<User> contacts;
    private Collection<User> participants;
    private Collection<User> possibleParticipants;
}
