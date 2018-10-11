package com.sap.poc.controllers;

import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamOwner;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
@RequestMapping(value = "/edit")
public class EditController extends GenericController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView editOwner(Principal principal, TeamOwner owner){
        ModelAndView modelAndView = new ModelAndView("redirect:/ownerHome");

        TeamOwner editOwner = (TeamOwner) userService.getUserByLogin(owner.getUsername());

        editOwner.setPassword(owner.getPassword());
        editOwner.setName(owner.getName());
        editOwner.setEmail(owner.getEmail());

        userService.update(editOwner);

        updateLoggedUser(principal);

        return modelAndView;
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public ModelAndView editMember(TeamMember member){
        ModelAndView modelAndView = new ModelAndView("redirect:/ownerHome");

        TeamMember editMember = (TeamMember) userService.getUserByLogin(member.getUsername());

        editMember.setPassword(member.getPassword());
        editMember.setName(member.getName());
        editMember.setEmail(member.getEmail());

        userService.update(editMember);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getEditOwnerPage(TeamOwner editOwner){
        ModelAndView modelAndView = new ModelAndView("editOwner");

        editOwner = (TeamOwner) userService.getUserByLogin(editOwner.getUsername());

        modelAndView.addObject("editOwner", editOwner);

        return modelAndView;
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public ModelAndView getEditMemberPage(TeamMember editMember){
        ModelAndView modelAndView = new ModelAndView("editMember");

        editMember = (TeamMember) userService.getUserByLogin(editMember.getUsername());

        modelAndView.addObject("editMember", editMember);

        return modelAndView;
    }

}
