package com.sap.poc.services;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;

import java.util.Calendar;
import java.util.List;

public interface TeamIntervalCalendarService {

    void create(TeamIntervalCalendar teamIntervalCalendar);
    void refresh(TeamIntervalCalendar teamIntervalCalendar);
    void update(TeamIntervalCalendar teamIntervalCalendar);
    void delete(TeamIntervalCalendar teamIntervalCalendar);
    TeamIntervalCalendar getTeamIntervalCalendarById(int id);
    int getIntervalSize(TeamIntervalCalendar teamIntervalCalendar);
    List<Calendar> getDateListOfInterval(TeamIntervalCalendar teamIntervalCalendar);
    List<TeamIntervalCalendar> getTeamIntervalCalendarByTeamId(int teamId);
    List<TeamIntervalCalendar> getTeamIntervalCalendarByTeam(Team team);
}
