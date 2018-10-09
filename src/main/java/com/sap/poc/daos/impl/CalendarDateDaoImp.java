package com.sap.poc.daos.impl;

import com.sap.poc.daos.CalendarDateDao;
import com.sap.poc.models.CalendarDate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CalendarDateDaoImp extends HibernateDaoSupport implements CalendarDateDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CalendarDateDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(CalendarDate calendarDate) {
        getHibernateTemplate().save(calendarDate);
    }

    @Override
    public void refresh(CalendarDate calendarDate) {
        getHibernateTemplate().refresh(calendarDate);
    }

    @Override
    public void update(CalendarDate calendarDate) {
        getHibernateTemplate().update(calendarDate);
    }

    @Override
    public void delete(CalendarDate calendarDate) {
        getHibernateTemplate().delete(calendarDate);
    }

    @Override
    public CalendarDate getCalendarDateById(int id) {
        return null;
    }
}
