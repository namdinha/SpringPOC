package com.sap.poc.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class TeamIntervalCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String initDate;
    private String endDate;

    @OneToMany(mappedBy = "teamIntervalCalendar")
    private Set<CalendarDate> dates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<CalendarDate> getDates() {
        return dates;
    }

    public void setDates(Set<CalendarDate> dates) {
        this.dates = dates;
    }

    public void addDates(CalendarDate date) {
        this.dates.add(date);
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
