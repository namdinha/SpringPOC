package com.sap.poc.controllers;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.services.RoleService;
import com.sap.poc.services.TeamService;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController extends GenericController{

    @Resource
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add("OWNER");
        roleNames.add("MEMBER");
        roleService.createRolesIfNotCreated(roleNames);

        return "homepage";
    }

    @RequestMapping(value="/ownerHome", method = RequestMethod.GET)
    public String getOwnerHome(Model model, HttpServletRequest request) {

        model.addAttribute("members", getMembersList(request));

        return "ownerHome";
    }

    @RequestMapping(value="/memberHome", method = RequestMethod.GET)
    public String getMemberHome(Model model, HttpServletRequest request) {

        return "memberHome";
    }
}
