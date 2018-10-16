package com.sap.poc.models;

import javax.persistence.*;

@Entity
public class TeamMemberShift implements Comparable<TeamMemberShift> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private CalendarDate date;

    private Shift desiredShift = Shift.ANY;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private TeamMember member;

    private boolean isAvailable = true;

    private Shift allocatedShift;

    public TeamMemberShift(CalendarDate date, Shift desiredShift, TeamMember member) {
        this.date = date;
        this.desiredShift = desiredShift;
        this.member = member;
    }

    public TeamMemberShift() {
        this.date = null;
        this.member = null;
    }

    public TeamMemberShift(CalendarDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CalendarDate getDate() {
        return date;
    }

    public void setDate(CalendarDate date) {
        this.date = date;
    }

    public Shift getDesiredShift() {
        return desiredShift;
    }

    public void setDesiredShift(Shift desiredShift) {
        this.desiredShift = desiredShift;
    }

    public TeamMember getMember() {
        return member;
    }

    public void setMember(TeamMember member) {
        this.member = member;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Shift getAllocatedShift() {
        return allocatedShift;
    }

    public void setAllocatedShift(Shift allocatedShift) {
        this.allocatedShift = allocatedShift;
    }

    public String isAvailableToString() {
        if(isAvailable) return "Available";
        else return "Not Available";
    }

    @Override
    public int compareTo(TeamMemberShift o) {
        return this.getDate().compareTo(o.getDate());
    }
}
