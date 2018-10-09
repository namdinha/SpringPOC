package com.sap.poc.services;

import com.sap.poc.models.CalendarDate;

public interface CalendarDateService {

    void create(CalendarDate calendarDate);
    void refresh(CalendarDate calendarDate);
    void update(CalendarDate calendarDate);
    void delete(CalendarDate calendarDate);
    CalendarDate getCalendarDateById(int id);
}
