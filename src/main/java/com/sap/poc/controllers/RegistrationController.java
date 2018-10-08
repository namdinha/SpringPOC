package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String registerOwner(Model model, TeamOwner user, Principal principal) {
        Team team = new Team();

        user.addRole(new Role("OWNER"));

        team.setOwner(user);
        user.addTeam(team);

        userService.create(user);
        teamService.create(team);

        model.addAttribute("members", getMembersList(principal));

        return "homepage";
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String registerMember(Model model, TeamMember member, Principal principal) {
        member.addRole(new Role("MEMBER"));

        TeamOwner owner = (TeamOwner) getLoggedUser(principal);

        Team team = teamService.getTeamByOwner(owner.getUsername());
        member.setTeam(team);

        userService.create(member);
        teamService.update(team);

        model.addAttribute("members", getMembersList(principal));

        return "ownerHome";
    }
}
