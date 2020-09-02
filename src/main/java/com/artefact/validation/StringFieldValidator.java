package com.artefact.validation;

import java.util.List;
import java.util.regex.Pattern;

public class StringFieldValidator extends AbstractFieldValidator<String, StringFieldValidator> {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?[0-9]+");

    StringFieldValidator(ValidationHandler handler, List<String> fieldNames, String value) {
        super(handler, fieldNames, value);
    }

    @Override
    public StringFieldValidator isPresent() {
        return super.isPresent().isNotEmpty();
    }

    public StringFieldValidator isEmpty() {
        return isTrue(String::isEmpty);
    }

    public StringFieldValidator isNotEmpty() {
        return isFalse(String::isEmpty);
    }

    public StringFieldValidator isNumeric() {
        return matches(NUMERIC_PATTERN);
    }

    public StringFieldValidator matches(Pattern pattern) {
        return isTrue(o -> pattern.matcher(o).matches());
    }

    public StringFieldValidator matchesIgnoreCase(String other) {
        return isTrue(o -> o.equalsIgnoreCase(other));
    }

    public StringFieldValidator hasLength(int expectedLength) {
        return isTrue(o -> o.length() == expectedLength);
    }

    public StringFieldValidator hasLengthGreaterThan(int expectedLength) {
        return isTrue(o -> o.length() > expectedLength);
    }

    public StringFieldValidator hasLengthLessThan(int expectedLength) {
        return isTrue(o -> o.length() < expectedLength);
    }

    @Override
    protected StringFieldValidator getThis() {
        return this;
    }

}
