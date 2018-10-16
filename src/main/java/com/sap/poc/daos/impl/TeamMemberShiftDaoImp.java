package com.sap.poc.daos.impl;

import com.sap.poc.daos.TeamMemberShiftDao;
import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamMemberShift;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TeamMemberShiftDaoImp extends HibernateDaoSupport implements TeamMemberShiftDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TeamMemberShiftDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TeamMemberShift teamMemberShift) {
        getHibernateTemplate().save(teamMemberShift);
    }

    @Override
    public void refresh(TeamMemberShift teamMemberShift) {
        getHibernateTemplate().refresh(teamMemberShift);
    }

    @Override
    public void update(TeamMemberShift teamMemberShift) {
        getHibernateTemplate().update(teamMemberShift);
    }

    @Override
    public void delete(TeamMemberShift teamMemberShift) {
        getHibernateTemplate().delete(teamMemberShift);
    }

    @Override
    public TeamMemberShift getTeamMemberShiftById(int id) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(TeamMemberShift.class);
            criteria.add(Restrictions.like("id", id));
            return (TeamMemberShift) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }

    @Override
    public List<TeamMemberShift> getTeamMemberShiftsByMember(TeamMember member) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(TeamMemberShift.class);
            criteria.add(Restrictions.like("member", member));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return (List<TeamMemberShift>) criteria.getExecutableCriteria(session).list();
        }
    }

    @Override
    public List<TeamMemberShift> getTeamMemberShiftByCalendarDate(CalendarDate calendarDate) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(TeamMemberShift.class);
            criteria.add(Restrictions.like("date", calendarDate));
            return (List<TeamMemberShift>) criteria.getExecutableCriteria(session).list();
        }
    }
}
