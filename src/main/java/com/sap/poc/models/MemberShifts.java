package com.sap.poc.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Entity
public class MemberShifts {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @DateTimeFormat
    private Map<Calendar, Integer> shifts = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Calendar, Integer> getShifts() {
        return shifts;
    }

    public void setShifts(Map<Calendar, Integer> shifts) {
        this.shifts = shifts;
    }
}
