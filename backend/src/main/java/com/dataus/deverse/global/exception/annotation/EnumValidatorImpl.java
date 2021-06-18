package com.dataus.deverse.global.exception.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value != null) {
            if(!value.toString().toUpperCase().equals("UNKNOWN")) {
                return true;
            }
        }

        return false;
    }   
    
}
