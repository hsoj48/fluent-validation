package com.github.hsoj48.validation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CollectionFieldValidator<T> extends AbstractFieldValidator<Collection<T>, CollectionFieldValidator<T>> {

    CollectionFieldValidator(ValidationHandler handler, List<String> fieldNames, Collection<T> value) {
        super(handler, fieldNames, value);
    }

    public CollectionFieldValidator<T> isEmpty() {
        return isTrue(Collection::isEmpty);
    }

    public CollectionFieldValidator<T> isNotEmpty() {
        return isFalse(Collection::isEmpty);
    }

    public CollectionFieldValidator<T> hasLength(int expectedLength) {
        return isTrue(o -> o.size() == expectedLength);
    }

    public CollectionFieldValidator<T> noneMatch(T disallowedValue) {
        return isTrue(o -> o.stream().noneMatch(disallowedValue == null ? Objects::isNull : disallowedValue::equals));
    }

    @Override
    protected CollectionFieldValidator<T> getThis() {
        return this;
    }

}
