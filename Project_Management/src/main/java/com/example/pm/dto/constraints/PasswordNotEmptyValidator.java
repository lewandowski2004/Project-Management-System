package com.example.pm.dto.constraints;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordNotEmptyValidator implements ConstraintValidator<PasswordNotEmptyConstraint, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(PasswordNotEmptyConstraint constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
        if (fieldValue.equals("") && fieldMatchValue.equals("")) {
            return false;
        } else {
            return true;
        }
    }
}
