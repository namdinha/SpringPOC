package com.sap.poc.daos.impl;

import com.sap.poc.daos.TeamDao;
import com.sap.poc.daos.UserDao;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TeamDaoImp extends HibernateDaoSupport implements TeamDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TeamDaoImp(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(Team team) {
        getHibernateTemplate().save(team);
    }

    @Override
    public void refresh(Team team) {
        getHibernateTemplate().refresh(team);
    }

    @Override
    @Transactional
    public void update(Team team) {
        getHibernateTemplate().update(team);
    }

    @Override
    @Transactional
    public void delete(Team team) {
        getHibernateTemplate().delete(team);
    }

    @Override
    @Transactional
    public List<Team> getTeams() {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
            return (List<Team>) criteria.getExecutableCriteria(session).list();
        }
    }

    @Override
    @Transactional
    public Team getTeamById(int teamId){
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
            criteria.add(Restrictions.like("id", teamId));
            return (Team) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }

    @Override
    @Transactional
    public Team getTeamByOwner(TeamOwner owner) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);
            criteria.add(Restrictions.like("owner", owner));
            return (Team) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }
}
