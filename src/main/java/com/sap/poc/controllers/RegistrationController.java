package com.sap.poc.controllers;

import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.models.User;
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

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterPage() {
        return "registerForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(Model model, TeamOwner user) {
        userService.create(user);
        model.addAttribute("user", userService.getUserByName(user.getName()));
        return "homepage";
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String getRegisterMemberPage() {
        return "registerMemberForm";
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String registerMember(Model model, TeamMember user) {
        userService.create(user);
        model.addAttribute("user", userService.getUserByName(user.getName()));
        return "homepage";
    }
}
