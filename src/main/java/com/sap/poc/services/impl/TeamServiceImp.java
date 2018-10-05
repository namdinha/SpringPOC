package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamDao;
import com.sap.poc.daos.UserDao;
import com.sap.poc.daos.impl.TeamDaoImp;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class TeamServiceImp implements TeamService {

    @Resource
    private TeamDao hibernateTeamDao;
    @Resource
    private UserService userService;

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
    public List<Team> getTeams() {
        return hibernateTeamDao.getTeams();
    }

    @Override
    public Team getTeamByOwner(String ownerUsername) {
        TeamOwner owner = (TeamOwner) userService.getUserByLogin(ownerUsername);
        return hibernateTeamDao.getTeamByOwner(owner);
    }

    @Override
    public Team getTeamById(int teamId) {
        return hibernateTeamDao.getTeamById(teamId);
    }
}
