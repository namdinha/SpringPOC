package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamIntervalCalendarDao;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.services.TeamIntervalCalendarService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TeamIntervalCalendarServiceImp implements TeamIntervalCalendarService {

    @Resource
    private TeamIntervalCalendarDao hibernateTeamIntervalCalendarDao;

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
        return (endYear - initYear)*yearSizeInDays + endInDaysOfYear - initInDaysOfYear;
    }
}
