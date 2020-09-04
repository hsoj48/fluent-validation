package com.github.hsoj48.validation;

import java.time.Instant;
import java.util.List;

public class InstantFieldValidator extends AbstractFieldValidator<Instant, InstantFieldValidator> {

    InstantFieldValidator(ValidationHandler handler, List<String> fieldNames, Instant value) {
        super(handler, fieldNames, value);
    }

    public InstantFieldValidator isBeforeNow() {
        return isBefore(Instant.now());
    }

    public InstantFieldValidator isAfterNow() {
        return isAfter(Instant.now());
    }

    public InstantFieldValidator isBefore(Instant other) {
        return isTrue(o -> other != null && o.isBefore(other));
    }

    public InstantFieldValidator isAfter(Instant other) {
        return isTrue(o -> other != null && o.isAfter(other));
    }

    @Override
    protected InstantFieldValidator getThis() {
        return this;
    }

}
