package com.sap.poc.validation;

import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.TeamIntervalCalendar;
import com.sap.poc.services.CalendarDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TeamIntervalCalendarValidation implements Validator {

    @Resource
    @Autowired
    private CalendarDateService calendarDateService;

    @Override
    public boolean supports(Class<?> clazz) {
        return TeamIntervalCalendar.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "initDate", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "defaultCapacity", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "defaultCapacityOnHolidays", "field.required");

        TeamIntervalCalendar teamIntervalCalendar = (TeamIntervalCalendar) target;
        try {
            teamIntervalCalendar.setDates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CalendarDate> dates = calendarDateService.getAllCalendarDates();
        for(CalendarDate date : teamIntervalCalendar.getDates()) {
            if(dates.contains(date)) errors.rejectValue("initDate", "field.required");
            if(dates.contains(date)) errors.rejectValue("endDate", "field.required");
        }
    }
}
