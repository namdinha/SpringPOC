package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamDao;
import com.sap.poc.models.Team;
import com.sap.poc.services.TeamService;

import javax.annotation.Resource;
import java.util.List;

public class TeamServiceImp implements TeamService {

    @Resource
    private TeamDao teamDao;

    @Override
    public void create(Team team) {
        teamDao.create(team);
    }

    @Override
    public void refresh(Team team) {
        teamDao.refresh(team);
    }

    @Override
    public void update(Team team) {
        teamDao.update(team);
    }

    @Override
    public void delete(Team team) {
        teamDao.delete(team);
    }

    @Override
    public List<Team> getTeams() {
        return teamDao.getTeams();
    }

    @Override
    public Team getTeamByOwner(String owner) {
        return teamDao.getTeamByOwner(owner);
    }
}
