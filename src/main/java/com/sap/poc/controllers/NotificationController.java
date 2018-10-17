package com.sap.poc.controllers;

import com.sap.poc.models.Notification;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.services.NotificationService;
import com.sap.poc.services.TeamService;
import com.sap.poc.validation.NotificationValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;

@Controller
@RequestMapping("/notification")
public class NotificationController extends GenericController {

    @Resource
    private NotificationService notificationService;
    @Resource
    private TeamService teamService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new NotificationValidation());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createNotification(@Valid Notification notification, BindingResult result, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/ownerHome");
        TeamOwner owner = (TeamOwner) getLoggedUser(principal);
        Team team = teamService.getTeamByOwner(owner.getUsername());

        if(result.hasErrors()) return modelAndView;

        notification.setTeam(team);
        notificationService.create(notification);

        team.setNotifications(new HashSet<>(notificationService.getNotificationsByTeam(team)));
        team.addNotification(notification);
        teamService.update(team);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getNotifications(@Valid Notification notification, BindingResult result, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/ownerHome");

        if(result.hasErrors()) return modelAndView;

        return modelAndView;
    }

}
