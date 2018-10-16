package com.sap.poc.controllers;

import com.sap.poc.logic.Allocator;
import com.sap.poc.logic.impl.GreedyAllocator;
import com.sap.poc.models.*;
import com.sap.poc.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.*;

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

    @RequestMapping(value = "/allocate", method = RequestMethod.GET)
    public ModelAndView allocate(Principal principal, Integer teamId){
        ModelAndView modelAndView;
        if(getUserType(principal).equals("OWNER"))
            modelAndView = new ModelAndView("redirect:/ownerHome");
        else
            modelAndView = new ModelAndView("redirect:/memberHome");

        List<TeamMember> members = userService.getMembersByTeamId(teamId);

        Allocator allocator = new GreedyAllocator(calendarDateService, teamMemberShiftService);
        allocator.allocate(members);

        userService.updateTeamMembers(new HashSet<>(members));

        return modelAndView;
    }

    @RequestMapping(value = "/editShift", method = RequestMethod.POST)
    public ModelAndView editShift(TeamMemberShift editedShift){
        ModelAndView modelAndView = new ModelAndView("redirect:/calendar/allocate");

        TeamMemberShift shift = teamMemberShiftService.getTeamMemberShiftById(editedShift.getId());
        shift.setDesiredShift(editedShift.getDesiredShift());

        teamMemberShiftService.update(shift);

        modelAndView.addObject("teamId", shift.getDate().getTeamIntervalCalendar().getTeam().getId());

        return modelAndView;
    }

    @RequestMapping(value = "/editHoliday", method = RequestMethod.POST)
    public ModelAndView editHoliday(CalendarDate editedCalendarDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/calendar/allocate");

        CalendarDate date = calendarDateService.getCalendarDateById(editedCalendarDate.getId());

        calendarDateService.changeHolidayOrWeekend(date);

        modelAndView.addObject("teamId", date.getTeamIntervalCalendar().getTeam().getId());

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
        calendarDateService.updateCapacityOfDates(new ArrayList<>(newInterval.getDates()), new HashMap<>(newInterval.getDefaultCapacity()));
        teamService.update(team);

        userService.setShiftsToMembers(members, newInterval.getDates());
        teamMemberShiftService.createShiftsOfMembers(members);
        userService.updateTeamMembers(members);

        return modelAndView;
    }

    @RequestMapping(value = "/editDateCapacity", method = RequestMethod.POST)
    public ModelAndView editDateCapacity(Principal principal, CalendarDate editedCalendarDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/calendar/allocate");

        CalendarDate date = calendarDateService.getCalendarDateById(editedCalendarDate.getId());
        date.setCapacity(editedCalendarDate.getCapacity());

        calendarDateService.update(date);

        return modelAndView;
    }
}
