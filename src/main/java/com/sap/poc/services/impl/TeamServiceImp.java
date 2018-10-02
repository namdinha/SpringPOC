package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamDao;
import com.sap.poc.daos.impl.TeamDaoImp;
import com.sap.poc.models.Team;
import com.sap.poc.services.TeamService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class TeamServiceImp implements TeamService {

    @Resource
    private TeamDao hibernateTeamDao;

    public TeamServiceImp(TeamDao hibernateTeamDao) {
        this.hibernateTeamDao = hibernateTeamDao;
    }

    @Override
    public void create(Team team) {
        hibernateTeamDao.create(team);
    }

    @Override
    public void refresh(Team team) {
        hibernateTeamDao.refresh(team);
    }

    @Override
    public void update(Team team) {
        hibernateTeamDao.update(team);
    }

    @Override
    public void delete(Team team) {
        hibernateTeamDao.delete(team);
    }

    @Override
    @Transactional
    public List<Team> getTeams() {
        return hibernateTeamDao.getTeams();
    }

    @Override
    @Transactional
    public Team getTeamByOwner(String owner) {
        return hibernateTeamDao.getTeamByOwner(owner);
    }
}
