package com.sap.poc.validation;

import com.sap.poc.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");

        User user = (User) target;
        if(user.getPassword().length() <= 0) errors.rejectValue("password", "field.required");
    }
}
