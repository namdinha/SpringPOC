package com.sap.poc.controllers;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.models.User;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public abstract class GenericController {

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    protected User getLoggedUser(HttpServletRequest request){
        return userService.getUserByLogin(request.getUserPrincipal().getName());
    }

    protected List<TeamMember> getMembersList(HttpServletRequest request){
        TeamOwner owner = (TeamOwner) getLoggedUser(request);
        Team team = teamService.getTeamByOwner(owner.getUsername());

        return new ArrayList<>(userService.getTeamMembers(team.getId()));
    }
}
