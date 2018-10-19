package com.sap.poc.services;

import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.Shift;
import com.sap.poc.models.TeamIntervalCalendar;

import java.util.HashMap;
import java.util.List;

public interface CalendarDateService {

    void create(CalendarDate calendarDate);
    void refresh(CalendarDate calendarDate);
    void update(CalendarDate calendarDate);
    void delete(CalendarDate calendarDate);
    CalendarDate getCalendarDateById(int id);
    List<CalendarDate> getCalendarDatesByInterval(TeamIntervalCalendar teamIntervalCalendar);
    void updateDates(List<CalendarDate> calendarDates);
    void changeHolidayOrWeekend(CalendarDate calendarDate);
    void updateCapacityOfDates(List<CalendarDate> calendarDates, HashMap<Shift, Integer> capacity);
    List<CalendarDate> getAllCalendarDates();
}
