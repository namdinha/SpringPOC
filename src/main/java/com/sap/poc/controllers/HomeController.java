package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class HomeController extends GenericController{

    @Resource
    private RoleService roleService;
    @Resource
    private TeamIntervalCalendarService teamIntervalCalendarService;
    @Resource
    private TeamService teamService;
    @Resource
    private TeamMemberShiftService teamMemberShiftService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add("OWNER");
        roleNames.add("MEMBER");

        roleService.createRolesIfNotCreated(roleNames);

        return "homepage";
    }

    @RequestMapping(value="/ownerHome", method = RequestMethod.GET)
    public String getOwnerHome(Model model, Principal principal) {

        TeamOwner owner = (TeamOwner) getLoggedUser(principal);

        Team team = teamService.getTeamByOwner(owner.getUsername());

        model.addAttribute("members", getMembersList(principal));
        model.addAttribute("intervals", teamIntervalCalendarService.getDateListsOfIntervals(team));

        return "ownerHome";
    }

    @RequestMapping(value="/memberHome", method = RequestMethod.GET)
    public String getMemberHome(Model model, Principal principal) {

        TeamMember member = (TeamMember) getLoggedUser(principal);

        List<TeamMemberShift> shifts = teamMemberShiftService.getTeamMemberShiftsByMember(member);

        model.addAttribute("shifts", shifts);

        return "memberHome";
    }


}
