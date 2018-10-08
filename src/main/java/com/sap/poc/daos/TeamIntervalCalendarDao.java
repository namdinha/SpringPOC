package com.sap.poc.daos;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;

import java.util.List;

public interface TeamIntervalCalendarDao {

    void create(TeamIntervalCalendar teamIntervalCalendar);
    void refresh(TeamIntervalCalendar teamIntervalCalendar);
    void update(TeamIntervalCalendar teamIntervalCalendar);
    void delete(TeamIntervalCalendar teamIntervalCalendar);
    TeamIntervalCalendar getTeamIntervalCalendarById(int id);
    List<TeamIntervalCalendar> getTeamIntervalsCalendarByTeam(Team team);
}
