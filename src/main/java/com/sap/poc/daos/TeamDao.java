package com.sap.poc.daos;

import com.sap.poc.models.Team;

import java.util.List;

public interface TeamDao {

    void create(Team team);
    void refresh(Team team);
    void update(Team team);
    void delete(Team team);
    List<Team> getTeams();
    Team getTeamByOwner(String owner);

}