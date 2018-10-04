package com.sap.poc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("Member")
public class TeamMember extends User{

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @DateTimeFormat
    @ElementCollection
    @CollectionTable(name = "SHIFTS", joinColumns = @JoinColumn(name = "Member_id"))
    private Map<Calendar, Integer> shifts = new HashMap<>();

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Map<Calendar, Integer> getShifts() {
        return shifts;
    }

    public void setShifts(Map<Calendar, Integer> shifts) {
        this.shifts = shifts;
    }
}
