package com.bsu.avizhen.dao;

import com.bsu.avizhen.entity.User;

import java.util.Collection;

public interface UserDao {

    Collection<User> getAllUsers();

    User getUserById(Integer id);

    void createUser(User user);

    User getUserBySurname(String surname);

    User getUserByLogin(String login);
}
