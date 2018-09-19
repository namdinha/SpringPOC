package com.sap.poc.daos.impl;

import com.sap.poc.daos.UserDao;
import com.sap.poc.models.Team;
import com.sap.poc.models.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImp extends HibernateDaoSupport implements UserDao {

    @Override
    @Transactional
    public void create(User user) {
        getHibernateTemplate().save(user);
    }

    @Override
    public void refresh(User user) {
        getHibernateTemplate().refresh(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        getHibernateTemplate().update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        getHibernateTemplate().delete(user);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
