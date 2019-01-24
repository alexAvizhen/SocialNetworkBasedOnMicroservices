package com.bsu.avizhen.dao.impl;

import com.bsu.avizhen.dao.ChatDao;
import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatDaoImpl implements ChatDao {
    private static Map<Integer, Chat> chats;

    static {
        chats = new HashMap<>();
    }

    public Collection<Chat> getAllChats() {
        return chats.values();
    }

    @Override
    public Chat getChatById(Integer id) {
        return chats.get(id);
    }

    @Override
    public Chat createChat(List<User> owners) {
        Integer id = generateChatId();
        Chat createdChat = new Chat(id, owners);
        chats.put(id, createdChat);
        return createdChat;
    }

    private Integer generateChatId() {
        Integer id = 1;
        while (chats.containsKey(id)) {
            id++;
        }
        return id;
    }



}
