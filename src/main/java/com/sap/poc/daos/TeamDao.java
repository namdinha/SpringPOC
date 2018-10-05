package com.sap.poc.daos;

import com.sap.poc.models.Team;
import com.sap.poc.models.TeamOwner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamDao {

    void create(Team team);
    void refresh(Team team);
    void update(Team team);
    void delete(Team team);
    List<Team> getTeams();
    Team getTeamById(int teamId);
    Team getTeamByOwner(TeamOwner owner);
}