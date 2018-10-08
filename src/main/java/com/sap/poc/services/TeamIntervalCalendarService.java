package com.sap.poc.services;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;

import java.util.Date;
import java.util.List;

public interface TeamIntervalCalendarService {

    void create(TeamIntervalCalendar teamIntervalCalendar);
    void refresh(TeamIntervalCalendar teamIntervalCalendar);
    void update(TeamIntervalCalendar teamIntervalCalendar);
    void delete(TeamIntervalCalendar teamIntervalCalendar);
    TeamIntervalCalendar getTeamIntervalCalendarById(int id);
    int getIntervalSize(TeamIntervalCalendar teamIntervalCalendar);
    List<Date> getDateListOfInterval(TeamIntervalCalendar teamIntervalCalendar);
    List<TeamIntervalCalendar> getTeamIntervalCalendarByTeamId(int teamId);
    List<TeamIntervalCalendar> getTeamIntervalsCalendarByTeam(Team team);

    List<List<Date>> getDateListsOfIntervals(Team team);
}
