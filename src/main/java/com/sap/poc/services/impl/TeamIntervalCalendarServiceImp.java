package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamIntervalCalendarDao;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.services.TeamIntervalCalendarService;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;

import javax.annotation.Resource;
import java.util.*;

public class TeamIntervalCalendarServiceImp implements TeamIntervalCalendarService {

    @Resource
    private TeamIntervalCalendarDao hibernateTeamIntervalCalendarDao;
    @Resource
    private TeamService teamService;
    @Resource
    private UserService userService;

    public TeamIntervalCalendarServiceImp(TeamIntervalCalendarDao hibernateTeamIntervalCalendarDao) {
        this.hibernateTeamIntervalCalendarDao = hibernateTeamIntervalCalendarDao;
    }

    @Override
    public void create(TeamIntervalCalendar teamIntervalCalendar) {
        hibernateTeamIntervalCalendarDao.create(teamIntervalCalendar);
    }

    @Override
    public void refresh(TeamIntervalCalendar teamIntervalCalendar) {
        hibernateTeamIntervalCalendarDao.refresh(teamIntervalCalendar);
    }

    @Override
    public void update(TeamIntervalCalendar teamIntervalCalendar) {
        hibernateTeamIntervalCalendarDao.update(teamIntervalCalendar);
    }

    @Override
    public void delete(TeamIntervalCalendar teamIntervalCalendar) {
        hibernateTeamIntervalCalendarDao.delete(teamIntervalCalendar);
    }

    @Override
    public TeamIntervalCalendar getTeamIntervalCalendarById(int id) {
        return hibernateTeamIntervalCalendarDao.getTeamIntervalCalendarById(id);
    }

    @Override
    public int getIntervalSize(TeamIntervalCalendar teamIntervalCalendar) {
        return 0;
    }

    @Override
    public List<Date> getDateListOfInterval(TeamIntervalCalendar teamIntervalCalendar) {
        List<Date> dates = new ArrayList<>();
        return dates;
    }

    @Override
    public List<TeamIntervalCalendar> getTeamIntervalCalendarByTeamId(int teamId) {
        Team team = teamService.getTeamById(teamId);
        return hibernateTeamIntervalCalendarDao.getTeamIntervalsCalendarByTeam(team);
    }

    @Override
    public List<TeamIntervalCalendar> getTeamIntervalsCalendarByTeam(Team team) {
        return hibernateTeamIntervalCalendarDao.getTeamIntervalsCalendarByTeam(team);
    }

}
