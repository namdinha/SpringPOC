package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController extends GenericController{

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registerOwner(TeamOwner user) {
        ModelAndView modelAndView = new ModelAndView("homepage");

        Team team = new Team();

        user.addRole(new Role("OWNER"));

        team.setOwner(user);
        user.addTeam(team);

        userService.create(user);
        teamService.create(team);

        return modelAndView;
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public ModelAndView registerMember(TeamMember member, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("ownerHome");

        member.addRole(new Role("MEMBER"));

        TeamOwner owner = (TeamOwner) getLoggedUser(principal);

        Team team = teamService.getTeamByOwner(owner.getUsername());
        member.setTeam(team);

        userService.create(member);
        teamService.update(team);

        modelAndView.addObject("members", getMembersList(principal));

        return modelAndView;
    }
}
