package com.sap.poc.controllers;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.models.User;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
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

    protected void updateLoggedUser(HttpServletRequest request){
        User user = getLoggedUser(request);
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected List<TeamMember> getMembersList(HttpServletRequest request){
        TeamOwner owner;
        try{
            owner = (TeamOwner) getLoggedUser(request);
        }
        catch(NullPointerException e){
            return new ArrayList<>();
        }
        Team team = teamService.getTeamByOwner(owner.getUsername());

        return new ArrayList<>(userService.getMembersByTeamId(team.getId()));
    }
}
