package com.bsu.avizhen.service;

import com.bsu.avizhen.entity.User;
import com.google.common.base.Predicate;

import java.util.Collection;

public interface UserService {
    Collection<User> getAllUsers();

    User getUserById(Integer id);

    void createUser(User user);

    User getUserBySurname(String surname);

    User getUserByLogin(String login);

    Collection<User> filterUsers(Collection<User> users, Iterable<Predicate<? super User>> predicates);

    Collection<User> getPossibleFriends(User user);
}
