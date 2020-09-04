package com.github.hsoj48.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InstantFieldValidatorTest {

    ValidationHandler sut_ValidationHandler;

    @Before
    public void init() {
        sut_ValidationHandler = new DefaultValidationHandler();
    }

    @Test
    public void testDate_BeforeMethods_HappyPath() {
        sut_ValidationHandler.requireThat("testDate", Instant.ofEpochMilli(0))
                .isBeforeNow()
                .isBefore(Instant.ofEpochMilli(1));

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_AfterMethods_HappyPath() {
        sut_ValidationHandler.requireThat("testDate", Instant.ofEpochMilli(System.currentTimeMillis() + 10000))
                .isAfterNow()
                .isAfter(Instant.now());

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_AllNull_ProducesSafeErrors() {
        sut_ValidationHandler.requireThat("testDate", (Instant) null)
                .isBeforeNow()
                .isBefore(Instant.ofEpochMilli(0))
                .isAfterNow()
                .isAfter(Instant.now());

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_NullInputMethods_HappyPath() {
        sut_ValidationHandler.requireThat("testDate", Instant.ofEpochMilli(0))
                .isBefore(null)
                .isAfter(null);

        assertTrue(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_AllFailures_ProducesSafeErrors() {
        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testDate", Instant.ofEpochMilli(System.currentTimeMillis() + 10000)).isBeforeNow();
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testDate", Instant.ofEpochMilli(System.currentTimeMillis() + 10000)).isBefore(Instant.ofEpochMilli(1));
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testDate", Instant.ofEpochMilli(0)).isAfterNow();
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();

        sut_ValidationHandler.requireThat("testDate", Instant.ofEpochMilli(0)).isAfter(Instant.now());
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());
    }

}