package com.bsu.avizhen.service.impl;

import com.bsu.avizhen.dao.ChatDao;
import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.rest.service.UserRestService;
import com.bsu.avizhen.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {


    private ChatDao chatDao;
    private UserRestService userRestService;

    @Autowired
    public ChatServiceImpl(UserRestService userRestService, ChatDao chatDao) {
        this.userRestService = userRestService;
        this.chatDao = chatDao;
    }

    @Override
    public Chat createChat(Collection<Integer> ownerIDs) {
        List<User> owners = new ArrayList<>();
        for (Integer ownerID : ownerIDs) {
            User owner = userRestService.getUserById(ownerID);
            if (owner != null) {
                owners.add(owner);
            }
        }
        return chatDao.createChat(owners);
    }

    @Override
    public Chat getChatById(Integer id) {
        return chatDao.getChatById(id);
    }

    @Override
    public List<Chat> getAllChatsByUserId(Integer userId) {
        List<Chat> resList = new ArrayList<>();
        for (Chat chat : chatDao.getAllChats()) {
            if (chat.getOwners() == null || chat.getOwners().isEmpty()) {
                continue;
            }
            Iterator<User> ownerIter = chat.getOwners().iterator();
            boolean isDetected = false;
            while (ownerIter.hasNext() && !isDetected) {
                User currentOwner = ownerIter.next();
                if (userId.equals(currentOwner.getId())) {
                    resList.add(chat);
                    isDetected = true;
                }
            }
        }
        return resList;
    }
}
