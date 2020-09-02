package com.artefact.validation;

import java.util.List;

public class GenericFieldValidator<T> extends AbstractFieldValidator<T, GenericFieldValidator<T>> {

    GenericFieldValidator(ValidationHandler handler, List<String> fieldNames, T value) {
        super(handler, fieldNames, value);
    }

    @Override
    protected GenericFieldValidator<T> getThis() {
        return this;
    }

}
