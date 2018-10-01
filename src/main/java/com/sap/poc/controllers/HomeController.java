package com.sap.poc.controllers;

import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.models.User;
import com.sap.poc.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Resource
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(Model model) {
        List<String> roleNames = new ArrayList<>();
        roleNames.add("OWNER");
        roleNames.add("MEMBER");
        roleService.createRolesIfNotCreated(roleNames);

        return "registerForm";
    }

    @RequestMapping(value = "/ownerHome", method = RequestMethod.GET)
    public String getOwnerPage(Model model, TeamOwner user){
        return "ownerHome";
    }

    @RequestMapping(value = "/memberHome", method = RequestMethod.GET)
    public String getMemberPage(Model model, TeamMember user){
        return "memberHome";
    }



}
