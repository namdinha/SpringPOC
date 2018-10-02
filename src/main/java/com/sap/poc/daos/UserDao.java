package com.sap.poc.daos;

import com.sap.poc.models.TeamMember;
import com.sap.poc.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface UserDao {

    void create(User user);
    void refresh(User user);
    void update(User user);
    void delete(User user);
    User getUserByName(String name);
    User getUserByLogin(String login);
    List<User> getUsers();
    Set<TeamMember> getTeamMembers(int teamId);

}
