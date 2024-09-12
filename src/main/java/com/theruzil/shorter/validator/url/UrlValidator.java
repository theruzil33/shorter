package com.theruzil.shorter.validator.url;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

    @Override
    public void initialize(ValidUrl constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            (new URI(value)).toURL();
            return true;
        } catch (MalformedURLException | URISyntaxException | IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("This isn't correct url").addConstraintViolation();
            return false;
        }
    }
}
