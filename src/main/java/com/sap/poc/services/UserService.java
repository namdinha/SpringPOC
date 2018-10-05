package com.sap.poc.services;

import com.sap.poc.models.TeamMember;
import com.sap.poc.models.User;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface UserService {

    void create(User user);
    void refresh(User user);
    void update(User user);
    void delete(User user);
    User getUserByName(String name);
    User getUserByLogin(String login);
    List<User> getUsers();
    List<TeamMember> getMembersByTeamId(int teamId);
}
