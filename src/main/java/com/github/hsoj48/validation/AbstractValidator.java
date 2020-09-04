package com.github.hsoj48.validation;

public abstract class AbstractValidator<T> implements Validator<T> {

    private final ValidationHandlerFactory<DefaultValidationHandler> factory;

    public AbstractValidator() {
        this.factory = new DefaultValidationHandler.Factory();
    }

    public void validate(T input) {
        ValidationHandler handler = factory.newInstance();
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