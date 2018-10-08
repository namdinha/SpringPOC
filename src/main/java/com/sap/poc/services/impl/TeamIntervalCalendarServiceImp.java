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
//        Calendar lastDay = new GregorianCalendar(teamIntervalCalendar.getInitDate().get(Calendar.YEAR), 12, 31);
//        int yearSizeInDays = lastDay.get(Calendar.DAY_OF_YEAR);
//        int initInDaysOfYear = teamIntervalCalendar.getInitDate().get(Calendar.DAY_OF_YEAR);
//        int endInDaysOfYear = teamIntervalCalendar.getEndDate().get(Calendar.DAY_OF_YEAR);
//        int initYear = teamIntervalCalendar.getInitDate().get(Calendar.YEAR);
//        int endYear = teamIntervalCalendar.getEndDate().get(Calendar.YEAR);
//        return (endYear - initYear)*yearSizeInDays + endInDaysOfYear - initInDaysOfYear + 1;

        long timeBetweenInitEnd = teamIntervalCalendar.getEndDate().getTime() - teamIntervalCalendar.getInitDate().getTime();
        timeBetweenInitEnd = timeBetweenInitEnd/1000; //sec
        timeBetweenInitEnd = timeBetweenInitEnd/60; //min
        timeBetweenInitEnd = timeBetweenInitEnd/60; //hour
        timeBetweenInitEnd = timeBetweenInitEnd/24; //day

        return (int) timeBetweenInitEnd;
    }

    @Override
    public List<Date> getDateListOfInterval(TeamIntervalCalendar teamIntervalCalendar) {
        int size = getIntervalSize(teamIntervalCalendar);

        List<Date> dates = new ArrayList<>();
//        Calendar initDate = teamIntervalCalendar.getInitDate();

//        for(int i = 0; i < size; i++){
//            dates.add(new GregorianCalendar(initDate.get(Calendar.YEAR), initDate.get(Calendar.MONTH), initDate.get(Calendar.DAY_OF_MONTH)));
//            dates.get(i).add(Calendar.DATE, i);
//        }
        Date initDate = teamIntervalCalendar.getInitDate();
        long time;
        for(int i = 0; i < size; i++){
            time = initDate.getTime() + 1000*60*60*24;
            dates.add(new Date(time));
        }
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

    @Override
    public List<List<Date>> getDateListsOfIntervals(Team team){
        List<List<Date>> intervalsDates = new ArrayList<>();
        List<TeamIntervalCalendar> intervals = hibernateTeamIntervalCalendarDao.getTeamIntervalsCalendarByTeam(team);
        for(TeamIntervalCalendar interval:intervals){
            intervalsDates.add(getDateListOfInterval(interval));
        }
        return intervalsDates;
    }

}
