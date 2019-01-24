package com.bsu.avizhen.service;

import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.User;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.List;

public interface ChatService {
    Chat createChat(Collection<Integer> ownerIDs);

    Chat getChatById(Integer id);

    List<Chat> getAllChatsByUserId(Integer userId);
}
