package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.dao.MessageDao;
import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public void addMessage(Chat chat, String message, User user) {
        messageDao.addMessage(chat, message, user);
    }
}
