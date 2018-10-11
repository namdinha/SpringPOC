package com.sap.poc.services.impl;

import com.sap.poc.daos.CalendarDateDao;
import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.services.CalendarDateService;

import javax.annotation.Resource;
import java.util.List;

public class CalendarDateServiceImp implements CalendarDateService {

    @Resource
    private CalendarDateDao hibernateCalendarDateDao;

    public CalendarDateServiceImp(CalendarDateDao hibernateCalendarDateDao) {
        this.hibernateCalendarDateDao = hibernateCalendarDateDao;
    }

    @Override
    public void create(CalendarDate calendarDate) {
        hibernateCalendarDateDao.create(calendarDate);
    }

    @Override
    public void refresh(CalendarDate calendarDate) {
        hibernateCalendarDateDao.refresh(calendarDate);
    }

    @Override
    public void update(CalendarDate calendarDate) {
        hibernateCalendarDateDao.update(calendarDate);
    }

    @Override
    public void delete(CalendarDate calendarDate) {
        hibernateCalendarDateDao.delete(calendarDate);
    }

    @Override
    public CalendarDate getCalendarDateById(int id) {
        return hibernateCalendarDateDao.getCalendarDateById(id);
    }

    @Override
    public List<CalendarDate> getCalendarDatesByInterval(TeamIntervalCalendar teamIntervalCalendar){
        return hibernateCalendarDateDao.getCalendarDatesByInterval(teamIntervalCalendar);
    }

    @Override
    public void updateDates(List<CalendarDate> calendarDates){
        for(CalendarDate date : calendarDates){
            hibernateCalendarDateDao.update(date);
        }
    }
}
