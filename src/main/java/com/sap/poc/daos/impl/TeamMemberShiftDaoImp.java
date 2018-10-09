package com.sap.poc.daos.impl;

import com.sap.poc.daos.TeamMemberShiftDao;
import com.sap.poc.models.TeamMemberShift;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }
}
