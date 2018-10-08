package com.sap.poc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class TeamIntervalCalendar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date initDate;
    private Date endDate;

    @DateTimeFormat()
    @ElementCollection
    @CollectionTable(name = "Holidays", joinColumns = @JoinColumn(name = "TeamIntervalCalendar_id"))
    private Set<Date> holidays = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Date> getHolidays() {
        return holidays;
    }

    public void setHolidays(Set<Date> holidays) {
        this.holidays = holidays;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setInitDate(String initDate) {
        try {
            this.initDate = format.parse(initDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setEndDate(String endDate) {
        List<String> date = Arrays.asList(endDate.split("-"));
        try {
            this.endDate = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
