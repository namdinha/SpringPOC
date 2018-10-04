package com.sap.poc.services;

import com.sap.poc.models.TeamIntervalCalendar;

public interface TeamIntervalCalendarService {

    void create(TeamIntervalCalendar teamIntervalCalendar);
    void refresh(TeamIntervalCalendar teamIntervalCalendar);
    void update(TeamIntervalCalendar teamIntervalCalendar);
    void delete(TeamIntervalCalendar teamIntervalCalendar);
    TeamIntervalCalendar getTeamIntervalCalendarById(int id);
}
