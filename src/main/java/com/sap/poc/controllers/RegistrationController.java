package com.sap.poc.controllers;

import com.sap.poc.models.Role;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterPage() {
        return "homepage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerOwner(Model model, TeamOwner user) {
        Team team = new Team();

        user.addRole(new Role("OWNER"));

        team.setOwner(user);
        user.addTeam(team);

        userService.create(user);
        teamService.create(team);
        
        model.addAttribute("user", userService.getUserByName(user.getName()));
        model.addAttribute("members", user.getTeams().get(0).getMembers());

        return "homepage";
    }
}
