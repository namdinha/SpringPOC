package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
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
    @Resource
    private TeamMemberShiftService teamMemberShiftService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add("OWNER");
        roleNames.add("MEMBER");

        roleService.createRolesIfNotCreated(roleNames);

        return new ModelAndView("homepage");
    }

    @RequestMapping(value="/ownerHome", method = RequestMethod.GET)
    public ModelAndView getOwnerHome(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("ownerHome");

        TeamOwner owner = (TeamOwner) getLoggedUser(principal);

        Team team = teamService.getTeamByOwner(owner.getUsername());

        modelAndView.addObject("members", getMembersList(principal));
        modelAndView.addObject("intervals", teamIntervalCalendarService.getDateListsOfIntervals(team));

        return modelAndView;
    }

    @RequestMapping(value="/memberHome", method = RequestMethod.GET)
    public ModelAndView getMemberHome(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("memberHome");

        TeamMember member = (TeamMember) getLoggedUser(principal);

        modelAndView.addObject("shifts", teamMemberShiftService.getTeamMemberShiftsByMember(member));

        return modelAndView;
    }


}
