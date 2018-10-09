package com.sap.poc.daos;

import com.sap.poc.models.CalendarDate;

public interface CalendarDateDao {

    void create(CalendarDate calendarDate);
    void refresh(CalendarDate calendarDate);
    void update(CalendarDate calendarDate);
    void delete(CalendarDate calendarDate);
    CalendarDate getCalendarDateById(int id);
}
