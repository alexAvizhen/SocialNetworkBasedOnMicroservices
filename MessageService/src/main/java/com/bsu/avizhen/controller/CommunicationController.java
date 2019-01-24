package com.bsu.avizhen.controller;

import com.bsu.avizhen.dao.MessageDao;
import com.bsu.avizhen.entity.*;
import com.bsu.avizhen.rest.service.UserRestService;
import com.bsu.avizhen.service.ChatService;
import com.bsu.avizhen.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommunicationController {

    private MessageService messageService;
    private ChatService chatService;
    private UserRestService userRestService;

    @Autowired
    public CommunicationController(MessageService messageService, ChatService chatService, UserRestService userRestService) {
        this.messageService = messageService;
        this.chatService = chatService;
        this.userRestService = userRestService;
    }

    @RequestMapping(value = {"/chat"}, method = RequestMethod.PUT)
    public Object createChat(@RequestBody Collection<Integer> ownerIDs) {
        return chatService.createChat(ownerIDs);
    }

    @RequestMapping(value = {"/chat/user/{userId}"}, method = RequestMethod.GET)
    public Object getChatsByUserId(@PathVariable("userId") String userIdStr) {
        Integer userId;
        try {
            userId = new Integer(userIdStr);
            return chatService.getAllChatsByUserId(userId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @RequestMapping(value = {"/messages"}, method = RequestMethod.PUT)
    public void addMessage(@RequestBody MessageInfo messageInfo) {
        Chat chat = chatService.getChatById(messageInfo.getChatId());
        User user = userRestService.getUserById(messageInfo.getUserId());
        if (chat == null || user == null) {
            return;
        }
        messageService.addMessage(chat, messageInfo.getMessage(), user);
    }


    @RequestMapping(value = {"/chat/messages/{chatId}"}, method = RequestMethod.GET)
    public Object getMessageByChatId(@PathVariable("chatId") Integer chatId) {
        Chat chat = chatService.getChatById(chatId);
        List<MessageDto> resList = new ArrayList<>();
        if (chat != null && chat.getMessages() != null) {
            for (Message message : chat.getMessages()) {
                resList.add(convertToMsgDto(message));
            }
        }
        return resList;
    }

    private MessageDto convertToMsgDto(Message message) {
        MessageDto resMsg = new MessageDto();
        resMsg.setLogin(message.getOwner().getLogin());
        resMsg.setMessage(message.getMessage());
        return resMsg;
    }


}
