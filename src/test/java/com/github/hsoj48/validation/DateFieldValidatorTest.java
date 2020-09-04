package com.github.hsoj48.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DateFieldValidatorTest {

    ValidationHandler sut_ValidationHandler;

    @Before
    public void init() {
        sut_ValidationHandler = new DefaultValidationHandler.Factory().newInstance();
    }

    @Test
    public void testDate_BeforeMethods_HappyPath() {
        sut_ValidationHandler.requireThat("testDate", new Date(0))
                .isBeforeNow()
                .isBefore(new Date(1));

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_AfterMethods_HappyPath() {
        sut_ValidationHandler.requireThat("testDate", new Date(System.currentTimeMillis() + 10000))
                .isAfterNow()
                .isAfter(new Date());

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_AllNull_ProducesSafeErrors() {
        sut_ValidationHandler.requireThat("testDate", (Date) null)
                .isBeforeNow()
                .isBefore(new Date(1))
                .isAfterNow()
                .isAfter(new Date());

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_NullInputMethods_HappyPath() {
        sut_ValidationHandler.requireThat("testDate", new Date(0))
                .isBefore(null)
                .isAfter(null);

        assertTrue(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testDate_AllFailures_ProducesSafeErrors() {
        sut_ValidationHandler = new DefaultValidationHandler.Factory().newInstance();
        sut_ValidationHandler.requireThat("testDate", new Date(System.currentTimeMillis() + 10000)).isBeforeNow();
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler.Factory().newInstance();
        sut_ValidationHandler.requireThat("testDate", new Date(System.currentTimeMillis() + 10000)).isBefore(new Date(1));
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler.Factory().newInstance();
        sut_ValidationHandler.requireThat("testDate", new Date(0)).isAfterNow();
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler.Factory().newInstance();

        sut_ValidationHandler.requireThat("testDate", new Date(0)).isAfter(new Date());
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testDate", sut_ValidationHandler.getErrorMessagesJoined());
    }

}