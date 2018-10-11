package com.sap.poc.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class TeamIntervalCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String initDate = "NotSet";
    private String endDate = "NotSet";

    @OneToMany(mappedBy = "teamIntervalCalendar")
    private Set<CalendarDate> dates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ElementCollection
    @CollectionTable(name = "DEFAULTCAPACITIES", joinColumns = @JoinColumn(name = "teamIntervalCalendar_id"))
    private Map<Shift, Integer> defaultCapacity = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "DEFAULTCAPACITIESONHOLIDAYS", joinColumns = @JoinColumn(name = "teamIntervalCalendar_id"))
    private Map<Shift, Integer> defaultCapacityOnHolidays = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "SHIFTSALLOCATION", joinColumns = @JoinColumn(name = "teamIntervalCalendar_id"))
    @MapKeyJoinColumn(name = "teamMemberShift_id")
    private Map<TeamMemberShift, Shift> shiftsAllocation = new HashMap<>();

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

    public void setDates() throws Exception {
        if(initDate == "NotSet" || endDate == "NotSet"){
            throw new Exception("InitDate or EndDate not set.");
        }
        else{
            Set<CalendarDate> dates = new HashSet<>();

            CalendarDate endDate = new CalendarDate();
            endDate.setDate(this.endDate);
            endDate.setTeamIntervalCalendar(this);

            CalendarDate initDate = new CalendarDate();
            initDate.setDate(this.initDate);
            initDate.setTeamIntervalCalendar(this);
            dates.add(initDate);

            Calendar dateIterator = (Calendar) initDate.getDate().clone();
            dateIterator.add(Calendar.DATE, 1);

            CalendarDate date;

            while(dateIterator.before(endDate.getDate())) {
                date = new CalendarDate();
                date.setDate((Calendar) dateIterator.clone());
                date.setTeamIntervalCalendar(this);
                dates.add(date);

                dateIterator.add(Calendar.DATE, 1);
            }

            dates.add(endDate);

            this.dates = dates;
        }
    }
}
