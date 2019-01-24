package com.bsu.avizhen.rest;

import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.Message;
import com.bsu.avizhen.entity.MessageInfo;

import java.util.Collection;

public interface MessageService {
    Collection<Chat> getChatsByUserId(Integer userId);

    void createChat(Collection<Integer> ownerIds);

    Collection<Message> getMessagesByChatId(Integer chatId);

    void sendMessage(MessageInfo messageInfo);
}
