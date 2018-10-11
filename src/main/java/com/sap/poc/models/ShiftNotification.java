package com.sap.poc.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Shift")
public class ShiftNotification extends Notification {

    @ManyToOne
    @JoinColumn(name = "calendarDate_id")
    private CalendarDate calendarDate;

    public CalendarDate getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(CalendarDate calendarDate) {
        this.calendarDate = calendarDate;
    }
}
