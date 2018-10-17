package com.sap.poc.validation;

import com.sap.poc.models.TeamMemberShift;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class TeamMemberShiftValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TeamMemberShift.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.invokeValidator(new CalendarDateValidation(), target, errors);
    }
}
