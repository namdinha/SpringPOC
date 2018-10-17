package com.sap.poc.controllers;

import com.sap.poc.models.*;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import com.sap.poc.validation.UserValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController extends GenericController{

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValidation());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registerOwner(@Valid TeamOwner user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        if(result.hasErrors()) return modelAndView;

        Team team = new Team();

        user.addRole(new Role("OWNER"));

        team.setOwner(user);
        user.addTeam(team);

        userService.create(user);
        teamService.create(team);

        return modelAndView;
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public ModelAndView registerMember(@Valid TeamMember member, BindingResult result, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/ownerHome");

        if(result.hasErrors()) return modelAndView;

        member.addRole(new Role("MEMBER"));

        TeamOwner owner = (TeamOwner) getLoggedUser(principal);

        Team team = teamService.getTeamByOwner(owner.getUsername());
        member.setTeam(team);

        userService.create(member);
        teamService.update(team);

        return modelAndView;
    }
}
