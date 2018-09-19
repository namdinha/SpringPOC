package com.sap.poc.daos.impl;

import com.sap.poc.daos.TeamDao;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamOwner;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TeamDaoImp extends HibernateDaoSupport implements TeamDao {

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
    public List<Team> getTeams() {
        return (List<Team>) getHibernateTemplate().find("from com.sap.poc.models.Team");
    }

    @Override
    public Team getTeamByOwner(String owner) {
        TeamOwner teamOwner = (TeamOwner) getHibernateTemplate().find("from com.sap.poc.models.TeamOwner as t where t.name=" + owner).get(0);
        return (Team) getHibernateTemplate().find("from com.sap.poc.models.Team as t where t.owner=" + teamOwner.toString()).get(0);
    }
}
