package com.github.hsoj48.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GenericFieldValidatorTest {

    ValidationHandler sut_ValidationHandler;

    @Before
    public void init() {
        sut_ValidationHandler = new DefaultValidationHandler();
    }

    @Test
    public void testInteger_HappyPath() {
        sut_ValidationHandler.requireThat("testInteger", 123)
                .isPresent()
                .matches(123)
                .matchesOneOf(123, 456)
                .matchesNoneOf(111, 222, null)
                .isTrue(true)
                .isTrue(o -> true)
                .isFalse(false)
                .isFalse(o -> false);

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testInteger_AllNull_ProducesSafeErrors() {
        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).isPresent();
        assertTrue(sut_ValidationHandler.hasErrors());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).matches(123);
        assertFalse(sut_ValidationHandler.hasErrors());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).matchesOneOf(123, 456);
        assertFalse(sut_ValidationHandler.hasErrors());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).matchesNoneOf(111, 222, null);
        assertFalse(sut_ValidationHandler.hasErrors());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).isTrue(true);
        assertFalse(sut_ValidationHandler.hasErrors());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).isTrue(o -> true);
        assertFalse(sut_ValidationHandler.hasErrors());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).isFalse(false);
        assertFalse(sut_ValidationHandler.hasErrors());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).isFalse(o -> false);
        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testInteger_AllFailures_ProducesSafeErrors() {
        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", (Integer) null).isPresent();
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", 234).matches(123);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", 234).matchesOneOf(123, 456);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", 234).matchesNoneOf(111, 234, null);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", 234).isTrue(false);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", 234).isTrue(o -> false);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", 234).isFalse(true);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testInteger", 234).isFalse(o -> true);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_ValidationHandler.getErrorMessagesJoined());
    }

}