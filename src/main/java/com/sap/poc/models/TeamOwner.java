package com.sap.poc.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Owner")
public class TeamOwner extends User {

    @OneToMany(mappedBy = "owner")
    private Set<Team> teams = new HashSet<>();

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Team> addTeam(Team team) {
        teams.add(team);
        return teams;
    }

}
