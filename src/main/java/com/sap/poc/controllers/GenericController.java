package com.sap.poc.controllers;

import com.sap.poc.models.User;
import com.sap.poc.services.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public abstract class GenericController {

    @Resource
    private UserService userService;

    protected User getLoggedUser(HttpServletRequest request){
        return userService.getUserByLogin(request.getUserPrincipal().getName());
    }
}
