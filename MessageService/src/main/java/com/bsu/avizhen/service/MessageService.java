package com.bsu.avizhen.service;


import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.User;

public interface MessageService {
    void addMessage(Chat chat, String message, User user);
}
