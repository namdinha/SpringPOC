package com.sap.poc.daos.impl;

import com.sap.poc.daos.TeamIntervalCalendarDao;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TeamIntervalCalendarDaoImp extends HibernateDaoSupport implements TeamIntervalCalendarDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TeamIntervalCalendarDaoImp(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(TeamIntervalCalendar teamIntervalCalendar) {
        getHibernateTemplate().save(teamIntervalCalendar);
    }

    @Override
    @Transactional
    public void refresh(TeamIntervalCalendar teamIntervalCalendar) {
        getHibernateTemplate().refresh(teamIntervalCalendar);
    }

    @Override
    @Transactional
    public void update(TeamIntervalCalendar teamIntervalCalendar) {
        getHibernateTemplate().update(teamIntervalCalendar);
    }

    @Override
    @Transactional
    public void delete(TeamIntervalCalendar teamIntervalCalendar) {
        getHibernateTemplate().delete(teamIntervalCalendar);
    }

    @Override
    @Transactional
    public TeamIntervalCalendar getTeamIntervalCalendarById(int id) {

        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(TeamIntervalCalendar.class);
            criteria.add(Restrictions.like("id", id));
            return (TeamIntervalCalendar) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }

    @Override
    public List<TeamIntervalCalendar> getTeamIntervalsCalendarByTeam(Team team) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(TeamIntervalCalendar.class);
            criteria.add(Restrictions.like("team", team));
            return (List<TeamIntervalCalendar>) criteria.getExecutableCriteria(session).list();
        }
    }
}
