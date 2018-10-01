package com.sap.poc.daos.impl;

import com.sap.poc.daos.UserDao;
import com.sap.poc.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImp extends HibernateDaoSupport implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(User user) {
        try{
            getUserByLogin(user.getUsername());
            System.out.println("Username already in use."); // Put exception here.
            return;
        }
        catch(Exception e){
            getHibernateTemplate().save(user);
        }
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
    @Transactional
    public User getUserByName(String name) {
        try (Session session = sessionFactory.openSession())
        {
            DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
            criteria.add(Restrictions.like("name", name));
            return (User) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        try (Session session = sessionFactory.openSession())
        {
            DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
            criteria.add(Restrictions.like("username", login));
            return (User) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return (List<User>) getHibernateTemplate().find("from com.sap.poc.models.User");
    }
}
