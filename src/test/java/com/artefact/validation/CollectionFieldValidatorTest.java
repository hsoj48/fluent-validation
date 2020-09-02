package com.artefact.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CollectionFieldValidatorTest {

    ValidationHandler sut_ValidationHandler;

    @Before
    public void init() {
        sut_ValidationHandler = new DefaultValidationHandler();
    }

    @Test
    public void testCollection_HappyPath() {
        sut_ValidationHandler.requireThat("testCollection", Arrays.asList("bacon", "eggs"))
                .isNotEmpty()
                .hasLength(2)
                .noneMatch("toast");

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testCollection_HappyPath2() {
        sut_ValidationHandler.requireThat("testCollection", Collections.emptyList())
                .isEmpty()
                .hasLength(0)
                .noneMatch("toast");

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testCollection_AllNull_ProducesSafeErrors() {
        sut_ValidationHandler.requireThat("testCollection", (Collection<String>) null)
                .isEmpty()
                .isNotEmpty()
                .hasLength(9)
                .noneMatch("bacon");

        assertFalse(sut_ValidationHandler.hasErrors());
    }

    @Test
    public void testCollection_AllFailures_ProducesSafeErrors() {
        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testCollection", Arrays.asList("bacon", "eggs")).isEmpty();
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testCollection", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testCollection", Collections.emptyList()).isNotEmpty();
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testCollection", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();
        sut_ValidationHandler.requireThat("testCollection", Arrays.asList("bacon", "eggs")).hasLength(3);
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testCollection", sut_ValidationHandler.getErrorMessagesJoined());

        sut_ValidationHandler = new DefaultValidationHandler();

        sut_ValidationHandler.requireThat("testCollection", Arrays.asList("bacon", "eggs")).noneMatch("bacon");
        assertTrue(sut_ValidationHandler.hasErrors());
        assertEquals("Invalid testCollection", sut_ValidationHandler.getErrorMessagesJoined());
    }

}