package com.sap.poc.controllers;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.services.RoleService;
import com.sap.poc.services.TeamIntervalCalendarService;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController extends GenericController{

    @Resource
    private RoleService roleService;
    @Resource
    private TeamIntervalCalendarService teamIntervalCalendarService;
    @Resource
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add("OWNER");
        roleNames.add("MEMBER");
        roleService.createRolesIfNotCreated(roleNames);

        return "homepage";
    }

    @RequestMapping(value="/ownerHome", method = RequestMethod.GET)
    public String getOwnerHome(Model model, HttpServletRequest request) {
        TeamOwner owner = (TeamOwner) getLoggedUser(request);
        Team team = teamService.getTeamByOwner(owner.getUsername());

        model.addAttribute("members", getMembersList(request));
        model.addAttribute("intervals", teamIntervalCalendarService.getDateListsOfIntervals(team));

        return "ownerHome";
    }

    @RequestMapping(value="/memberHome", method = RequestMethod.GET)
    public String getMemberHome(Model model, HttpServletRequest request) {
        TeamMember member = (TeamMember) getLoggedUser(request);

        model.addAttribute("intervals", teamIntervalCalendarService.getDateListsOfIntervals(member.getTeam()));

        return "memberHome";
    }

    @RequestMapping(value = "/interval/addInterval", method = RequestMethod.POST)
    public String addInterval(Model model, HttpServletRequest request, TeamIntervalCalendar newInterval){
        TeamOwner owner = (TeamOwner) getLoggedUser(request);
        Team team = teamService.getTeamByOwner(owner.getUsername());

        team.addIntervalCalendar(newInterval);
        newInterval.setTeam(team);

        teamIntervalCalendarService.create(newInterval);
        teamService.update(team);

        model.addAttribute("intervals", teamIntervalCalendarService.getDateListsOfIntervals(team));

        return "ownerHome";
    }
}
