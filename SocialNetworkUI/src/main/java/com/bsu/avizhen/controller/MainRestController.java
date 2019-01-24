package com.bsu.avizhen.controller;

import com.bsu.avizhen.entity.*;
import com.bsu.avizhen.rest.MessageService;
import com.bsu.avizhen.rest.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api")
@SessionAttributes({"currentUser"})
public class MainRestController {
    @Autowired
    private UserRestService userRestService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/users/colleagues", method = RequestMethod.GET)
    public Collection<User> getPossibleFriends(@ModelAttribute("currentUser") User currentUser) {
        if (currentUser != null) {
            return userRestService.getPossibleFriends(currentUser.getId());
        }
        return new ArrayList<User>();
    }


    @RequestMapping(value = "/users/friends", method = RequestMethod.GET)
    public Collection<User> getUserFriends(@ModelAttribute("currentUser") User currentUser) {
        if (currentUser != null) {
            return userRestService.getUserFriends(currentUser.getId());
        }
        return new ArrayList<User>();
    }

    @RequestMapping(value = "/users/friends/{friendId}", method = RequestMethod.GET)
    public void addFriend(@ModelAttribute("currentUser") User currentUser, @PathVariable("friendId") Integer friendId) {
        if (currentUser != null) {
            userRestService.addToFriend(currentUser.getId(), friendId);
        }
    }

    @RequestMapping(value = "/users/chats", method = RequestMethod.GET)
    public Collection<Chat> getChats(@ModelAttribute("currentUser") User currentUser) {
        System.out.println("trying get chats for user");
        if (currentUser != null) {
            return messageService.getChatsByUserId(currentUser.getId());
        }
        return new ArrayList<Chat>();
    }

    @RequestMapping(value = "/chats/messages/{chatId}", method = RequestMethod.GET)
    public Collection<Message> getMessagesByChatId(@ModelAttribute("currentUser") User currentUser, @PathVariable("chatId") Integer chatId) {
        System.out.println("trying get messages by chat");
        if (currentUser != null) {
            return messageService.getMessagesByChatId(chatId);
        }
        return new ArrayList<Message>();
    }

    @RequestMapping(value = "/users/chats/{friendId}", method = RequestMethod.GET)
    public void createChat(@ModelAttribute("currentUser") User currentUser, @PathVariable("friendId") Integer friendId) {
        System.out.println("creating chat");
        Collection<Integer> ownerIds = new ArrayList<Integer>();
        ownerIds.add(friendId);
        ownerIds.add(currentUser.getId());
        messageService.createChat(ownerIds);
    }

    @RequestMapping(value = "/chats/messages", method = RequestMethod.POST)
    public void sendMessage(@ModelAttribute("currentUser") User currentUser, @RequestBody MessageInfoWithoutUserId messageInfoWithoutUserId) {
        MessageInfo messageInfo = new MessageInfo();
        System.out.println("sending message");
        if (currentUser != null) {
            messageInfo.setUserId(currentUser.getId());
            messageInfo.setChatId(messageInfoWithoutUserId.getChatId());
            messageInfo.setMessage(messageInfoWithoutUserId.getMessage());
            messageService.sendMessage(messageInfo);
        }
    }

    @RequestMapping(value = "/chats/messages/test", method = RequestMethod.POST)
    public void sendMessageTest(@RequestBody MessageInfoWithoutUserId messageInfoWithoutUserId) {
        MessageInfo messageInfo = new MessageInfo();
        System.out.println("sending message");
            messageInfo.setUserId(1);
            messageInfo.setChatId(messageInfoWithoutUserId.getChatId());
            messageInfo.setMessage(messageInfoWithoutUserId.getMessage());
            messageService.sendMessage(messageInfo);
    }
}
