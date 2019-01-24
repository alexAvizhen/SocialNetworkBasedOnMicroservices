package com.bsu.avizhen.dao.impl;

import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.dao.UserDao;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    private static Map<Integer, User> users = new HashMap<>();
    private static Integer userIdCounter = 1;
    static {
        users.put(1, new User(1, "Alexander", "Avizhen", "alex.avizhen@netcracker.com", "Junior", "alav"));
        users.put(2, new User(2, "Kirill", "Ivanov", "kirill.ivanov@netcracker.com", "Middle", "ivki"));
        users.put(3, new User(3, "Ivan", "Petrov", "ivan.petrov@netcracker.com", "Middle", "peiv"));
    }


    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

    @Override
    public User getUserById(Integer id) {
        return users.get(id);
    }

    @Override
    public void createUser(User user) {
        if (user.getId() == null) {
            user.setId(generateId());
        }
        users.put(user.getId(), user);
    }

    @Override
    public User getUserBySurname(String surname) {
        for (User user : getAllUsers()) {
            if (surname.equals(user.getSurname())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : getAllUsers()) {
            if (login.equals(user.getLogin())) {
                return user;
            }
        }
        return null;
    }

    private Integer generateId() {
        while (users.containsKey(userIdCounter)) {
            userIdCounter++;
        }
        return userIdCounter;
    }
}
