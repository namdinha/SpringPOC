package com.sap.poc.services;

import com.sap.poc.models.Team;

import java.util.List;

public interface TeamService {

    void create(Team team);
    void refresh(Team team);
    void update(Team team);
    void delete(Team team);
    List<Team> getTeams();
    Team getTeamByOwner(String owner);
    Team getTeamById(int teamId);

}
