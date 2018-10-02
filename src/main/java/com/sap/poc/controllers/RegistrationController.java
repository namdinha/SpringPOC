package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;
import java.security.Principal;
import java.util.*;

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

        List<Team> teams = new ArrayList<>(user.getTeams());

        model.addAttribute("members", teams.get(0).getMembers());

        return "homepage";
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String registerMember(Model model, TeamMember member, HttpServletRequest request) {
        member.addRole(new Role("MEMBER"));

        TeamOwner owner = (TeamOwner) getLoggedUser(request);

        Team team = teamService.getTeamByOwner(owner.getUsername());
        team.setMembers(userService.getTeamMembers(team.getId()));
        team.addMember(member);
        team.setOwner(owner);
        member.setTeam(team);

        userService.create(member);

        List<TeamMember> members = new ArrayList<>(team.getMembers());

        model.addAttribute("members", members);

        return "homepage";
    }

    public User getLoggedUser(HttpServletRequest request){
        return userService.getUserByLogin(request.getUserPrincipal().getName());
    }
}
