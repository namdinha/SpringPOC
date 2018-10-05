package com.sap.poc.controllers;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.models.TeamMember;
import com.sap.poc.services.TeamIntervalCalendarService;
import com.sap.poc.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class CalendarRestController extends GenericController{

    @Resource
    private TeamIntervalCalendarService teamIntervalCalendarService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/getTeamCalendar", method = RequestMethod.GET)
    public List<TeamIntervalCalendar> getTeamCalendar(HttpServletRequest request){
        TeamMember loggedMember = (TeamMember) getLoggedUser(request);

        Team team = loggedMember.getTeam();

        return teamIntervalCalendarService.getTeamIntervalCalendarByTeam(team);
    }

    @RequestMapping(value = "/getMemberShifts", method = RequestMethod.GET)
    public Map<Calendar, Integer> getMemberShifts(HttpServletRequest request){
        TeamMember loggedMember = (TeamMember) getLoggedUser(request);

        return loggedMember.getShifts();
    }
}
