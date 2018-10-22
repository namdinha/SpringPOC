package com.sap.poc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class CalendarDate implements Comparable<CalendarDate> {

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar date = Calendar.getInstance();

    private boolean isHolidayOrWeekend = false;

    @ManyToOne
    @JoinColumn(name = "teamIntervalCalendar_id")
    private TeamIntervalCalendar teamIntervalCalendar;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "capacity", joinColumns = @JoinColumn(name = "CalendarDate_id"))
    private Map<Shift, Integer> capacity = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usedCapacity", joinColumns = @JoinColumn(name = "CalendarDate_id"))
    private Map<Shift, Integer> usedCapacity = new HashMap<>();

    @OneToMany(mappedBy = "date")
    private Set<TeamMemberShift> membersShifts = new HashSet<>();

    public CalendarDate() {
        for(Shift shift : Shift.getValidShifts()) {
            capacity.put(shift, 0);
            usedCapacity.put(shift, 0);
        }
    }

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
        if(this.date.get(Calendar.DAY_OF_WEEK) == 1 || this.date.get(Calendar.DAY_OF_WEEK) == 7)
            isHolidayOrWeekend = true;
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

    public Map<Shift, Integer> getCapacity() {
        return capacity;
    }

    public void setCapacity(Map<Shift, Integer> capacity) {
        this.capacity.putAll(capacity);
    }

    public void addCapacity(Shift shift, Integer capacity){
        this.capacity.put(shift, capacity);
    }

    public Set<TeamMemberShift> getMembersShifts() {
        return membersShifts;
    }

    public void setMembersShifts(Set<TeamMemberShift> membersShifts) {
        this.membersShifts = membersShifts;
    }

    public Map<Shift, Integer> getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(Map<Shift, Integer> usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public void addMemberShift(TeamMemberShift memberShift){
        this.membersShifts.add(memberShift);
    }

    public void addUsedCapacity(Shift shift) {
        this.usedCapacity.replace(shift, this.usedCapacity.get(shift) + 1);
    }

    public void setUsedCapacityToZero() {
        for(Shift shift : Shift.getValidShifts()) {
            this.usedCapacity.put(shift, 0);
        }

    }

    @Override
    public String toString(){
        return formatOut.format(this.date.getTime());
    }

    @Override
    public int compareTo(CalendarDate o) {
        return this.getDate().compareTo(o.getDate());
    }

    @Override
    public boolean equals(Object o) {
        CalendarDate calendarDate = (CalendarDate) o;
        return calendarDate.getDate().equals(this.date);
    }
}
