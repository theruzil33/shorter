package com.theruzil.shorter.validator.url;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UrlValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidUrl {
    String message() default "This isn't correct url";

    Class[] groups() default {};

    Class[] payload() default {};
}
