package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String editShift(Model model, Principal principal, TeamMemberShift editedShift){

        TeamMember loggedMember = (TeamMember) getLoggedUser(principal);

        TeamMemberShift shift = teamMemberShiftService.getTeamMemberShiftById(editedShift.getId());
        shift.setShift(editedShift.getShift());

        teamMemberShiftService.update(shift);

        List<TeamMemberShift> shifts = teamMemberShiftService.getTeamMemberShiftsByMember(loggedMember);

        model.addAttribute("shifts", shifts);

        return "memberHome";
    }

    @RequestMapping(value = "/editHoliday", method = RequestMethod.POST)
    public String editHoliday(Model model, Principal principal, CalendarDate editedCalendarDate) {
        TeamOwner loggedOwner = (TeamOwner) getLoggedUser(principal);

        CalendarDate date = calendarDateService.getCalendarDateById(editedCalendarDate.getId());
        date.setHolidayOrWeekend(!date.isHolidayOrWeekend());

        calendarDateService.update(date);

        Team team = teamService.getTeamByOwner(loggedOwner.getUsername());
        model.addAttribute("members", getMembersList(principal));
        model.addAttribute("intervals", teamIntervalCalendarService.getDateListsOfIntervals(team));

        return "ownerHome";
    }

    @RequestMapping(value = "/addInterval", method = RequestMethod.POST)
    public String addInterval(Model model, Principal principal, TeamIntervalCalendar newInterval){

        TeamOwner owner = (TeamOwner) getLoggedUser(principal);

        Team team = teamService.getTeamByOwner(owner.getUsername());
        Set<TeamMember> members = team.getMembers();//new HashSet<>(userService.getMembersByTeamId(team.getId()));
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

        model.addAttribute("members", getMembersList(principal));
        model.addAttribute("intervals", teamIntervalCalendarService.getDateListsOfIntervals(team));

        return "ownerHome";
    }
}
