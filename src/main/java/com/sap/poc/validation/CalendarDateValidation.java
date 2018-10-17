package com.sap.poc.validation;

import com.sap.poc.models.CalendarDate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CalendarDateValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CalendarDate.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "field.required");
    }
}
