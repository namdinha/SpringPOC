package com.sap.poc.validation;

import com.sap.poc.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "username", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "field.required");
    }
}
