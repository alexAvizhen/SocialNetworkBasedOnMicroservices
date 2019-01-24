package com.bsu.avizhen.rest.service;

import com.bsu.avizhen.entity.User;

public interface UserRestService {
    User getUserByLogin(String login);

    User getUserById(Integer id);
}
