package com.sap.poc.validation;

import com.sap.poc.models.TeamIntervalCalendar;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class TeamIntervalCalendarValidation implements Validator {
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
    }
}
