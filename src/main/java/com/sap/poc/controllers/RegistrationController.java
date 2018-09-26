package com.sap.poc.controllers;

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
import java.util.List;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterPage() {
        return "registerForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(Model model, TeamOwner user) {
        Team team = new Team();

        user.setRoles("ROLE_OWNER");

        team.setOwner(user);
        user.addTeam(team);

        

        userService.create(user);

        teamService.create(team);

        model.addAttribute("user", userService.getUserByName(user.getName()));
        model.addAttribute("team", teamService.getTeamByOwner(user.getName()));

        return "registerForm";
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String getRegisterMemberPage() {
        return "registerMemberForm";
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String registerMember(Model model, TeamMember user) {
        userService.create(user);
        model.addAttribute("user", userService.getUserByName(user.getName()));
        return "registerForm";
    }
}
