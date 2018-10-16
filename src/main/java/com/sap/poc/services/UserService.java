package com.sap.poc.services;

import com.sap.poc.models.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    void create(User user);
    void refresh(User user);
    void update(User user);
    void delete(User user);
    User getUserByName(String name);
    User getUserByLogin(String login);
    List<User> getUsers();
    List<TeamMember> getMembersByTeamId(int teamId);
    void setShiftsToMembers(Set<TeamMember> members, Set<CalendarDate> dates);
    void setShiftsToMember(TeamMember member, Set<CalendarDate> dates);
    void updateTeamMembers(Set<TeamMember> members);
}
