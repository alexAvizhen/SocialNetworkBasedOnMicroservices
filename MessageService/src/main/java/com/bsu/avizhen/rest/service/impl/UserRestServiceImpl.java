package com.bsu.avizhen.rest.service.impl;

import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.rest.service.UserRestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRestServiceImpl implements UserRestService{
    private RestTemplate restTemplate;

    public UserRestServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public User getUserByLogin(String login) {
        User resUser;
        try {
            String findUserRequest = "http://localhost:8090/api/users/login/" + login;
            resUser = restTemplate.getForObject(findUserRequest, User.class);
        } catch (RestClientException e) {
            resUser = new User();
            resUser.setId(-1);
            resUser.setLogin("User with login " + login + "not found, error: " + e.getMessage());
        }
        return resUser;
    }

    @Override
    public User getUserById(Integer id) {
        User resUser;
        try {
            String findUserRequest = "http://localhost:8090/api/users/" + id;
            resUser = restTemplate.getForObject(findUserRequest, User.class);
        } catch (RestClientException e) {
            resUser = new User();
            resUser.setId(-1);
            resUser.setLogin("User with id " + id + "not found, error: " + e.getMessage());
        }
        return resUser;
    }
}
