package com.theruzil.shorter.validator.url;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlValidatorTest {
    UrlValidator urlValidator;
    ConstraintValidatorContext constraintValidatorContext;

    public UrlValidatorTest() {
        this.urlValidator = new UrlValidator();
        this.constraintValidatorContext = new CustomConstraintValidatorContext();
    }

    @Test
    void isValid() {
        boolean result = urlValidator.isValid("test", constraintValidatorContext);
        assertFalse(result);
    }

    @Test
    void isNotValid() {
        boolean result = urlValidator.isValid("http://localhost", constraintValidatorContext);
        assertTrue(result);
    }

    static class CustomConstraintValidatorContext implements ConstraintValidatorContext {
        @Override
        public void disableDefaultConstraintViolation() {

        }

        @Override
        public String getDefaultConstraintMessageTemplate() {
            return null;
        }

        @Override
        public ClockProvider getClockProvider() {
            return null;
        }

        @Override
        public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String s) {
            return new CustomConstraintViolationBuilder();
        }

        @Override
        public <T> T unwrap(Class<T> aClass) {
            return null;
        }
    }

    static class CustomConstraintViolationBuilder implements ConstraintValidatorContext.ConstraintViolationBuilder {
        @Override
        public ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext addNode(String s) {
            return null;
        }

        @Override
        public ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext addPropertyNode(String s) {
            return null;
        }

        @Override
        public ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext addBeanNode() {
            return null;
        }

        @Override
        public ConstraintValidatorContext.ConstraintViolationBuilder.ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String s, Class<?> aClass, Integer integer) {
            return null;
        }

        @Override
        public ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext addParameterNode(int i) {
            return null;
        }

        @Override
        public ConstraintValidatorContext addConstraintViolation() {
            return null;
        }
    }
}