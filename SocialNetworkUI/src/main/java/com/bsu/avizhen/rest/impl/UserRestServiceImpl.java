package com.bsu.avizhen.rest.impl;

import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.entity.UserToFriend;
import com.bsu.avizhen.rest.UserRestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserRestServiceImpl implements UserRestService {
    private String userServiceUrl = "http://localhost:8090/api";
    private RestTemplate restTemplate;

    public UserRestServiceImpl() {
        restTemplate = new RestTemplate();

    }

    @Override
    public User loginUser(String login, String password) {
        User resUser;
        try {
            String findUserRequest = userServiceUrl + "/users/login/" + login;
            resUser = restTemplate.getForObject(findUserRequest, User.class);
        } catch (RestClientException e) {
            resUser = new User();
        }
        return resUser;
    }

    @Override
    public Collection<User> getPossibleFriends(Integer userId) {
        String findPossibleFriendsRequest = userServiceUrl + "/users/colleagues/" + userId;
        Collection<User> possibleFriends = restTemplate.getForObject(findPossibleFriendsRequest, Collection.class);
        if (possibleFriends != null) {
            return possibleFriends;
        }
        return new ArrayList<User>();
    }

    @Override
    public Collection<User> getUserFriends(Integer userId) {

        String findUserRequest = userServiceUrl + "/users/" + userId;
        User resUser = restTemplate.getForObject(findUserRequest, User.class);
        if (resUser != null) {
            return resUser.getFriends();
        }
        return new ArrayList<User>();
    }

    @Override
    public void addToFriend(Integer userId, Integer friendId) {
        String findPossibleFriendsRequest = userServiceUrl + "/users/friends";
        UserToFriend userToFriend = new UserToFriend(userId, friendId);

        restTemplate.put(findPossibleFriendsRequest, userToFriend);

    }
}
