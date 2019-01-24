package com.bsu.avizhen.dao.impl;

import com.bsu.avizhen.dao.MessageDao;
import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.Message;
import com.bsu.avizhen.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageDaoImpl implements MessageDao {
    private static Map<Integer, Message> messages = new HashMap<>();

    @Override
    public void addMessage(Chat chat, String message, User user) {
        Message msg = new Message(generateMsgId(), message, new Date(), user);
        messages.put(msg.getId(), msg);
        chat.getMessages().add(msg);
    }

    private Integer generateMsgId() {
        Integer id = 1;
        while (messages.containsKey(id)) {
            id++;
        }
        return id;
    }
}
