package com.sap.poc.daos.impl;

import com.sap.poc.daos.UserDao;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
            return (List<User>) criteria.getExecutableCriteria(session).list();
        }
    }

    @Override
    public Set<TeamMember> getTeamMembers(int teamId) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
            criteria.add(Restrictions.like("id", teamId));
            Team team = (Team) criteria.getExecutableCriteria(session).uniqueResult();

            criteria = DetachedCriteria.forClass(User.class);
            criteria.add(Restrictions.like("team", team));
            return new HashSet<>(criteria.getExecutableCriteria(session).list());
        }
    }
}
