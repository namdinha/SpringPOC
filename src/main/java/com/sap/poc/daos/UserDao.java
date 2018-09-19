package com.sap.poc.daos;

import com.sap.poc.models.User;

import java.util.List;

public interface UserDao {

    void create(User user);
    void refresh(User user);
    void update(User user);
    void delete(User user);
    User getUserByName(String name);
    User getUserByLogin(String login);
    List<User> getUsers();

}
