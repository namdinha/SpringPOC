package com.sap.poc.services.impl;

import com.sap.poc.daos.UserDao;
import com.sap.poc.models.User;
import com.sap.poc.services.UserService;

import javax.annotation.Resource;
import java.util.List;

public class UserServiceImp implements UserService {

    @Resource
    private UserDao hibernateUserDao;

    public UserServiceImp(UserDao hibernateUserDao) {
        this.hibernateUserDao = hibernateUserDao;
    }

    @Override
    public void create(User user) {
        hibernateUserDao.create(user);
    }

    @Override
    public void refresh(User user) {
        hibernateUserDao.refresh(user);
    }

    @Override
    public void update(User user) {
        hibernateUserDao.update(user);
    }

    @Override
    public void delete(User user) {
        hibernateUserDao.delete(user);
    }

    @Override
    public User getUserByName(String name) {
        return hibernateUserDao.getUserByName(name);
    }

    @Override
    public User getUserByLogin(String login) {
        return hibernateUserDao.getUserByLogin(login);
    }

    @Override
    public List<User> getUsers() {
        return hibernateUserDao.getUsers();
    }
}
