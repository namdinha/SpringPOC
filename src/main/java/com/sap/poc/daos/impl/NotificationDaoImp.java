package com.sap.poc.daos.impl;

import com.sap.poc.daos.NotificationDao;
import com.sap.poc.models.Notification;
import com.sap.poc.models.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class NotificationDaoImp extends HibernateDaoSupport implements NotificationDao {

    private SessionFactory sessionFactory;

    @Autowired
    public NotificationDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Notification notification) {
        getHibernateTemplate().save(notification);
    }

    @Override
    public void refresh(Notification notification) {
        getHibernateTemplate().refresh(notification);
    }

    @Override
    public void update(Notification notification) {
        getHibernateTemplate().update(notification);
    }

    @Override
    public void delete(Notification notification) {
        getHibernateTemplate().delete(notification);
    }

    @Override
    public Notification getNotificationById(int id) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Notification.class);
            criteria.add(Restrictions.like("id", id));
            return (Notification) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }

    @Override
    public List<Notification> getNotificationsByTeam(Team team) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Notification.class);
            criteria.add(Restrictions.like("team", team));
            return (List<Notification>) criteria.getExecutableCriteria(session).list();
        }
    }
}
