package com.sap.poc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class CalendarDate implements Comparable<CalendarDate> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar date = Calendar.getInstance();

    private boolean isHolidayOrWeekend = false;

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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDate(String date) {
        try {
            this.date.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String HolidayOrWeekendToString() {
        if(isHolidayOrWeekend) return "Holiday";
        else return "Not Holiday";
    }

    public TeamIntervalCalendar getTeamIntervalCalendar() {
        return teamIntervalCalendar;
    }

    public void setTeamIntervalCalendar(TeamIntervalCalendar teamIntervalCalendar) {
        this.teamIntervalCalendar = teamIntervalCalendar;
    }

    @Override
    public String toString(){
        return formatOut.format(this.date.getTime());
    }

    @Override
    public int compareTo(CalendarDate o) {
        return this.getDate().compareTo(o.getDate());
    }
}
