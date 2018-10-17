package com.sap.poc.validation;

import com.sap.poc.models.Notification;
import com.sap.poc.models.ShiftNotification;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class NotificationValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Notification.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "field.required");

        if(target.getClass().equals(ShiftNotification.class)) {
            ShiftNotification notification = (ShiftNotification) target;
            ValidationUtils.invokeValidator(new CalendarDateValidation(), notification.getCalendarDate(), errors);
            if(notification.getMessage().length() <= 0) {
                errors.rejectValue("message", "field.required");
            }
        }
        else {
            Notification notification = (Notification) target;
            if(notification.getMessage().length() <= 0) {
                errors.rejectValue("message", "field.required");
            }
        }
    }
}
