package com.demo.app.application.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberValidatorTest {

    private final PhoneNumberValidator validator = new PhoneNumberValidator();

    @Test
    void shouldValidatePhoneNo() {
        assertTrue(validator.isValid("+372 58301404", null));
        assertTrue(validator.isValid("+37258301404", null));
        assertTrue(validator.isValid("58301404", null));
        assertTrue(validator.isValid("58301404213", null));
        assertFalse(validator.isValid("asd", null));
        assertFalse(validator.isValid("321 312312312", null));
        assertFalse(validator.isValid("++222 312312312", null));
        assertFalse(validator.isValid("+22  312312312", null));
    }

}