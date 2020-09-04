package com.github.hsoj48.validation;

public abstract class AbstractValidator<T> implements Validator<T> {

    public void validate(T input) {
        ValidationHandler handler = new DefaultValidationHandler();
        validate(input, handler);
        handler.onError(this::onError);
        handler.onSuccess(o -> onSuccess(input));
    }

    protected abstract void validate(T input, ValidationHandler handler);

    protected abstract void onError(ValidationHandler handler);

    protected void onSuccess(T input) {
        // do nothing by default
    }

}