package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamDao;
import com.sap.poc.daos.TeamIntervalCalendarDao;
import com.sap.poc.daos.UserDao;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.services.TeamIntervalCalendarService;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        Calendar lastDay = new GregorianCalendar(teamIntervalCalendar.getInitDate().get(Calendar.YEAR), 12, 31);
        int yearSizeInDays = lastDay.get(Calendar.DAY_OF_YEAR);
        int initInDaysOfYear = teamIntervalCalendar.getInitDate().get(Calendar.DAY_OF_YEAR);
        int endInDaysOfYear = teamIntervalCalendar.getEndDate().get(Calendar.DAY_OF_YEAR);
        int initYear = teamIntervalCalendar.getInitDate().get(Calendar.YEAR);
        int endYear = teamIntervalCalendar.getEndDate().get(Calendar.YEAR);
        return (endYear - initYear)*yearSizeInDays + endInDaysOfYear - initInDaysOfYear + 1;
    }

    @Override
    public List<Calendar> getDateListOfInterval(TeamIntervalCalendar teamIntervalCalendar) {
        int size = getIntervalSize(teamIntervalCalendar);

        List<Calendar> dates = new ArrayList<>();
        Calendar initDate = teamIntervalCalendar.getInitDate();

        for(int i = 0; i < size; i++){
            dates.add(new GregorianCalendar(initDate.get(Calendar.YEAR), initDate.get(Calendar.MONTH), initDate.get(Calendar.DAY_OF_MONTH)));
            dates.get(i).add(Calendar.DATE, i);
        }
        return dates;
    }

    @Override
    public List<TeamIntervalCalendar> getTeamIntervalCalendarByTeamId(int teamId) {
        Team team = teamService.getTeamById(teamId);
        return hibernateTeamIntervalCalendarDao.getTeamIntervalCalendarByTeam(team);
    }

    @Override
    public List<TeamIntervalCalendar> getTeamIntervalCalendarByTeam(Team team) {
        return hibernateTeamIntervalCalendarDao.getTeamIntervalCalendarByTeam(team);
    }


}
