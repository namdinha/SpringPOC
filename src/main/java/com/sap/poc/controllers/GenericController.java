package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public abstract class GenericController {

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    protected User getLoggedUser(Principal principal){
        return userService.getUserByLogin(principal.getName());
    }

    protected void updateLoggedUser(Principal principal){
        User user = getLoggedUser(principal);
        String username = user.getUsername();
        String password = user.getPassword();
    }

    protected List<TeamMember> getMembersList(Principal principal){
        TeamOwner owner;
        try{
            owner = (TeamOwner) getLoggedUser(principal);
        }
        catch(NullPointerException e){
            return new ArrayList<>();
        }
        Team team = teamService.getTeamByOwner(owner.getUsername());

        return new ArrayList<>(userService.getMembersByTeamId(team.getId()));
    }

    protected String getUserType(Principal principal) {
        List<Role> roles = new ArrayList<>(userService.getUserByLogin(principal.getName()).getRoles());
        return roles.get(0).getRoleName();
    }
}
