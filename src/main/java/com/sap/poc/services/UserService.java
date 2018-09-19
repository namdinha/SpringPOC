package com.sap.poc.services;

import com.sap.poc.models.User;

import java.util.List;

public interface UserService {

    void create(User user);
    void refresh(User user);
    void update(User user);
    void delete(User user);
    User getUserByName(String name);
    User getUserByLogin(String login);
    List<User> getUsers();
}
