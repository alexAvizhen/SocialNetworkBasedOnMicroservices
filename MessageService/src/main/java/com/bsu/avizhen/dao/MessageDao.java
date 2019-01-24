package com.bsu.avizhen.dao;

import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.User;

public interface MessageDao {
    void addMessage(Chat chat, String message, User user);
}
