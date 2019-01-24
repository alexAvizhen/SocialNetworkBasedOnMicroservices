package com.bsu.avizhen.rest.impl;

import com.bsu.avizhen.entity.Chat;
import com.bsu.avizhen.entity.Message;
import com.bsu.avizhen.entity.MessageInfo;
import com.bsu.avizhen.rest.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MessageServiceImpl implements MessageService {

    private String messageServiceUrl = "http://localhost:8091/api";
    private RestTemplate restTemplate;

    public MessageServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public Collection<Chat> getChatsByUserId(Integer userId) {
        String getChatForUserRequestUrl = messageServiceUrl + "/chat/user/" + userId;
        Collection<Chat> userChats = restTemplate.getForObject(getChatForUserRequestUrl, Collection.class);
        if (userChats != null) {
            return userChats;
        }
        return new ArrayList<Chat>();
    }

    @Override
    public void createChat(Collection<Integer> ownerIds) {
        String createChatByOwners = messageServiceUrl + "/chat";
        restTemplate.put(createChatByOwners, ownerIds);
    }

    @Override
    public Collection<Message> getMessagesByChatId(Integer chatId) {
        String getMessageByChatIdRequest = messageServiceUrl + "/chat/messages/" + chatId;
        Collection<Message> resList = restTemplate.getForObject(getMessageByChatIdRequest, Collection.class);
        if (resList != null) {
            return resList;
        }

        return new ArrayList<Message>();
    }

    @Override
    public void sendMessage(MessageInfo messageInfo) {
        String createChatByOwners = messageServiceUrl + "/messages";
        restTemplate.put(createChatByOwners, messageInfo);
    }


}
