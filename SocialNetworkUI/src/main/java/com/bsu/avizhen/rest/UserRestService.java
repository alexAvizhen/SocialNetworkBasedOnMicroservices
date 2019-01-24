package com.bsu.avizhen.rest;

import com.bsu.avizhen.entity.User;

import java.util.Collection;

public interface UserRestService {
    User loginUser(String login, String password);

    Collection<User> getPossibleFriends(Integer userId);

    Collection<User> getUserFriends(Integer userId);

    void addToFriend(Integer userId, Integer friendId);
}
