package com.sap.poc.models;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Calendar;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "calendarDate_id")
    private CalendarDate calendarDate;

    private Shift shift;

    @Transient
    private int dateId;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CalendarDate getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(CalendarDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    public void setCalendarDate(String date) {
        this.calendarDate = new CalendarDate();
        Calendar newDate = Calendar.getInstance();
        try {
            newDate.setTime(CalendarDate.formatOut.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.calendarDate.setDate(newDate);
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int date) {
        this.dateId = date;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
