package com.sap.poc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class CalendarDate implements Comparable<CalendarDate> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date = new Date();

    private boolean isHolidayOrWeekend;

    @ManyToOne
    @JoinColumn(name = "teamIntervalCalendar_id")
    private TeamIntervalCalendar teamIntervalCalendar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHolidayOrWeekend() {
        return isHolidayOrWeekend;
    }

    public void setHolidayOrWeekend(boolean holidayOrWeekend) {
        isHolidayOrWeekend = holidayOrWeekend;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TeamIntervalCalendar getTeamIntervalCalendar() {
        return teamIntervalCalendar;
    }

    public void setTeamIntervalCalendar(TeamIntervalCalendar teamIntervalCalendar) {
        this.teamIntervalCalendar = teamIntervalCalendar;
    }

    @Override
    public String toString(){
        return this.date.toString();
    }

    @Override
    public int compareTo(CalendarDate o) {
        return this.getDate().compareTo(o.getDate());
    }
}
