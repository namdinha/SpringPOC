package com.sap.poc.models;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("Member")
public class TeamMember extends User{

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member")
    private Set<TeamMemberShift> shifts = new HashSet<>();

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<TeamMemberShift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<TeamMemberShift> shifts) {
        this.shifts = shifts;
    }
}
