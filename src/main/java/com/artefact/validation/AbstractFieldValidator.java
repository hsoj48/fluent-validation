package com.artefact.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractFieldValidator<T, U extends AbstractFieldValidator<T, U>> {

    private final ValidationHandler handler;
    private final List<String> fieldNames;
    private final T value;

    public U isPresent() {
        return isTrue(value != null);
    }

    public U matches(T other) {
        return isTrue(o -> o.equals(other));
    }

    public U matchesOneOf(T... values) {
        return matchesOneOf(Arrays.asList(values));
    }

    public U matchesOneOf(Collection<T> values) {
        return isTrue(values::contains);
    }

    public U matchesNoneOf(T... values) {
        return matchesNoneOf(Arrays.asList(values));
    }

    public U matchesNoneOf(Collection<T> values) {
        return isFalse(values::contains);
    }

    public U isTrue(boolean test) {
        if (!test) {
            fieldNames.forEach(handler::addFieldError);
        }

        return getThis();
    }

    public U isTrue(Predicate<T> predicate) {
        if (value != null) {
            return isTrue(predicate.test(value));
        } else {
            return getThis();
        }
    }

    public U isFalse(boolean test) {
        if (test) {
            fieldNames.forEach(handler::addFieldError);
        }

        return getThis();
    }

    public U isFalse(Predicate<T> predicate) {
        if (value != null) {
            return isFalse(predicate.test(value));
        } else {
            return getThis();
        }
    }

    protected abstract U getThis();

}
