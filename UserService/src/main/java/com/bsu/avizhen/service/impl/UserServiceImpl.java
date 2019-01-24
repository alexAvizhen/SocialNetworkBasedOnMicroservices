package com.bsu.avizhen.service.impl;


import com.bsu.avizhen.dao.UserDao;
import com.bsu.avizhen.entity.User;
import com.bsu.avizhen.service.UserService;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User getUserBySurname(String surname) {
        return userDao.getUserBySurname(surname);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public Collection<User> filterUsers(Collection<User> users, Iterable<Predicate<? super User>> predicates) {
        return Collections2.filter(users, Predicates.and(predicates));
    }

    @Override
    public Collection<User> getPossibleFriends(User user) {
        List<User> possibleFriends = new ArrayList<>();
        for (User possibleFriend : getAllUsers()) {
            boolean isUserFriend = true;
            for (User userFriend : user.getFriends()) {
                if (userFriend.getId().equals(possibleFriend.getId())) {
                    isUserFriend = false;
                }
            }
            if (isUserFriend && !user.getId().equals(possibleFriend.getId())) {
                possibleFriends.add(possibleFriend);
            }
        }
        return possibleFriends;
    }


}
