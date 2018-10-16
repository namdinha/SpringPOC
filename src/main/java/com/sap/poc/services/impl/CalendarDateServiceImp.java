package com.sap.poc.services.impl;

import com.sap.poc.daos.CalendarDateDao;
import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.Shift;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.services.CalendarDateService;
import com.sap.poc.services.TeamMemberShiftService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarDateServiceImp implements CalendarDateService {

    @Resource
    private CalendarDateDao hibernateCalendarDateDao;
    @Resource
    private TeamMemberShiftService teamMemberShiftService;

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

    @Override
    public void changeHolidayOrWeekend(CalendarDate calendarDate) {
        calendarDate.setHolidayOrWeekend(!calendarDate.isHolidayOrWeekend());
        if(calendarDate.isHolidayOrWeekend())
            calendarDate.setCapacity(calendarDate.getTeamIntervalCalendar().getDefaultCapacityOnHolidays());
        else
            calendarDate.setCapacity(calendarDate.getTeamIntervalCalendar().getDefaultCapacity());
        teamMemberShiftService.changeAvailabilityByDate(calendarDate);
        calendarDate.setUsedCapacityToZero();

        this.update(calendarDate);
    }

    @Override
    public void updateCapacityOfDates(List<CalendarDate> calendarDates, HashMap<Shift, Integer> capacity) {
        for(CalendarDate calendarDate : calendarDates) {
            calendarDate.setCapacity(capacity);
            this.update(calendarDate);
        }
    }
}
