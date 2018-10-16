package com.sap.poc.daos.impl;

import com.sap.poc.daos.CalendarDateDao;
import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.Shift;
import com.sap.poc.models.TeamIntervalCalendar;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(CalendarDate.class);
            criteria.add(Restrictions.like("id", id));
            return (CalendarDate) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }

    @Override
    public List<CalendarDate> getCalendarDatesByInterval(TeamIntervalCalendar teamIntervalCalendar) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(CalendarDate.class);
            criteria.add(Restrictions.like("teamIntervalCalendar", teamIntervalCalendar));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return (List<CalendarDate>) criteria.getExecutableCriteria(session).list();
        }
    }

}
