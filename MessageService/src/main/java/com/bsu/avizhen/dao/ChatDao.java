package com.bsu.avizhen.dao;

import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.User;

import java.util.Collection;
import java.util.List;

public interface ChatDao {
    Chat getChatById(Integer id);

    Chat createChat(List<User> owners);

    Collection<Chat> getAllChats();
}
