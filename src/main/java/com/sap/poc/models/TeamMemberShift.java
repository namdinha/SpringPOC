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

    private Shift shift = Shift.ANY;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private TeamMember member;

    public TeamMemberShift(CalendarDate date, Shift shift, TeamMember member) {
        this.date = date;
        this.shift = shift;
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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public TeamMember getMember() {
        return member;
    }

    public void setMember(TeamMember member) {
        this.member = member;
    }

    @Override
    public int compareTo(TeamMemberShift o) {
        return this.getDate().compareTo(o.getDate());
    }
}
