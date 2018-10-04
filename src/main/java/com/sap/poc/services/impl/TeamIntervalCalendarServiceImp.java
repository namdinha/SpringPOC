package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamIntervalCalendarDao;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.services.TeamIntervalCalendarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
