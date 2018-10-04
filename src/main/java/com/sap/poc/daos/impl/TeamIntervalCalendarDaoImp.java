package com.sap.poc.daos.impl;

import com.sap.poc.daos.TeamIntervalCalendarDao;
import com.sap.poc.models.TeamIntervalCalendar;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }
}
