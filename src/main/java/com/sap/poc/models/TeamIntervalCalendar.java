package com.sap.poc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TeamIntervalCalendar {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @DateTimeFormat
    private Calendar initDate;

    @DateTimeFormat
    private Calendar endDate;

    @DateTimeFormat
    @ElementCollection
    @CollectionTable(name = "HOLIDAYS", joinColumns = @JoinColumn(name = "TeamIntervalCalendar_id"))
    private Set<Calendar> holidays = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getInitDate() {
        return initDate;
    }

    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Set<Calendar> getHolidays() {
        return holidays;
    }

    public void setHolidays(Set<Calendar> holidays) {
        this.holidays = holidays;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
