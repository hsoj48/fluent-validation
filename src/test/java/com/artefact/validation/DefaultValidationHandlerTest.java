package com.artefact.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultValidationHandlerTest {

    ValidationHandler sut_DefaultValidationHandler;

    @Before
    public void init() {
        sut_DefaultValidationHandler = new DefaultValidationHandler();
    }

    @Test
    public void testInteger_HappyPath() {
        sut_DefaultValidationHandler.requireThat("testInteger", 123)
                .isPresent()
                .matches(123)
                .matchesOneOf(123, 456)
                .matchesNoneOf(111, 222, null)
                .isTrue(true)
                .isTrue(o -> true)
                .isFalse(false)
                .isFalse(o -> false);

        assertFalse(sut_DefaultValidationHandler.hasErrors());
    }

    @Test
    public void testInteger_AllNull_ProducesSafeErrors() {
        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).isPresent();
        assertTrue(sut_DefaultValidationHandler.hasErrors());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).matches(123);
        assertFalse(sut_DefaultValidationHandler.hasErrors());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).matchesOneOf(123, 456);
        assertFalse(sut_DefaultValidationHandler.hasErrors());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).matchesNoneOf(111, 222, null);
        assertFalse(sut_DefaultValidationHandler.hasErrors());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).isTrue(true);
        assertFalse(sut_DefaultValidationHandler.hasErrors());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).isTrue(o -> true);
        assertFalse(sut_DefaultValidationHandler.hasErrors());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).isFalse(false);
        assertFalse(sut_DefaultValidationHandler.hasErrors());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).isFalse(o -> false);
        assertFalse(sut_DefaultValidationHandler.hasErrors());
    }

    @Test
    public void testInteger_AllFailures_ProducesSafeErrors() {
        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", (Integer) null).isPresent();
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", 234).matches(123);
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", 234).matchesOneOf(123, 456);
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", 234).matchesNoneOf(111, 234, null);
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", 234).isTrue(false);
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", 234).isTrue(o -> false);
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", 234).isFalse(true);
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());

        sut_DefaultValidationHandler = new DefaultValidationHandler();
        sut_DefaultValidationHandler.requireThat("testInteger", 234).isFalse(o -> true);
        assertTrue(sut_DefaultValidationHandler.hasErrors());
        assertEquals("Invalid testInteger", sut_DefaultValidationHandler.getErrorMessagesJoined());
    }

}