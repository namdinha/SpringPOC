package com.sap.poc.daos;

import com.sap.poc.models.TeamIntervalCalendar;

public interface TeamIntervalCalendarDao {

    void create(TeamIntervalCalendar teamIntervalCalendar);
    void refresh(TeamIntervalCalendar teamIntervalCalendar);
    void update(TeamIntervalCalendar teamIntervalCalendar);
    void delete(TeamIntervalCalendar teamIntervalCalendar);
    TeamIntervalCalendar getTeamIntervalCalendarById(int id);
}
