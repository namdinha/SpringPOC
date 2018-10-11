package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController extends GenericController{

    @Resource
    private TeamIntervalCalendarService teamIntervalCalendarService;
    @Resource
    private TeamService teamService;
    @Resource
    private UserService userService;
    @Resource
    private TeamMemberShiftService teamMemberShiftService;
    @Resource
    private CalendarDateService calendarDateService;

    @RequestMapping(value = "/editShift", method = RequestMethod.POST)
    public ModelAndView editShift(TeamMemberShift editedShift){
        ModelAndView modelAndView = new ModelAndView("redirect:/memberHome");

        TeamMemberShift shift = teamMemberShiftService.getTeamMemberShiftById(editedShift.getId());
        shift.setShift(editedShift.getShift());

        teamMemberShiftService.update(shift);

        return modelAndView;
    }

    @RequestMapping(value = "/editHoliday", method = RequestMethod.POST)
    public ModelAndView editHoliday(CalendarDate editedCalendarDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/ownerHome");

        CalendarDate date = calendarDateService.getCalendarDateById(editedCalendarDate.getId());
        date.setHolidayOrWeekend(!date.isHolidayOrWeekend());

        calendarDateService.update(date);

        return modelAndView;
    }

    @RequestMapping(value = "/addInterval", method = RequestMethod.POST)
    public ModelAndView addInterval(Principal principal, TeamIntervalCalendar newInterval){
        ModelAndView modelAndView = new ModelAndView("redirect:/ownerHome");

        TeamOwner owner = (TeamOwner) getLoggedUser(principal);
        Team team = teamService.getTeamByOwner(owner.getUsername());
        Set<TeamMember> members = team.getMembers();
        Set<TeamIntervalCalendar> teamIntervalCalendars = new HashSet<>(teamIntervalCalendarService.getTeamIntervalsCalendarByTeam(team));

        team.setMembers(members);
        team.setIntervalCalendars(teamIntervalCalendars);
        team.addIntervalCalendar(newInterval);
        newInterval.setTeam(team);

        teamIntervalCalendarService.create(newInterval);

        try {
            newInterval.setDates();
        } catch (Exception e) {
            e.printStackTrace();
        }

        teamIntervalCalendarService.update(newInterval);
        teamService.update(team);

        userService.setShiftsToMembers(members, newInterval.getDates());
        teamMemberShiftService.createShiftsOfMembers(members);
        userService.updateTeamMembers(members);

        return modelAndView;
    }
}
